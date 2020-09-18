package com.foodapp.genericfoodapp.service;

import java.util.List;

import com.foodapp.genericfoodapp.dto.ChatDTO;
import com.foodapp.genericfoodapp.dto.ChatResponseDTO;

public interface ChatService {

	public void postMessage(ChatDTO chatDto ,Integer Id); 
	
	public List<ChatResponseDTO> getChat(Integer userId);
	
}
