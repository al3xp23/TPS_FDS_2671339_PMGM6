package com.proyecto.sena.mgm.service;

import java.util.List;

import com.proyecto.sena.mgm.entity.ProductosEntity;

public interface ProductoService {

	List<ProductosEntity> getAll();
	
	public ProductosEntity findById(Integer id);
	
	public ProductosEntity save(ProductosEntity producto);
	
	public List<ProductosEntity> buscarProducto(Integer idProducto, String nombreProducto);
	
}
