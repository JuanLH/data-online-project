package com.example.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entity.GrupoDato;
import com.example.models.entity.Usuario;

public interface IGrupoDatoDao extends CrudRepository<GrupoDato, Long>{
	
	public List<GrupoDato> findByUsuario(Usuario usuario);
}
