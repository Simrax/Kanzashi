package de.ks.kanzashi.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.ks.kanzashi.entity.File;
import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.service.ItemService;
import de.ks.kanzashi.service.UserService;
import de.ks.kanzashi.validator.FileValidator;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	FileValidator validator;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("user", userService.findByName("admin"));
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String fileUploaded(Model model, @Validated File file, BindingResult result, Principal principal) throws IOException{
		String name = principal.getName();
		Item item = new Item();
		item.setImage(file.getFile().getBytes());
		itemService.save(item, name);
		return "redirect:/index.html";
	}
}
