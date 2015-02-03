package de.ks.kanzashi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.User;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	List<Item> findByUser(User user, Pageable pageable);

	Item findById(Integer id);

	Item findByName(String name);
}
