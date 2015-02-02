package de.ks.kanzashi.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.ItemDetail;
import de.ks.kanzashi.entity.Role;
import de.ks.kanzashi.entity.User;
import de.ks.kanzashi.repository.ItemDetailRepository;
import de.ks.kanzashi.repository.ItemRepository;
import de.ks.kanzashi.repository.RoleRepository;
import de.ks.kanzashi.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemDetailRepository itemDetailRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		if(roleRepository.findByName("ROLE_ADMIN") == null){		
			// create first role
			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);
			
			// create first user admin
			User userAdmin = new User();
			userAdmin.setName("admin");
			userAdmin.setPassword("admin");
			
			// create role array
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);
			
			// create first Item
			Item item = new Item();
			item.setName("first item test");
			item.setImageUrl("http://www.sankt-georgen.de/sites/sankt-georgen.de/files/u11/avatar-blank.jpg");
			setImage(item.getImageUrl(), item);
			item.setUser(userAdmin);
			itemRepository.save(item);
		}
	}

	public void setImage(String imageUrl, Item item) {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    try {
	    	URL url = new URL(imageUrl);
	        byte[] chunk = new byte[4096];
	        int bytesRead;
	        InputStream stream = url.openStream();

	        while ((bytesRead = stream.read(chunk)) > 0) {
	            outputStream.write(chunk, 0, bytesRead);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    item.setImage(outputStream.toByteArray());
	}

}
