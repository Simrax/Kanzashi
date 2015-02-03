package de.ks.kanzashi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String imageUrl;
	
	@Lob
	private byte[] image;
	
	@Size(min = 1, message = "Name must be at least 1 character!")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	private ItemDetail itemDetail;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ItemDetail getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(ItemDetail itemDetail) {
		this.itemDetail = itemDetail;
	}
}
