package com.apps.digiple.npdapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    
	@GetMapping("/banks")
	public String getBanks() {
		return "banks";
	}
    
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
    
	@GetMapping("/data-visualization")
	public String getDataVisualization() {
		return "data-visualization";
	}
    
	@GetMapping("/maps")
	public String getMaps() {
		return "maps";
	}
    
	@GetMapping("/preferences")
	public String getPreferences() {
		return "preferences";
	}
	
	@GetMapping("/manage-users")
	public String getManageUsers() {
		return "manage-users";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
}
