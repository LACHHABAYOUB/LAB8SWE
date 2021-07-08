package edu.miu.de.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/eregistrar/home")
    public String showHomepage() {
        return "home/index";
    }
	
	@GetMapping("/")
	public String redirect() {
		return "redirect:/eregistrar/home";
	}

}
