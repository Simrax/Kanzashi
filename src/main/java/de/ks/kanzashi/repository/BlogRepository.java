package de.ks.kanzashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ks.kanzashi.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

}
