package de.ks.kanzashi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.ks.kanzashi.entity.Customer;
import de.ks.kanzashi.service.CustomerService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private CustomerService customerService;
	
	@ModelAttribute("customer")
	public Customer constructUser() {
		return new Customer();
	}

	@RequestMapping
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("customer") Customer customer, 
			BindingResult result, RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			return "user-register";
		}
		
		customerService.save(customer);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/register.html";
	}
	
	@RequestMapping("/available")
	@ResponseBody
	public String available(@RequestParam String email){
		Boolean available = customerService.findOne(email) == null;
		return available.toString();
	}
}
