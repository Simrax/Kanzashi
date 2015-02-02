package de.ks.kanzashi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.ItemDetail;
import de.ks.kanzashi.entity.User;
import de.ks.kanzashi.repository.ItemDetailRepository;
import de.ks.kanzashi.repository.ItemRepository;
import de.ks.kanzashi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemDetailRepository itemDetailRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}
	
	@Transactional
	public User findOneWithItems(int id) {
		User user = findOne(id);
		List<Item> items = itemRepository.findByUser(user);
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
}
