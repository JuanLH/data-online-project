package com.example.models.service;

import java.util.List;

import com.example.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();
	public void save(Usuario usuario);
	public Usuario findOne(Long id);
	public void delete(Long id);
	public Usuario findByNombreAndClave(String nombre,String clave);
	public Usuario findByNombre(String nombre);
}
