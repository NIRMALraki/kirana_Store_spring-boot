package com.kirana.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirana.transaction.entity.Tag;
import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

	List<Tag> findByName(String name);
	
}
