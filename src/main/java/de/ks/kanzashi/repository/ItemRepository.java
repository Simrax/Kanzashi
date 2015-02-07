package de.ks.kanzashi.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.Customer;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	List<Item> findByCustomer(Customer customer, Pageable pageable);

	Item findById(Integer id);

	Item findByName(String name);

	List<Item> findItemListByCustomer(Customer customer, PageRequest pageRequest);
}
