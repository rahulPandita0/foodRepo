package com.foodapp.genericfoodapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.genericfoodapp.entity.Friends;
import com.foodapp.genericfoodapp.entity.User;

public interface FriendsRepository extends JpaRepository<Friends, Integer>{
	
	
	public Optional<Friends> findBySenderAndReceiver(User sender,User receiver); 
	
	
	public List<Friends> findBySenderAndApprove(User sender,Boolean approve);
	public List<Friends> findByReceiverAndApprove(User receiver,Boolean approve );
	
	public List<Friends> findBySenderAndReceiverAndApprove(User sender,User receiver, Boolean approve);
	
	
	
	

}
