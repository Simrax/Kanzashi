package de.ks.kanzashi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HerkunftController {
	
	@RequestMapping("/herkunft")
	public String users(){
		return "herkunft";
	}

}
