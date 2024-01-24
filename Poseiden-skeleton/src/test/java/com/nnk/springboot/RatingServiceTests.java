package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@SpringBootTest
public class RatingServiceTests {

    @Autowired
	private RatingService ratingService;
	private static int idRating;

	 private static Rating ratingToAdd = new Rating();

	@BeforeAll
    public  static void setup() {

	
    ratingToAdd.setId(102);   
    ratingToAdd.setMoodysRating("moodysRating");
    ratingToAdd.setSandPRating("sandPRating");
    ratingToAdd.setFitchRating("fitchRating");
    ratingToAdd.setOrderNumber(96);
    idRating = 102;
    }

	@AfterEach
    public void deleteAddedRating() {
	ratingService.deleteRating(idRating);
    }
	

	@Test
	public void shouldRetrieveNonEMptyRating(){
		Assertions.assertNotNull(ratingService.getRatings());
	}
	@Test
	public void shouldAddSuccessfullyRating(){
		ratingService.postRating(ratingToAdd);
		List<Rating> listRatings = ratingService.getRatings();
		Assertions.assertEquals(listRatings.get(listRatings.size() - 1).getOrderNumber(),
												 ratingToAdd.getOrderNumber());
	}

	@Test
	public void shouldEditSuccefullyRating(){
		ratingService.postRating(ratingToAdd);
		List<Rating> listRatings = ratingService.getRatings();
		int initSize = listRatings.size();
		Rating ratingToEdit = new Rating();
		ratingToEdit.setId(listRatings.get(initSize-1).getId());
    	ratingToEdit.setMoodysRating("moodysRatin");
    	ratingToEdit.setSandPRating("sandPRatin");
    	ratingToEdit.setFitchRating("fitchRatin");
    	ratingToEdit.setOrderNumber(98);
		ratingService.putRating(ratingToEdit.getId(),ratingToEdit.getMoodysRating(),ratingToEdit.getSandPRating(),
								  ratingToEdit.getFitchRating(), ratingToEdit.getOrderNumber());
		listRatings = ratingService.getRatings();
		initSize = listRatings.size();
		Assertions.assertEquals(ratingToEdit.getMoodysRating(), listRatings.get(initSize-1).getMoodysRating());
		Assertions.assertEquals(ratingToEdit.getSandPRating(), listRatings.get(initSize-1).getSandPRating());
		Assertions.assertEquals(ratingToEdit.getFitchRating(), listRatings.get(initSize-1).getFitchRating());
		Assertions.assertEquals(ratingToEdit.getOrderNumber(), listRatings.get(initSize -1).getOrderNumber());
	}

	@Test
	public void shouldNotEditSuccefullyRating(){
		ratingService.postRating(ratingToAdd);
		List<Rating> listRatings = ratingService.getRatings();
		int initSize = listRatings.size();
		Rating ratingToEdit = new Rating();
		ratingToEdit.setId(listRatings.get(initSize-1).getId()+1);
    	ratingToEdit.setMoodysRating("moodysRatin");
    	ratingToEdit.setSandPRating("sandPRatin");
    	ratingToEdit.setFitchRating("fitchRatin");
    	ratingToEdit.setOrderNumber(98);
		ratingService.putRating(ratingToEdit.getId(),ratingToEdit.getMoodysRating(),ratingToEdit.getSandPRating(),
								  ratingToEdit.getFitchRating(), ratingToEdit.getOrderNumber());
		listRatings = ratingService.getRatings();
		initSize = listRatings.size();
		Assertions.assertNotEquals(ratingToEdit.getMoodysRating(), listRatings.get(initSize-1).getMoodysRating());
		Assertions.assertNotEquals(ratingToEdit.getSandPRating(), listRatings.get(initSize-1).getSandPRating());
		Assertions.assertNotEquals(ratingToEdit.getFitchRating(), listRatings.get(initSize-1).getFitchRating());
		Assertions.assertNotEquals(ratingToEdit.getOrderNumber(), listRatings.get(initSize -1).getOrderNumber());
	}

	@Test
    public void shouldDeleteSuccessfullyRating() {
		List<Rating> listRatings = ratingService.getRatings();
		int initSize = listRatings.size();

		ratingService.postRating(ratingToAdd);
		listRatings = ratingService.getRatings();
		assertEquals(initSize + 1, listRatings.size());

		ratingService.deleteRating(listRatings.get(initSize).getId());
		listRatings = ratingService.getRatings();	
		assertEquals(initSize, listRatings.size());
    }

}
