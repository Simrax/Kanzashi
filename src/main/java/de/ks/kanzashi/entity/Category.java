package de.ks.kanzashi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 1, message = "Name must be at least 5 characters!")
	private String name;
	
	@OneToMany(mappedBy = "category", cascade=CascadeType.REMOVE)
	private List<Item> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
