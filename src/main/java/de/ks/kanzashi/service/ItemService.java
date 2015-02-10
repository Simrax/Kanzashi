package de.ks.kanzashi.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Category;
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

	public Page<Item> findAll(Integer pageNumber) {
		return itemRepository.findAll(new PageRequest(0, 5, Direction.DESC, "name"));
	}
	
	public List<Item> findAll() {
		Sort sort = new Sort(Direction.DESC, "releaseDate");
		return itemRepository.findAll(sort);
	}

	public List<Item> findByCategory(Category category) {
		List<Item> items = itemRepository.findByCategory(category);
		Collections.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return o2.getReleaseDate().compareTo(o1.getReleaseDate());
			}
			});
		return items;
	}
}
