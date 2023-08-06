package com.proyecto.sena.mgm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.sena.mgm.entity.ProductosEntity;

@Repository
public interface Productosrepository extends JpaRepository<ProductosEntity, Integer>{
	
	@Query(value = "select * from productos where nombre_producto like %:nombre%", 
			nativeQuery = true)
	List<ProductosEntity> findByNombreProducto(@Param("nombre") String nombreProducto);
	
	@Query(value = "select * from productos where nombre_producto like %:nombre% and id_producto = :id", 
			nativeQuery = true)
	List<ProductosEntity> findByNombreProductoAndIdProducto(@Param("nombre") String nombreProducto, @Param("id") Integer idProducto);

}
