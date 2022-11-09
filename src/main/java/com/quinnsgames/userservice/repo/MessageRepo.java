package com.quinnsgames.userservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quinnsgames.userservice.domain.Message;

//This interface is automatically implemented by Spring.
public interface MessageRepo extends JpaRepository<Message, Long>{
	//This custom SQL query retrieves all messages sent or received by two specific users, and orders them from oldest to newest.
	@Query(value = "SELECT * FROM Message WHERE (user1_id = :user1 AND user2_id = :user2) OR (user1_id = :user2 AND user2_id = :user1) ORDER BY date", nativeQuery = true)
	List<Message> findUserMessages(@Param("user1") long user1_id, @Param("user2") long user2_id);
}
