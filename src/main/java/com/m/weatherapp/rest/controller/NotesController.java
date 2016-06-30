package com.m.weatherapp.rest.controller;

import com.m.weatherapp.service.dto.NoteDto;
import com.m.weatherapp.service.service.AdminUserService;
import com.m.weatherapp.rest.dto.request.GetAllNotesRequestDTO;
import com.m.weatherapp.rest.dto.response.GetAllNotesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<GetAllNotesResponseDTO> viewNotes(@RequestBody GetAllNotesRequestDTO requestDTO) {

		List<NoteDto> notesList = adminUserService.getAllNotes(requestDTO.getUserEmail());
		
		// create response entity with list of notes
		ResponseEntity<GetAllNotesResponseDTO> responseEntity = new ResponseEntity<GetAllNotesResponseDTO>(new GetAllNotesResponseDTO(notesList), HttpStatus.OK);

		return responseEntity;
	}
	
}
