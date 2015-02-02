package de.ks.kanzashi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.ks.kanzashi.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("user", userService.findByName("admin"));
		return "index";
	}
}
