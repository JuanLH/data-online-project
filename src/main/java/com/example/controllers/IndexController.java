package com.example.controllers;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.models.entity.Usuario;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model,HttpSession session) {
		model.addAttribute("titulo", "Inicio");
		if(session.getAttribute("nombre")==null)
			return "welcome";
		else
			return "logged/home";
		
	}
	
	@RequestMapping(value = "/inicio-sesion", method = RequestMethod.GET)
	public String login(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("titulo", "login");
		model.addAttribute("usuario",usuario);
		return "inicio-sesion";
		
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("titulo", "Inicio");
		return "/logged/home";
		
	}
	
}
