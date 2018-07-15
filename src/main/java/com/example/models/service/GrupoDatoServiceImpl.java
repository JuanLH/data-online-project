package com.example.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.dao.IGrupoDatoDao;
import com.example.models.entity.GrupoDato;
import com.example.models.entity.Usuario;

@Service
public class GrupoDatoServiceImpl implements IGrupoDatoService {

	@Autowired
	private IGrupoDatoDao grupoDatoDao;
	
	@Override
	public List<GrupoDato> findAll() {
		return (List<GrupoDato>) grupoDatoDao.findAll();
	}

	@Override
	public void save(GrupoDato usuario) {
		grupoDatoDao.save(usuario);
		
	}

	@Override
	public GrupoDato findOne(Long id) {
		return grupoDatoDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		grupoDatoDao.deleteById(id);
	}

	@Override
	public List<GrupoDato> findByUsuario(Usuario usuario) {
		return grupoDatoDao.findByUsuario(usuario);
	}

}
