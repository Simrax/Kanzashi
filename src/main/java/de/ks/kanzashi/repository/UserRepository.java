package de.ks.kanzashi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Customer;
import de.ks.kanzashi.entity.Item;

public interface UserRepository extends JpaRepository<Customer, Integer>{

	Customer findByEmail(String name);

}
