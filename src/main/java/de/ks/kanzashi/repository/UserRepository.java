package de.ks.kanzashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
