package com.example.models.service;

import java.util.List;

import com.example.models.entity.GrupoDato;

public interface IGrupoDatoService {
	public List<GrupoDato> findAll();
	public void save(GrupoDato usuario);
	public GrupoDato findOne(Long id);
	public void delete(Long id);
}
