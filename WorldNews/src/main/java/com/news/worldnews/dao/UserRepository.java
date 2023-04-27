package com.news.worldnews.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.news.worldnews.entity.User;



@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
	 @Transactional
	 @Modifying
	 @Query(value = "UPDATE USER_TABLE a SET a.enable = TRUE WHERE a.email = ?1", nativeQuery = true)
	 public void enableUser(String email);
}
