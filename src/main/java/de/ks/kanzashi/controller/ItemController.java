package de.ks.kanzashi.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.fileUpload.FileUpload;
import de.ks.kanzashi.fileUpload.FileValidator;
import de.ks.kanzashi.service.ItemService;
import de.ks.kanzashi.service.UserService;

@Controller
public class ItemController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	FileValidator validator;
	
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute("file")
	public FileUpload construct(){
		return new FileUpload();
	}
	
	@RequestMapping("/item")
	public String index(Model model){
		model.addAttribute("user", userService.findByName("admin"));
		return "item";
	}
	
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public String fileUploaded(Model model, @Validated FileUpload file, BindingResult result, Principal principal) throws IOException{
		String name = principal.getName();
		Item item = new Item();
		item.setImage(file.getFile().getBytes());
		item.setName(file.getName());
		itemService.save(item, name);
		return "redirect:/item.html";
	}
	
	@RequestMapping("/item/{name}")
	public String detail(Model model, @PathVariable String name){
		model.addAttribute("item", itemService.findByName(name));
		return "item-detail";
	}
	
	@RequestMapping("/item/remove/{id}")
	public String removeItem(@PathVariable int id){
		Item item = itemService.findById(id);
		itemService.delete(item);
		return "redirect:/item.html";
	}
}
