package com.kirana.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirana.transaction.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	User findByEmail(String email);
	boolean existsByEmail(String email);
}
