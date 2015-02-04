package de.ks.kanzashi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.Customer;
import de.ks.kanzashi.entity.ItemImage;
import de.ks.kanzashi.repository.ItemRepository;
import de.ks.kanzashi.repository.UserRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void save(Item item, String name, ItemImage itemImage) {
		Customer customer = userRepository.findByEmail(name);
		item.setCustomer(customer);
		item.setItemImage(itemImage);
		itemRepository.save(item);
	}

	@PreAuthorize("#item.customer.email == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("item") Item item) {
		itemRepository.delete(item);
	}

	public Item findById(int id) {
		return itemRepository.findById(id);
	}

	public Item findByName(String name) {
		return itemRepository.findByName(name);
	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}
}
