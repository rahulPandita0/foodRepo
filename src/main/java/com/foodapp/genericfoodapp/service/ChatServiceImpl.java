/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.foodapp.genericfoodapp.Exception.CustomException;
import com.foodapp.genericfoodapp.dto.ChatDTO;
import com.foodapp.genericfoodapp.dto.ChatResponseDTO;
import com.foodapp.genericfoodapp.entity.Chat;
import com.foodapp.genericfoodapp.entity.Friends;
import com.foodapp.genericfoodapp.entity.User;
import com.foodapp.genericfoodapp.repository.ChatRepository;
import com.foodapp.genericfoodapp.repository.FriendsRepository;
import com.foodapp.genericfoodapp.repository.UserRepository;

/**
 * @author rahul.pandita
 *
 */
@Service
public class ChatServiceImpl extends GenericService implements ChatService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ChatRepository chatRepository;

	@Autowired
	FriendsRepository friendsRepository;

	@Override
	public void postMessage(ChatDTO chatDto, Integer Id) {

		Optional<User> user = userRepository.findById(Id);
//		to do check if users are frnds; 

		User sender = getUser();

		if (user.isPresent()) {
			if (checkUsersAreFrnds(user.get(), sender)) {
				Chat chat = new Chat();
				chat.setMessage(chatDto.getMessage());
				chat.setReceiver(user.get());
				chat.setSender(sender);
				chat.setTimestamp(new Date());
				chatRepository.save(chat);
			}else {
				throw new CustomException("not friend with "+user.get().getName());
			}

		} else {
			throw new CustomException("unable to find user");
		}
	}

	private User getUser() {
		return userRepository.findById(getExecutingUser()).get();
	}

	@Override
	public List<ChatResponseDTO> getChat(Integer userId) {

		Optional<User> user = userRepository.findById(userId);
		List<ChatResponseDTO> chatResponseDTOs = new ArrayList<ChatResponseDTO>();
		if (user.isPresent()) {
			List<Chat> ChatList = chatRepository.findchattingBtwnUsers(user.get(), getUser());
			for (Chat chat : ChatList) {
				ChatResponseDTO chatResponseDTO = new ChatResponseDTO();
				chatResponseDTO.setMessage(chat.getMessage());
				chatResponseDTO.setReceiverName(chat.getReceiver().getName());
				chatResponseDTO.setSenderName(chat.getSender().getName());
				chatResponseDTO.setTimeStamp(chat.getTimestamp());
				chatResponseDTOs.add(chatResponseDTO);
			}
		}
		return chatResponseDTOs;
	}

	private Boolean checkUsersAreFrnds(User user1, User user2) {

		List<Friends> findBySenderAndReceiverAndApprove = friendsRepository.findBySenderAndReceiverAndApprove(user1,
				user2, true);
		if (!CollectionUtils.isEmpty(findBySenderAndReceiverAndApprove)) {
			return true;
		}
		List<Friends> findBySenderAndReceiverAndApprove2 = friendsRepository.findBySenderAndReceiverAndApprove(user2,
				user1, true);
		if (!CollectionUtils.isEmpty(findBySenderAndReceiverAndApprove2)) {
			return true;
		}
		return false;

	}

}
