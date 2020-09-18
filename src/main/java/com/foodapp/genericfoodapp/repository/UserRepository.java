package com.foodapp.genericfoodapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foodapp.genericfoodapp.entity.Friends;
import com.foodapp.genericfoodapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	@Query("select model from User model where model.country = :country and model.state = :state"
			+ " and model.city = :city and model.preference.crusine = :crusine "
			+ " and model.preference.lunch = :lunch and model.preference.dinner = :dinner"
			+ " and model.preference.vegetarian = :vegetarian and model.preference.nonvegetarian = :nonvegetarian "
			+ " and model.preference.eggetarian = :eggetarian "
			+ " and model.id != :userId")
	public List<User> findByUserPreferences(String country ,String state,String city,String crusine,Boolean lunch,
			Boolean dinner,Boolean vegetarian,Boolean nonvegetarian,Boolean eggetarian,Integer userId );
	
	public Optional<User> findByEmailAndPassword(String email,String password);
	
}	
	
