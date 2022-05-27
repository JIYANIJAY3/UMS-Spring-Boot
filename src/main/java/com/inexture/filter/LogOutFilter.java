package com.inexture.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebFilter(urlPatterns = {"/UserHome", "/UserProfile","/updateUserController","/AdminHome","/AdminProfile","/adminRegistration"})
public class LogOutFilter implements Filter {

	static final Logger log = Logger.getLogger("debugLog");

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("do filter");
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0
		res.setDateHeader("Expires", 0); // Proxies.
		HttpSession session = req.getSession(false);
 
		if (session == null) {
			res.sendRedirect("index");
		} else {
			chain.doFilter(req, res);
		}
	}
}