package com.nnk.springboot.configuration;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustumLogoutSuccessHandler implements LogoutSuccessHandler {
	
	
    private static final Logger logger = LogManager.getLogger("CustumLogoutSuccessHandler");

	/**
	 * method allowing authentication success and create request session
	 */
	

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        String refererUrl = request.getHeader("Referer");
        logger.info("Logout from: " + refererUrl);

		request.getSession().removeAttribute("username");
		response.sendRedirect("/login");;

	}
	
}
