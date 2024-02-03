package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
     /**
      * Endpoint GET /login to return Login
      * @param session attribute allowing to get a user connect
      * @param model attribute who make link between the front ans back end 
      * @return html page of login
      */
    @GetMapping("/login") 
	public String login(HttpSession session, Model model    ){ 
		  
		  
		model.addAttribute("connectForm", new Users());
		
	
	  
	return "Login"; 
	  
	}
	 
	/**
	 * method allowing the login of a user to access at the application
	 * @param user value of the user connect username
	 * @param password value of the user connect password
	 * @param connectForm attribute allowing connect the back and the front end
	 * @return html page of bidList/list
	 */
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String logUser(@Validated  Users  user, @RequestParam("username") String username, @RequestParam("password") String password,
							@ModelAttribute Users connectForm ) {	
			
	return "bidList/list";
	}

}
