package de.ks.kanzashi.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.ItemImage;
import de.ks.kanzashi.fileUpload.FileValidator;
import de.ks.kanzashi.service.CustomerService;
import de.ks.kanzashi.service.ItemImageService;
import de.ks.kanzashi.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private CustomerService userService;
	
	@Autowired
	private ItemImageService itemImageService;
	
	@Autowired
	FileValidator validator;
	
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute("item")
	public Item constructItem(){
		return new Item();
	}
	
	@RequestMapping("/item")
	public String index(Model model){
		model.addAttribute("items", itemService.findAll());
		return "item";
	}
	
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public String fileUploaded(@ModelAttribute Item item, BindingResult result, Principal principal) throws IOException{
		String name = principal.getName();
		ItemImage itemImage = new ItemImage();
		itemImage.setImage(item.getFile().getBytes());
		
		itemService.save(item, name, itemImage);
		
		return "redirect:/item.html";
	}
	
	@RequestMapping("/item/itemDetails/{name}")
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
