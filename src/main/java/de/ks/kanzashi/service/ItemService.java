package de.ks.kanzashi.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Customer;
import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.fileUpload.BASE64DecodedMultipartFile;
import de.ks.kanzashi.repository.ItemRepository;
import de.ks.kanzashi.repository.UserRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void save(Item item, String name) {
		Customer customer = userRepository.findByEmail(name);
		item.setCustomer(customer);
		itemRepository.save(item);
	}
	
	public void save(Item item) {
		itemRepository.save(item);
	}

	@PreAuthorize("#item.customer.email == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("item") Item item) {
		itemRepository.delete(item);
	}

	public Item findById(int id) {
		return itemRepository.findById(id);
	}
	
	@Transactional
	public Item findOneWithImage(String name){
		Item item = itemRepository.findByName(name);
		byte[] image = item.getItemImage().getImage();
		BASE64DecodedMultipartFile base64DecodedMultipartFile = new BASE64DecodedMultipartFile(image);
		item.setFile(base64DecodedMultipartFile);
		return item;
	}

	public Item findByName(String name) {
		return findOneWithImage(name);
	}

	public Page<Item> findAll(Integer pageNumber) {
		return itemRepository.findAll(new PageRequest(0, 5, Direction.DESC, "name"));
	}
	
	public List<Item> findAll() {
//		List<Item> items = itemRepository.findAll();
//		items.parallelStream()
//			.forEach(item -> {
//				byte[] image = item.getItemImage().getImage();
//				BASE64DecodedMultipartFile decodedMultipartFile = new BASE64DecodedMultipartFile(image);
//				item.getFile().
//			});
		return itemRepository.findAll();
	}
}
