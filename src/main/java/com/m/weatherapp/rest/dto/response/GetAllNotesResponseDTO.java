package com.m.weatherapp.rest.dto.response;

import com.m.weatherapp.service.dto.NoteDto;

import java.util.List;

public class GetAllNotesResponseDTO extends ResponseDTO{
	
	List<NoteDto> notesList;

	
	
	public GetAllNotesResponseDTO(List<NoteDto> notesList) {
		super();
		this.notesList = notesList;
	}

	public List<NoteDto> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<NoteDto> notesList) {
		this.notesList = notesList;
	}
	
	
	
}
