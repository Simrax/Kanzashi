package de.ks.kanzashi.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.ItemDetail;
import de.ks.kanzashi.entity.Role;
import de.ks.kanzashi.entity.User;
import de.ks.kanzashi.repository.ItemDetailRepository;
import de.ks.kanzashi.repository.ItemRepository;
import de.ks.kanzashi.repository.RoleRepository;
import de.ks.kanzashi.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemDetailRepository itemDetailRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}
	
	@Transactional
	public User findOneWithItems(int id) {
		User user = findOne(id);
		List<Item> items = itemRepository.findByUser(user, new PageRequest(0, 10, Direction.DESC, "name"));
		items.parallelStream()
			.forEach(item -> {
				ItemDetail itemDetail = itemDetailRepository.findByItem(item);
				item.setItemDetail(itemDetail);
			});
		user.setItems(items);
		return user;
	}

	public User findByName(String name) {
		User user = userRepository.findByName(name);
		return findOneWithItems(user.getId());
	}

	public byte[] loadImage(int id) {
		return itemRepository.findById(id).getImage();
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		// create roles
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		userRepository.save(user);
	}

	public void delete(int id) {
		userRepository.delete(id);
	}
}
