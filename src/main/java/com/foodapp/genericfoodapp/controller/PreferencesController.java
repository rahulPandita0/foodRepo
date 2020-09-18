package com.foodapp.genericfoodapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.foodapp.genericfoodapp.dto.PreferenceDTO;
import com.foodapp.genericfoodapp.dto.UserResponseDTO;
import com.foodapp.genericfoodapp.service.PreferenceService;

@RestController
@RequestMapping("preferences")
public class PreferencesController {
	
	@Autowired
	private PreferenceService preferenceService;

	@PostMapping("")
	public ResponseEntity<JsonNode> addPreferences(@Valid @RequestBody(required = true) PreferenceDTO preferences) {
		 preferenceService.updatePreference(preferences);
		 return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@GetMapping("/compare")
	public ResponseEntity<List<UserResponseDTO>> comparePreferences() {
		return ResponseEntity.status(HttpStatus.OK).body(preferenceService.comparePreferences());
	}
	
}
