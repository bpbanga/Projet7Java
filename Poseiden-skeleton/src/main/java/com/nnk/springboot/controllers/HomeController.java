package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Users;
import com.nnk.springboot.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
/*
 * 
 */
@Controller
public class HomeController
{

@Autowired
	private UserRepository userRepository;
/**
 * 
 * @param model
 * @param session
 * @param isAdmin
 * @return
 */
	@RequestMapping("/home")
	public String home(Model model,  HttpSession session   )
	{
		
			 Users utiConnect = userRepository.findByUsername( session.getAttribute("username").toString());
			if("ADMIN".equals(utiConnect.getRole())){
				
					model.addAttribute("isAdmin", "true");
			}else {
				model.addAttribute("isAdmin", "false");
			}
				
		      
		

		return "home";
	}

	


}
