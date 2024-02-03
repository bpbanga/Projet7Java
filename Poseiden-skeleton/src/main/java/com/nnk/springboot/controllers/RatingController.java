package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.BidService;
import com.nnk.springboot.services.RatingService;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;


@Controller
public class RatingController {
    // TODO: Inject Rating service
@Autowired
private RatingService ratingService;
@Autowired
private RatingRepository ratingRepository;

    private static final Logger logger = LogManager.getLogger("RatingController");


    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        List<Rating> rating = ratingService.getRatings();
       model.addAttribute("ratings", rating);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }
   
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        
        if (!result.hasErrors()) {
            logger.info("Returning rating/add page");
		model.addAttribute("rating", rating);
        ratingService.postRating(rating);        
        return "redirect:/rating/list";	
		}
           logger.info("error Redirect: rating/add");

               
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        
         Optional<Rating> rating = ratingRepository.findById(id);
        if(! rating.isPresent()){
          
             }
             
            model.addAttribute("rating", rating.get());
        
        return "rating/update";
        
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
			logger.info(" error Redirect:rating/update ");
			return "rating/update";
		}
		logger.info("Returning rating/update page");
		model.addAttribute("rating", rating);
		ratingService.putRating(rating.getId(), rating.getMoodysRating(), rating.getSandPRating(),
         rating.getFitchRating(), rating.getOrderNumber());
        
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        // TODO: Find Rating by Id and delete the Rating, return to Rating list
         Optional<Rating> rating = ratingRepository.findById(id);
         ratingService.deleteRating(rating.get().getId());;
        return "redirect:/rating/list";
        
    }
}
