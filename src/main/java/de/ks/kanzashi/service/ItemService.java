package de.ks.kanzashi.service;

import org.springframework.beans.factory.annotation.Autowired;
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

	
}
