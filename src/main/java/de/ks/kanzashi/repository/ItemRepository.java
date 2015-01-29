package de.ks.kanzashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
