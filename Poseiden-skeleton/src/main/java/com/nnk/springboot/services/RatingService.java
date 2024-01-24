package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
@Service
public class RatingService {

     @Autowired
    private  RatingRepository ratingRepository;
    private static final Logger logger = LogManager.getLogger("BidListService");

@Transactional(rollbackFor = { Exception.class})
    public List<Rating> getRatings() {

	return (List<Rating>)ratingRepository.findAll();
    }


@Transactional(rollbackFor = { Exception.class})
    public void postRating( Rating ratingToAdd){

        ratingRepository.save(ratingToAdd);
    }

@Transactional(rollbackFor = { Exception.class})
    public void putRating(int ratingToUpdateId, String moodysRatingToUpdate , String sandPRatingToUpdate
                        , String fitchRatingToUpdate , int orderNumberToUpdate)  {
        Optional <Rating> ratingUpdate = ratingRepository.findById(ratingToUpdateId);
        if ( ratingUpdate.isPresent()){
            ratingUpdate.get().setId(ratingToUpdateId);
            ratingUpdate.get().setMoodysRating(moodysRatingToUpdate);
            ratingUpdate.get().setSandPRating(sandPRatingToUpdate);
            ratingUpdate.get().setFitchRating(fitchRatingToUpdate);
            ratingUpdate.get().setOrderNumber(orderNumberToUpdate);
            ratingRepository.save(ratingUpdate.get());
        }
        
        
    }

@Transactional(rollbackFor = { Exception.class})
    public void deleteRating(int ratingToDeleteId){
        Optional <Rating> ratingToDelete = ratingRepository.findById(ratingToDeleteId);
        if ( ratingToDelete.isPresent()){

            ratingRepository.delete(ratingToDelete.get());
        }



    }

}
