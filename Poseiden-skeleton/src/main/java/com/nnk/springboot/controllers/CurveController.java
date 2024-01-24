package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.BidService;
import com.nnk.springboot.services.CurvePointService;

import jakarta.validation.Valid;

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

/*
 * 
 */
@Controller
public class CurveController {

@Autowired
private CurvePointRepository curvePointRepository;

@Autowired
private CurvePointService curvePointService;


    private static final Logger logger = LogManager.getLogger("BidListController");

/**
 * 
 * @param model
 * @return
 */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
         List<CurvePoint> curvePointLists = curvePointService.getCurvePoints();
       model.addAttribute("curvePoints", curvePointLists);
        
        return "curvePoint/list";
    }
/**
 * 
 * @param curvePoint
 * @return
 */
    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

/**
 * 
 * @param curvePoint
 * @param result
 * @param model
 * @return
 */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if (!result.hasErrors()) {
            logger.info("Returning curvePoint/add page");
		model.addAttribute("curvePoint", curvePoint);
        curvePointService.postCurvePoint(curvePoint);        
        return "redirect:/curvePoint/list";	
		}
         logger.info("error Redirect: curvePoint/add");
        return "curvePoint/add";
    }
/**
 * 
 * @param id
 * @param model
 * @return
 */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if(! curvePoint.isPresent()){
            
             }
             
        model.addAttribute("curvePoint", curvePoint.get());
        return "curvePoint/update";
    }
/**
 * 
 * @param id
 * @param curvePoint
 * @param result
 * @param model
 * @return
 */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
                                if (result.hasErrors()) {
			logger.info("error Redirect curvePoint/update ");
			return "curvePoint/update";
		}
		logger.info("Returning curvePoint/update page");
		model.addAttribute("curvePoint", curvePoint);
		curvePointService.putCurvePoint(id, curvePoint.getCurveId(), curvePoint.getTerm(), curvePoint.getValue());
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        return "redirect:/curvePoint/list";
    }
/**
 * 
 * @param id
 * @param model
 * @return
 */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
         curvePointService.deleteCurvePoint(curvePoint.get().getId());;
        return "redirect:/curvePoint/list";
    }
}
