package com.m.weatherapp.model.repository;

import com.m.weatherapp.model.config.ModelConfig;
import com.m.weatherapp.model.entity.PredefinedNote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ModelConfig.class)
@Transactional
public class PredefinedNoteRepoTest {

	@Autowired
	private PredefinedNoteRepo PredefinedNoteRepo;

	@Test
	public void testGetAll() {

		List<PredefinedNote> list = PredefinedNoteRepo.findAll();

		// assert that return list size is 4
		assertEquals(4, list.size());

	}

	@Test
	public void testFindPredefinedNoteByTempValue() {

		PredefinedNote predefinedNote = PredefinedNoteRepo.findByMinTempLessThanAndMaxTempGreaterThan(5.0f);

		// assert not null
		assertNotNull(predefinedNote);

	}

	@Test
	public void testUpdatePredefinedNote() {

		String note = "weather is fine";

		// select first predefined note
		PredefinedNote predefinedNote = PredefinedNoteRepo.findByMinTempLessThanAndMaxTempGreaterThan(5.0f);
		predefinedNote.setPredefinedNoteText(note);

		// update note
		PredefinedNoteRepo.save(predefinedNote);

		// select updated pre-definend note
		PredefinedNote updatedPredefinedNote = PredefinedNoteRepo.findByMinTempLessThanAndMaxTempGreaterThan(5.0f);

		// assert equals
		assertEquals(note, updatedPredefinedNote.getPredefinedNoteText());

	}

	@Test
	public void testFindById() {

		PredefinedNote predefinedNote = PredefinedNoteRepo.findById(3);

		// assert not null
		assertNotNull(predefinedNote);
		
		// assert minTemp = 15 and max temp = 20
		assertEquals(new Float(15), predefinedNote.getMinTemp());
		assertEquals(new Float(20), predefinedNote.getMaxTemp());

	}

}
