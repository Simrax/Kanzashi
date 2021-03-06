package de.ks.kanzashi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import de.ks.kanzashi.annotation.UniqueEmail;

@Entity
public class Customer {
	
	private Date registered;
	
	@Id
	@Size(min = 1, message = "Ung�ltige E-Mail -Adresse!")
	@Email(message = "Ung�ltige E-Mail -Adresse!")
	@UniqueEmail(message = "Diese E-Mail-Adresse existiert bereits")
	private String email;
	
	@Size(min = 5, message = "Name muss mindestens 5 Zeichen lang sein!")
	private String password;
	
	private boolean enabled;
	
	@ManyToMany
	@JoinTable
	private List<Role> roles;
	
	@OneToMany(mappedBy = "customer", cascade=CascadeType.REMOVE)
	private List<Item> items;

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
