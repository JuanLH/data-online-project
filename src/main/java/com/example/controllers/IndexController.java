package com.example.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.models.entity.Usuario;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("titulo", "Inicio");
		return "welcome";
		
	}
	
	@RequestMapping(value = "/inicio-sesion", method = RequestMethod.GET)
	public String login(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("titulo", "login");
		model.addAttribute("usuario",usuario);
		return "inicio-sesion";
		
	}
	
}
