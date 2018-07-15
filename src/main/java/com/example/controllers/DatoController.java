package com.example.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.models.entity.Dato;
import com.example.models.entity.GrupoDato;
import com.example.models.service.IDatoService;
import com.example.models.service.IGrupoDatoService;
import com.example.models.service.IUsuarioService;


@Controller
public class DatoController {

	@Autowired
	IGrupoDatoService grupoDatoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired IDatoService datoService;
	
	@RequestMapping(value = "/new-fact", method = RequestMethod.GET)
	public String nuevoGrupoPage(Model model,HttpSession session) {
		Dato dato = new Dato();
		
		List<GrupoDato> grupos =  usuarioService.findByNombre((String)session.getAttribute("nombre")).getGruposDatos();
		//List<GrupoDato> grupos =grupoDatoService.findByUsuario(usuarioService.findByNombre((String)session.getAttribute("nombre")));
		
		model.addAttribute("dato",dato);
		model.addAttribute("groups",grupos);
		return "logged/new-fact";
	}
	
	@RequestMapping(value="/guardar-dato", method = RequestMethod.POST)
	public String nuevoGrupo(@Valid Dato dato,BindingResult result,Model model,RedirectAttributes flash,SessionStatus status,HttpSession session) {
		if(result.hasErrors()) {
			Dato dato2 = new Dato();
			List<GrupoDato> grupos =  usuarioService.findByNombre((String)session.getAttribute("nombre")).getGruposDatos();
			
			model.addAttribute("dato",dato2);
			model.addAttribute("groups",grupos);
			
			System.out.println("Error Count = "+result.getErrorCount());
			for( ObjectError obj: result.getAllErrors()) {
				System.out.println(obj.getDefaultMessage()+" * "+obj.toString());
			}
			/*System.out.println("+++++++++++");
			System.out.println(dato.toString());*/
			
			return "logged/new-fact";
		}
		
		String mensajeFlash = (dato.getId() != null)? "Grupo editado con exito" : "Grupo creado con exito";
		datoService.save(dato);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/home";
	}
	
	@RequestMapping(value="/eliminar-dato/{id}")
	public String eliminar_dato(@PathVariable(value="id") Long id,RedirectAttributes flash) {
		if(id>0) {
			datoService.delete(id);
			flash.addFlashAttribute("success", "Dato eliminado con exito");
		}
		return "redirect:/home";
	}
}
