package com.example.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.dao.IUsuarioDao;
import com.example.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired //para que se usa esta annotation
	private IUsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDao.findAll();
	}

	@Override
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	public Usuario findByNombreAndClave(String nombre, String clave) {
		return usuarioDao.findByNombreAndClave(nombre, clave);
	}

	@Override
	public Usuario findByNombre(String nombre) {
		return usuarioDao.findByNombre(nombre);
	}
	

}
