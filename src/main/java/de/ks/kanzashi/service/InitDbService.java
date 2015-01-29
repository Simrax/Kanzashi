package de.ks.kanzashi.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Blog;
import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.Role;
import de.ks.kanzashi.entity.User;
import de.ks.kanzashi.repository.BlogRepository;
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
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		// if(roleRepository.findByeName("ROLE_ADMIN") == null){
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setName("admin");
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		Blog firstBlog = new Blog();
		firstBlog.setName("first test blog");
		firstBlog.setImageUrl("http://www.sankt-georgen.de/sites/sankt-georgen.de/files/u11/avatar-blank.jpg");
		try {
			setImage(firstBlog.getImageUrl(), firstBlog);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		firstBlog.setUser(userAdmin);
		blogRepository.save(firstBlog);

		Item firstItem = new Item();
		firstItem.setBlog(firstBlog);
		firstItem.setImageUrl("hier url vom Image");
		firstItem.setInsertDate(new Date());
		firstItem.setVideoUrl("hier video url");
		itemRepository.save(firstItem);
		// }
	}

	public void setImage(String imageUrl, Blog blog)
			throws MalformedURLException {
		URL url = new URL(imageUrl);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	    try {
	        byte[] chunk = new byte[4096];
	        int bytesRead;
	        InputStream stream = url.openStream();

	        while ((bytesRead = stream.read(chunk)) > 0) {
	            outputStream.write(chunk, 0, bytesRead);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    blog.setImage(outputStream.toByteArray());
	}

}
