package de.ks.kanzashi.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Customer;
import de.ks.kanzashi.entity.Role;
import de.ks.kanzashi.repository.ItemImageRepository;
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
	private ItemImageRepository itemDetailRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		if(roleRepository.findByName("ROLE_ADMIN") == null){		
			// create role
			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);
			
			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);
			
			// create first user admin
			Customer userAdmin = new Customer();
			userAdmin.setEnabled(true);
			userAdmin.setEmail("wooff@gmx.de");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userAdmin.setPassword(encoder.encode("12345"));
			
			// create role array
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);
			
//			// create first Item
//			Item item = new Item();
//			item.setName("first item test");
//			item.setImageUrl("http://www.sankt-georgen.de/sites/sankt-georgen.de/files/u11/avatar-blank.jpg");
//			setImage(item.getImageUrl(), item);
//			item.setUser(userAdmin);
//			itemRepository.save(item);
		}
	}

//	public void setImage(String imageUrl, Item item) {
//		
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	    try {
//	    	URL url = new URL(imageUrl);
//	        byte[] chunk = new byte[4096];
//	        int bytesRead;
//	        InputStream stream = url.openStream();
//
//	        while ((bytesRead = stream.read(chunk)) > 0) {
//	            outputStream.write(chunk, 0, bytesRead);
//	        }
//
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	    item.setImage(outputStream.toByteArray());
//	}

}
