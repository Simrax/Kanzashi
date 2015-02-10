package de.ks.kanzashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findByName(String name);

	Category findByIdAndName(int id, String name);
	
}
