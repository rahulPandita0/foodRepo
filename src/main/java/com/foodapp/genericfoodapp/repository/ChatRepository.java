package com.foodapp.genericfoodapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foodapp.genericfoodapp.entity.Chat;
import com.foodapp.genericfoodapp.entity.User;
import com.sun.el.stream.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	@Query("select model from Chat model where (sender = :sender and receiver = :receiver) or (sender = :receiver and receiver = :sender) order by timestamp desc   ")
	public List<Chat> findchattingBtwnUsers(User sender,User receiver);

}
