package com.proyecto.sena.mgm.service;

import java.util.List;

import com.proyecto.sena.mgm.entity.CategoriaEntity;

public interface CategoriaService {

	List<CategoriaEntity> getAll();
	
	public CategoriaEntity findById(Integer id);
	
	public CategoriaEntity save(CategoriaEntity producto);
	
}
