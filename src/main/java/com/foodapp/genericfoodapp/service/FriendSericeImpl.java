package com.foodapp.genericfoodapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.foodapp.genericfoodapp.Exception.CustomException;
import com.foodapp.genericfoodapp.dto.FriendResponseDTO;
import com.foodapp.genericfoodapp.entity.Friends;
import com.foodapp.genericfoodapp.entity.User;
import com.foodapp.genericfoodapp.repository.FriendsRepository;
import com.foodapp.genericfoodapp.repository.UserRepository;

@Service
public class FriendSericeImpl extends GenericService implements FriendService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FriendsRepository friendsRepository;

	@Override
	public void postFriendRequest(Integer userId) {

		Optional<User> user = userRepository.findById(userId);
		// to do check if sender and user are not same
//		to do check if they are already friends
		if (user.isPresent()) {
			Friends friends = new Friends();
			friends.setSender(getUser());
			friends.setReceiver(user.get());
			friends.setTimestamp(new Date());
			friendsRepository.save(friends);
//			return "saved";

		} else {
			throw new CustomException("user not found");
		}

	}

	@Override
	public void respondFriendRequest(Integer userId, Boolean response) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			Optional<Friends> friend = friendsRepository.findBySenderAndReceiver(user.get(), getUser());
			if (friend.isPresent()) {
				friend.get().setApprove(response);
				friendsRepository.save(friend.get());
//				return "response updated";
			} else {
//				return "frnd request not found";
			}
		} else {
//			return "user not found";
		}

	}

	private User getUser() {
		return userRepository.findById(getExecutingUser()).get();
	}

	@Override
	public List<FriendResponseDTO> fetchAllFriends() {

		List<Friends> allFriends = new ArrayList<Friends>();

		List<Friends> findByReceiverAndApprove = friendsRepository.findByReceiverAndApprove(getUser(), true);
		List<Friends> findBySenderAndApprove = friendsRepository.findBySenderAndApprove(getUser(), true);

		List<FriendResponseDTO> friendResponseDTOs = new ArrayList<FriendResponseDTO>();

		if (!CollectionUtils.isEmpty(findByReceiverAndApprove)) {
			for (Friends friend : findByReceiverAndApprove) {
				if (Objects.nonNull(friend.getSender())) {
					FriendResponseDTO friendResponseDTO = new FriendResponseDTO();
					BeanUtils.copyProperties(friend.getSender(), friendResponseDTO);
					friendResponseDTOs.add(friendResponseDTO);
				}

			}
		}
		if (!CollectionUtils.isEmpty(findBySenderAndApprove)) {
			for (Friends friend : findByReceiverAndApprove) {
				if (Objects.nonNull(friend.getReceiver())) {
					FriendResponseDTO friendResponseDTO = new FriendResponseDTO();
					BeanUtils.copyProperties(friend.getReceiver(), friendResponseDTO);
					friendResponseDTOs.add(friendResponseDTO);
				}

			}
		}

		return friendResponseDTOs;

	}

	@Override
	public List<FriendResponseDTO> fetchPendingFriends() {

		List<Friends> findByReceiverAndApprove = friendsRepository.findByReceiverAndApprove(getUser(), null);
		List<Friends> findBySenderAndApprove = friendsRepository.findBySenderAndApprove(getUser(), null);

		List<FriendResponseDTO> friendResponseDTOs = new ArrayList<FriendResponseDTO>();

		if (!CollectionUtils.isEmpty(findByReceiverAndApprove)) {
			for (Friends friend : findByReceiverAndApprove) {
				if (Objects.nonNull(friend.getSender())) {
					FriendResponseDTO friendResponseDTO = new FriendResponseDTO();
					BeanUtils.copyProperties(friend.getSender(), friendResponseDTO);
					friendResponseDTOs.add(friendResponseDTO);
				}

			}
		}
		if (!CollectionUtils.isEmpty(findBySenderAndApprove)) {
			for (Friends friend : findByReceiverAndApprove) {
				if (Objects.nonNull(friend.getReceiver())) {
					FriendResponseDTO friendResponseDTO = new FriendResponseDTO();
					BeanUtils.copyProperties(friend.getReceiver(), friendResponseDTO);
					friendResponseDTOs.add(friendResponseDTO);
				}

			}
		}

		return friendResponseDTOs;
	}

}
