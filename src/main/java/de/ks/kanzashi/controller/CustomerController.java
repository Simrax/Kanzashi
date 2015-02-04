package de.ks.kanzashi.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.ks.kanzashi.entity.Customer;
import de.ks.kanzashi.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@ModelAttribute("user")
	public Customer construct(){
		return new Customer();
	}
	
	@RequestMapping("/users")
	public String users(Model model){
		model.addAttribute("users", customerService.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{email}")
	public String detail(Model model, @PathVariable String email){
		model.addAttribute("user", customerService.findOne(email));
		return "user-detail";
	}
	
	@RequestMapping("/register")
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") Customer user, BindingResult result){
		if(result.hasErrors()){
			return "user-register";
		}
		
		if(customerService.findOne(user.getEmail()) != null)
			return "redirect:/register.html?success=false";
		
		customerService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user", customerService.findOne(name));
		return "user-detail";
	}
	
	@RequestMapping("/users/remove/{id}")
	public String removeUser(@PathVariable int id){
		customerService.delete(id);
		return "redirect:/users.html";
	}
}
