package com.example.controllers;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.models.entity.Gender;
import com.example.models.entity.GrupoDato;
import com.example.models.entity.Usuario;
import com.example.models.service.IUsuarioService;

@Controller
public class UsuarioController {

	@Autowired // para que sirve autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value="/registro", method = RequestMethod.GET)
	public  String registrar(Map<String,Object> model) {
		Usuario usuario = new Usuario();
		List<Gender> genders = new ArrayList<Gender>();
		genders.add(new Gender("Masculino", 'M'));
		genders.add(new Gender("Femenino", 'F'));
		genders.add(new Gender("Indeciso", 'I'));
		
		model.put("usuario", usuario);
		model.put("titulo", "Registrarse");
		model.put("genders", genders);
		return "registro";
	}
	
	@RequestMapping(value="/guardar-usuario", method=RequestMethod.POST)
	public String guardarUsuario(@Valid Usuario usuario,BindingResult result, Model model,RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			List<Gender> genders = new ArrayList<Gender>();
			genders.add(new Gender("Masculino", 'M'));
			genders.add(new Gender("Femenino", 'F'));
			genders.add(new Gender("Indeciso", 'I'));
			model.addAttribute("titulo", "Registrarse");
			model.addAttribute("genders", genders);
			
			System.out.println("Error Count = "+result.getErrorCount());
			for( ObjectError obj: result.getAllErrors()) {
				System.out.println(obj.getDefaultMessage()+" * "+obj.toString());
			}
			return "registro";
		}
		
		String mensajeFlash = (usuario.getId() != null)? "Usuario editado con exito" : "Usuario creado con exito";
		usuarioService.save(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/inicio-sesion";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)//login
	public String login(@ModelAttribute("usuario") Usuario usuario,Model model,HttpSession session) {
		Usuario user = usuarioService.findByNombreAndClave(usuario.getNombre(),  usuario.getClave());
		if(user == null) {
			model.addAttribute("error","Login Fail");
			return "/inicio-sesion";
		}
		else {
			session.setAttribute("nombre", usuario.getNombre());
			List<GrupoDato> grupos =  usuarioService.
					findByNombre((String)session.getAttribute("nombre")).getGruposDatos();
			model.addAttribute("grupoList",grupos);
			for(GrupoDato g:grupos) {
				System.out.println(g.getDescripcion());
			}
			return "/logged/home";
		}
	}
	
	@RequestMapping(value="/logged/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("nombre");
		return "redirect:/";
	}
}
