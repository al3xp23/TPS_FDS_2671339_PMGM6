package com.proyecto.sena.mgm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.sena.mgm.entity.ProductosEntity;
import com.proyecto.sena.mgm.service.ProductoService;


@RestController
@RequestMapping(path = "api/productos")
@CrossOrigin(origins = "*")
public class ProductosController {
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/listado")
    public ResponseEntity<List<ProductosEntity>> consultarTodos(){
        return new ResponseEntity<>(productoService.getAll(), HttpStatus.OK);
    }
	
	@GetMapping("/listado/{id}")
    public ResponseEntity<ProductosEntity> consultarPorId(@PathVariable("id") Integer idProducto){
        return new ResponseEntity<>(productoService.findById(idProducto), HttpStatus.OK);
    }
	
	@PostMapping("/guardar")
    public ResponseEntity<ProductosEntity> guardar(@RequestBody ProductosEntity producto){
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.OK);
    }
	
	@GetMapping("/buscar")
    public ResponseEntity<List<ProductosEntity>> buscarProducto(@RequestParam(required = false) Integer idProducto, @RequestParam(required = false) String nombreProducto){
        return new ResponseEntity<>(productoService.buscarProducto(idProducto, nombreProducto), HttpStatus.OK);
    }
	

}
