package de.ks.kanzashi.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Customer;
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
	
	@PreAuthorize("#customer.email == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("customer") Customer customer) {
		userRepository.delete(customer);
	}
}
