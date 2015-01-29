package de.ks.kanzashi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

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
	public void init(){
		//if(roleRepository.findByeName("ROLE_ADMIN") == null){
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
			firstBlog.setImageUrl("hier url vom Image");
			firstBlog.setUser(userAdmin);
			blogRepository.save(firstBlog);
			
			Item firstItem = new Item();
			firstItem.setBlog(firstBlog);
			firstItem.setImageUrl("hier url vom Image");
			firstItem.setInsertDate(new Date());
			firstItem.setVideoUrl("hier video url");
			itemRepository.save(firstItem);
		//}
	}
}
