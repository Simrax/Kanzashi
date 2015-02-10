package de.ks.kanzashi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.Category;
import de.ks.kanzashi.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	public void save(Category category) {
		categoryRepository.save(category);
	}

	public Category findById(int id) {
		return categoryRepository.findOne(id);
	}

	public void delete(Category category) {
		categoryRepository.delete(category);
	}

	public Category findByIdAndName(int id, String name) {
		return categoryRepository.findByIdAndName(id, name);
	}
	
}
