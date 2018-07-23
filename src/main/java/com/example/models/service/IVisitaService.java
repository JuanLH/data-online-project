package com.example.models.service;

import java.util.List;

import com.example.models.entity.Visita;



public interface IVisitaService {
	public List<Visita> findAll();
	public void save(Visita visita);
	public Visita findOne(Long id);
	public void delete(Long id);
	public Visita findByClave(String clave);
}
