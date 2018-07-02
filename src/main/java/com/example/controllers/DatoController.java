package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.models.entity.Dato;
import com.example.models.entity.GrupoDato;


@Controller
public class DatoController {

	@RequestMapping(value = "/new-fact", method = RequestMethod.GET)
	public String nuevoGrupoPage(Model model) {
		Dato dato = new Dato();
		List<GrupoDato> grupos = new ArrayList<>();
		grupos.add(new GrupoDato("Personales", "Datos Personales"));
		grupos.add(new GrupoDato("Financieros", "Datos Financieros"));
		model.addAttribute("dato",dato);
		model.addAttribute("groups",grupos);
		return "logged/new-fact";
	}
	
}
