package de.ks.kanzashi.service;

import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import de.ks.kanzashi.entity.Customer;
import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.ItemImage;
import de.ks.kanzashi.entity.Role;
import de.ks.kanzashi.repository.ItemImageRepository;
import de.ks.kanzashi.repository.ItemRepository;
import de.ks.kanzashi.repository.RoleRepository;
import de.ks.kanzashi.repository.UserRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemImageRepository itemImageRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Customer> findAll(){
		return userRepository.findAll();
	}

	public Customer findOne(String email) {
		return userRepository.findByEmail(email);
	}
	
//	@Transactional
//	public Customer findOneWithItems(String email) {
//		Customer customer = findOne(email);
//		List<Item> items = itemRepository.findByCustomer(customer, new PageRequest(0, 10, Direction.DESC, "releaseDate"));
//		items.parallelStream()
//			.forEach(item -> {
//				ItemImage itemImage = itemImageRepository.findByItem(item);
//				item.setItemImage(itemImage);
//			});
//		customer.setItems(items);
//		return customer;
//	}

//	public Customer findByName(String name) {
//		Customer user = userRepository.findByName(name);
//		return findOneWithItems(user.getId());
//	}

	public void save(Customer user) {
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
