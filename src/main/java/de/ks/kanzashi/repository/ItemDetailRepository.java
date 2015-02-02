package de.ks.kanzashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.ItemDetail;

public interface ItemDetailRepository extends JpaRepository<ItemDetail, Integer>{

	ItemDetail findByItem(Item item);
}
