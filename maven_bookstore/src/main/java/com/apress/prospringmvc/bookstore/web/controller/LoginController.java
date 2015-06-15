package com.apress.prospringmvc.bookstore.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Role;
import com.apress.prospringmvc.bookstore.service.AccountService;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;

@Controller
@RequestMapping(value ="/login")
public class LoginController {

	public static final String ACCOUNT_ATTRIBUTE = "account";
	public static final String REQUESTED_URL = "REQUESTED_URL";
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String handleLogin(@RequestParam String username,
																		 @RequestParam String password,
																		 RedirectAttributes redirect,
																		 HttpServletRequest request, HttpSession session) throws AuthenticationException {
		
		Account account = accountService.login(username, password);
		List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("ROLE_ADMIN"));
		session.setAttribute(ACCOUNT_ATTRIBUTE, account);
		String url = (String)session.getAttribute(REQUESTED_URL);
		session.removeAttribute(REQUESTED_URL);
		if(StringUtils.hasText(url) && !url.contains("login")) {
			return "redirect:" + url;
		} else {
			return "redirect:/index.htm";
		}
		/*
		try {
			Account account = accountService.login(username, password);
			session.setAttribute(ACCOUNT_ATTRIBUTE, account);
			return "redirect:/index.htm";
			
		} catch(AuthenticationException ex) {
			redirect.addFlashAttribute("exception", ex);
			return "redirect:/login";
		}
		*/
	}
}
