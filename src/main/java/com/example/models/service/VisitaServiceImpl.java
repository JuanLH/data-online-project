package com.example.models.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.dao.IVisitaDao;
import com.example.models.entity.Visita;

@Service
public class VisitaServiceImpl implements IVisitaService {

	@Autowired
	private IVisitaDao visitaDao;
	
	@Override
	public List<Visita> findAll() {
		// TODO Auto-generated method stub
		return (List<Visita>) visitaDao.findAll();
	}

	@Override
	public void save(Visita visita) {
		visitaDao.save(visita);
		
	}

	@Override
	public Visita findOne(Long id) {
		// TODO Auto-generated method stub
		return visitaDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		visitaDao.deleteById(id);
	}

	@Override
	public Visita findByClave(String clave) {
		// TODO Auto-generated method stub
		return visitaDao.findByClave(clave);
	}

}
