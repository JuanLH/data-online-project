package com.example.controllers;



import java.util.Date;
import java.util.List;

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
import com.example.models.entity.Usuario;
import com.example.models.entity.Visita;
import com.example.models.service.IUsuarioService;
import com.example.models.service.IVisitaService;


@Controller
public class VisitaController {

	@Autowired
	private IVisitaService visitaService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/new-visit", method = RequestMethod.GET)
	public String home(Model model,HttpSession session) {
		Visita visita = new Visita();
		List<GrupoDato> grupos =  usuarioService.findByNombre((String)session.getAttribute("nombre")).getGruposDatos();
		model.addAttribute("titulo", "Visitas");
		model.addAttribute("visita", visita);
		model.addAttribute("grupoList", grupos);
		
		return "/logged/new-visit";	
	}
	
	@RequestMapping(value="/guardar-visita",method= RequestMethod.POST)
	public String guardar_visita(@Valid Visita visita, Model model,HttpSession session,BindingResult result,RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			
			System.out.println("Error Count = "+result.getErrorCount());
			for( ObjectError obj: result.getAllErrors()) {
				System.out.println(obj.getDefaultMessage()+" * "+obj.toString());
			}
			return "/logged/new-visit";
		}
		visita.setUsuario(usuarioService.findByNombre((String)session.getAttribute("nombre")));
		String mensajeFlash = (visita.getId() != null)? "Visita editada con exito" : "Visita creada con exito";
		visitaService.save(visita);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/home";

	}
	
	@RequestMapping(value = "/visit", method = RequestMethod.GET)
	public String visitar(Model model) {
		Visita visita = new Visita();
		model.addAttribute("visita", visita);
		model.addAttribute("titulo", "Visitar");
		
		return "/logged/visit";
		
	}
	
	@RequestMapping(value = "/visitar", method = RequestMethod.POST)
	public String visit(@Valid Visita visita,Model model,HttpSession session,BindingResult result,RedirectAttributes flash,SessionStatus status) {
		String mensajeFlash;
		Visita visit_original = visitaService.findByClave(visita.getClave());
		if(visit_original!= null) {
			if(visit_original.getEstado() != 0) {
				List<GrupoDato> grupos =  usuarioService.findByNombre(visit_original.getUsuario().getNombre()).getGruposDatos();
				model.addAttribute("grupoList", grupos);
				model.addAttribute("nom_propietario", visit_original.getUsuario().getNombre());
				visit_original.setEstado(0);
				Usuario user = usuarioService.findByNombre((String)session.getAttribute("nombre"));
				visit_original.setUsuarioVisitante(user);
				visit_original.setFechaVisita(new Date());
				visitaService.save(visit_original);
				mensajeFlash = "Acceso exitoso";
				status.setComplete();
				flash.addFlashAttribute("success", mensajeFlash);
				return "/logged/visita_home";
			}
			else {
				
				mensajeFlash = "La clave ha sido usada, ya no es valida!";
				status.setComplete();
				flash.addFlashAttribute("error", mensajeFlash);
				return "redirect:/visit";
			}
		}
		else {
			
			mensajeFlash = "No existe ninguna visita con esta clave";
			status.setComplete();
			flash.addFlashAttribute("error", mensajeFlash);
			return "redirect:/visit";
		}
	}
}
