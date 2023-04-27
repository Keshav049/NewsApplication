package com.news.worldnews.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.news.worldnews.entity.ConformationToken;

@Repository
@Transactional(readOnly = true)
public interface ConformationTokenRepository extends JpaRepository<ConformationToken, Long>{

	 Optional<ConformationToken> findByToken(String token);
	

    @Transactional
    @Modifying
    @Query(value = "UPDATE CONFORMATION_TOKEN  c " +
            "SET c.confirm_Time = ?2 " +
            "WHERE c.token = ?1", nativeQuery = true)
	public int updateConfirmedAt(String token, LocalDateTime now);
    
    @Transactional
    @Query(value = "SELECT * FROM CONFORMATION_TOKEN u WHERE u.USER_ID = ?1",nativeQuery = true)
    public ConformationToken findByUserId(long userId);       
   
}
