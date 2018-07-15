package com.example.controllers;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.models.entity.Dato;
import com.example.models.entity.GrupoDato;
import com.example.models.entity.Usuario;
import com.example.models.service.IUsuarioService;

@Controller
public class IndexController {

	@Autowired
	private IUsuarioService usuarioService;
	
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
	public String home(Model model,HttpSession session) {
		model.addAttribute("titulo", "Inicio");
		
		List<GrupoDato> grupos =  usuarioService.findByNombre((String)session.getAttribute("nombre")).getGruposDatos();
		model.addAttribute("grupoList",grupos);
		for(GrupoDato g:grupos) {
			System.out.println(g.getDescripcion());
		}
		//Intentare iterar la lista de grupos y vere si desde thymelead puedo iterar la lista de datos
		//https://stackoverflow.com/questions/24459936/loop-through-array-in-thymeleaf
		
		return "/logged/home";
		
	}
	
}
