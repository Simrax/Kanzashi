package de.ks.kanzashi.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.ks.kanzashi.entity.Category;
import de.ks.kanzashi.entity.Customer;
import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.entity.ItemImage;
import de.ks.kanzashi.fileUpload.FileValidator;
import de.ks.kanzashi.service.CategoryService;
import de.ks.kanzashi.service.CustomerService;
import de.ks.kanzashi.service.ItemImageService;
import de.ks.kanzashi.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ItemImageService itemImageService;
	
	@Autowired
	private CategoryService categoryService;
	
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
		model.addAttribute("categorys", categoryService.findAll());
		return "item";
	}
	
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public String createItem(@Valid @ModelAttribute("item") Item item, 
			BindingResult result, Principal principal, RedirectAttributes redirectAttributes) throws IOException{
		if(result.hasErrors()){		
		    redirectAttributes.addFlashAttribute("fail", false);
			return "redirect:/";
		}
		
		item.setReleaseDate(new Date());
		
		byte[] image = item.getFile().getBytes();
		ItemImage itemImage = new ItemImage();
		itemImage.setImage(image);
		item.setItemImage(itemImage);
		
		Category category = categoryService.findById(item.getCategoryId());
		item.setCategory(category);
		
		Customer customer = customerService.findOne(principal.getName());
		item.setCustomer(customer);
		
		itemService.save(item);
		
		return "redirect:/";
	}
	
	@RequestMapping("/item/itemDetails/{id}")
	public String detail(Model model, @PathVariable int id){
		model.addAttribute("item", itemService.findById(id));
		model.addAttribute("categorys", categoryService.findAll());
		return "item-detail";
	}
	
	@RequestMapping(value = "/item/editDetails/{id}", method = RequestMethod.POST)
	public String editItem(@Valid @ModelAttribute("item") Item item, BindingResult result, 
			RedirectAttributes redirectAttributes, Principal principal, @PathVariable int id) throws IOException{
		
		boolean isFileLoaded = true;
		if(result.hasErrors()){
			if(result.getFieldErrors().size() == 1){
				if(!result.getFieldErrors().get(0).getField().equals("file")){
					redirectAttributes.addFlashAttribute("fail", false);
					return "redirect:/item/itemDetails/" + item.getId() + ".html";
				}else {
					isFileLoaded = false;
				}
			}else {
				redirectAttributes.addFlashAttribute("fail", false);
				return "redirect:/item/itemDetails/" + item.getId() + ".html";
			}
		}
		
		Item oldItem = itemService.findById(id);
		
		if(item.getName() != null){
			oldItem.setName(item.getName());
		}
		
		if(item.getCategoryId() != null){
			Category category = categoryService.findById(item.getCategoryId());
			oldItem.setCategory(category);
		}
		
		if(isFileLoaded){
			byte[] image = item.getFile().getBytes();
			ItemImage itemImage = new ItemImage();
			itemImage.setImage(image);
			oldItem.setItemImage(itemImage);
		}
		
		if(item.getDescription() != null){
			oldItem.setDescription(item.getDescription());
		}
		
		oldItem.setPrice(item.getPrice());
		Customer customer = customerService.findOne(principal.getName());
		oldItem.setCustomer(customer);
		
		itemService.save(oldItem);
		
		return "redirect:/item/itemDetails/" + item.getId() + ".html";
	}
	
	@RequestMapping("/item/remove/{id}")
	public String removeItem(@PathVariable int id){
		Item item = itemService.findById(id);
		itemService.delete(item);
		return "redirect:/";
	}
	
	@RequestMapping("item/{name}")
	public String category(Model model, @PathVariable String name, HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		Category category = categoryService.findByIdAndName(id, name);
		List<Item> items = itemService.findByCategory(category);
		model.addAttribute("items", items);
		model.addAttribute("categorys", categoryService.findAll());
		return "item";
	}
	
	@RequestMapping("/item/category/remove/{id}")
	public String removeCategory(@PathVariable int id){
		Category category = categoryService.findById(id);
		categoryService.delete(category);
		return "redirect:/";
	}
}
