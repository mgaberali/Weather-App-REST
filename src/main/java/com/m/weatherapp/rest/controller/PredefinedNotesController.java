package com.m.weatherapp.rest.controller;

import com.m.weatherapp.service.dto.PredefinedNoteDto;
import com.m.weatherapp.service.service.AdminUserService;
import com.m.weatherapp.rest.dto.request.SetPredefinedNotesRequestDTO;
import com.m.weatherapp.rest.dto.response.GetPredefinedNotesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/predefinedNotes")
public class PredefinedNotesController {

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GetPredefinedNotesResponseDTO> viewPredefinedNotes() {

		List<PredefinedNoteDto> predefinedNotes = adminUserService.getAllPredefinedNotes();

		GetPredefinedNotesResponseDTO responseDTO = new GetPredefinedNotesResponseDTO();
		
		for (PredefinedNoteDto predefinedNoteDto : predefinedNotes) {

			if (predefinedNoteDto.getMinTemp() == 1) {
				responseDTO.setFrom1To10(predefinedNoteDto.getPredefinedNoteText());

			} else if (predefinedNoteDto.getMinTemp() == 10) {
				responseDTO.setFrom10To15(predefinedNoteDto.getPredefinedNoteText());

			} else if (predefinedNoteDto.getMinTemp() == 15) {
				responseDTO.setFrom15To20(predefinedNoteDto.getPredefinedNoteText());

			} else if (predefinedNoteDto.getMinTemp() == 20) {
				responseDTO.setAbove20(predefinedNoteDto.getPredefinedNoteText());
			}

		}
		
		ResponseEntity<GetPredefinedNotesResponseDTO> responseEntity = new ResponseEntity<GetPredefinedNotesResponseDTO>(responseDTO, HttpStatus.OK);

		return responseEntity;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> setPredefinedNotes(@RequestBody SetPredefinedNotesRequestDTO requestDTO) {
		
		// get all predefined notes
		List<PredefinedNoteDto> predefinedNotes = adminUserService.getAllPredefinedNotes();

		for (PredefinedNoteDto predefinedNoteDto : predefinedNotes) {

			if (predefinedNoteDto.getMinTemp() == 1) {
				predefinedNoteDto.setPredefinedNoteText(requestDTO.getFrom1To10());

			} else if (predefinedNoteDto.getMinTemp() == 10) {
				predefinedNoteDto.setPredefinedNoteText(requestDTO.getFrom10To15());

			} else if (predefinedNoteDto.getMinTemp() == 15) {
				predefinedNoteDto.setPredefinedNoteText(requestDTO.getFrom15To20());

			} else if (predefinedNoteDto.getMinTemp() == 20) {
				predefinedNoteDto.setPredefinedNoteText(requestDTO.getAbove20());
			}

		}

		adminUserService.setPredefinedNotesList(predefinedNotes);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
