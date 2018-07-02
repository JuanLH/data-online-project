package com.example.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.models.entity.GrupoDato;
import com.example.models.service.IGrupoDatoService;
import com.example.models.service.IUsuarioService;

@Controller
public class GrupoDatoController {

	@Autowired
	private IGrupoDatoService grupoDatoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/new-group", method = RequestMethod.GET)
	public String nuevoGrupoPage(Model model,HttpSession session) {
		GrupoDato grupo = new GrupoDato();
		grupo.setUsuario(usuarioService.findByNombre((String)session.getAttribute("nombre")));
		model.addAttribute("grupoDato",grupo);
		return "logged/new-group";
	}
	
	@RequestMapping(value = "/guardar-grupo", method = RequestMethod.POST)
	public String nuevoGrupo(@Valid GrupoDato grupoDato,BindingResult result,Model model,RedirectAttributes flash,SessionStatus status) {
		
		if(result.hasErrors()) {
			GrupoDato grupo = new GrupoDato();
			model.addAttribute("grupoDato",grupo);
			
			System.out.println("Error Count = "+result.getErrorCount());
			for( ObjectError obj: result.getAllErrors()) {
				System.out.println(obj.getDefaultMessage()+" * "+obj.toString());
			}
			
			return "logged/new-group";
		}
		
		String mensajeFlash = (grupoDato.getId() != null)? "Grupo editado con exito" : "Grupo creado con exito";
		grupoDatoService.save(grupoDato);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/home";
	}
	
}
