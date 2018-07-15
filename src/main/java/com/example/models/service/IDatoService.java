package com.example.models.service;

import java.util.List;

import com.example.models.entity.Dato;

public interface IDatoService {
	public List<Dato> findAll();
	public void save(Dato dato);
	public Dato findOne(Long id);
	public void delete(Long id);
}
