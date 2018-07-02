package com.example.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	//@Query("SELECT u FROM Usuario u where u.usuario = :usuario and u.clave = :clave")
	public Usuario findByNombreAndClave(String nombre,String clave);
	public Usuario findByNombre(String nombre);
}
