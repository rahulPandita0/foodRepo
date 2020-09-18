package com.foodapp.genericfoodapp.service;

import java.util.List;

import com.foodapp.genericfoodapp.dto.FriendResponseDTO;

public interface FriendService {

	public void postFriendRequest(Integer userId);
	
	public void respondFriendRequest(Integer userId,Boolean response);
	
	public List<FriendResponseDTO> fetchAllFriends();
	
	public List<FriendResponseDTO> fetchPendingFriends();
	
	
}
