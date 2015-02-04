package de.ks.kanzashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.ItemImage;

public interface ItemImageRepository extends JpaRepository<ItemImage, Integer>{

	ItemImage findById(Integer id);

}
