package com.example.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.dao.IDatoDao;
import com.example.models.entity.Dato;

@Service
public class DatoServiceImpl implements IDatoService {

	@Autowired
	private IDatoDao datoDao;
	
	@Override
	public List<Dato> findAll() {
		return (List<Dato>) datoDao.findAll();
	}

	@Override
	public void save(Dato dato) {
		datoDao.save(dato);
		
	}

	@Override
	public Dato findOne(Long id) {
		return datoDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		datoDao.deleteById(id);
	}

}
