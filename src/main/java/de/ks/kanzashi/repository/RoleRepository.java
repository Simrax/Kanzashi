package de.ks.kanzashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
