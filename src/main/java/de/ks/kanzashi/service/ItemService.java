package de.ks.kanzashi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.User;
import de.ks.kanzashi.repository.ItemRepository;
import de.ks.kanzashi.repository.UserRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void save(Item item, String name) {
		User user = userRepository.findByName(name);
		item.setUser(user);
		itemRepository.save(item);
	}

	@PreAuthorize("#item.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("item") Item item) {
		itemRepository.delete(item);
	}

	public Item findById(int id) {
		return itemRepository.findById(id);
	}

	public Item findByName(String name) {
		return itemRepository.findByName(name);
	}
}
