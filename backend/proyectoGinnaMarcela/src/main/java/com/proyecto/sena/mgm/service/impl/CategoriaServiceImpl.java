package com.proyecto.sena.mgm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.sena.mgm.entity.CategoriaEntity;
import com.proyecto.sena.mgm.repository.CategoriaRepository;
import com.proyecto.sena.mgm.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<CategoriaEntity> getAll() {
		return categoriaRepository.findAll();
	}
	
	public CategoriaEntity findById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	public CategoriaEntity save(CategoriaEntity producto) {
		return categoriaRepository.save(producto);
	}

	public void delete(Integer id) {
		categoriaRepository.deleteById(id);
	}
	
}
