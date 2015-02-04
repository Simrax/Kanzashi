package de.ks.kanzashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Customer;

public interface UserRepository extends JpaRepository<Customer, Integer>{

	Customer findByEmail(String name);

}
