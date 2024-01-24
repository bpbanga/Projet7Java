package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Users;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.BidService;

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

/*
 * 
 */
@Controller
public class BidListController {

@Autowired
private BidListRepository bidListRepository;
@Autowired
private BidService bidListService;
@Autowired


    private static final Logger logger = LogManager.getLogger("BidListController");
/**
 * 
 * @param model
 * @return
 */
    @RequestMapping("/bidList/list")
    public String home(Model model   )
    {
       List<Bid> bidList = bidListService.getBidLists();
       model.addAttribute("bidLists", bidList);
        
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Bid bidList) {  

        return "bidList/add";
    }
/**
 * 
 * @param bidList
 * @param result
 * @param model
 * @return
 */
    @PostMapping("/bidList/validate")
    public String validate(@Valid Bid bidList, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            logger.info("Returning bidList/add page");
		model.addAttribute("bidList", bidList);
		bidListService.postBid(bidList);
        
        return "redirect:/bidList/list";	
		}
            
 logger.info("error Redirect: bidList/add");
         return "bidList/add";
		
        
        // TODO: check data valid and save to db, after saving return bid list
        
    }
/**
 * 
 * @param id
 * @param model
 * @return
 */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Bid> bid = bidListRepository.findById(id);
        if(! bid.isPresent()){
              
             }
             
        model.addAttribute("bidList", bid.get());
        // TODO: get Bid by Id and to model then show to the form
        return "bidList/update";
    }
/**
 * 
 * @param id
 * @param bidList
 * @param result
 * @param model
 * @return
 */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid Bid bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info(" error Redirect:bidList/update ");
		
        return "bidList/update";
        }
        logger.info(" Returning bidList/update page ");
        model.addAttribute("bidList", bidList);
		bidListService.putBid(bidList.getBidListId(), bidList.getAccount(), bidList.getType(), bidList.getBidQuantity());
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        return "redirect:/bidList/list";	
		
    }
/**
 * 
 * @param id
 * @param model
 * @return
 */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
         Optional<Bid> bid = bidListRepository.findById(id);
         bidListService.deleteBid(bid.get().getBidListId());
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        return "redirect:/bidList/list";
    }
}
