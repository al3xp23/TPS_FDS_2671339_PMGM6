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
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.sena.mgm.entity.CategoriaEntity;
import com.proyecto.sena.mgm.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(path = "api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/listado")
    public ResponseEntity<List<CategoriaEntity>> consultarTodos(){
        return new ResponseEntity<>(categoriaService.getAll(), HttpStatus.OK);
    }
	
	@GetMapping("/listado/{id}")
    public ResponseEntity<CategoriaEntity> consultarPorId(@PathVariable("id") Integer idProducto){
        return new ResponseEntity<>(categoriaService.findById(idProducto), HttpStatus.OK);
    }
	
	@Operation(summary = "Guardar Categoria", description = "El nombre de la categoria no puede estar vacio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "success", content = {@Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoriaEntity.class)))}),
	        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
	        @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
	        @ApiResponse(responseCode = "500", description = "Internal server error")})
	@PostMapping("/guardar")
    public ResponseEntity<CategoriaEntity> guardar(@RequestBody CategoriaEntity categoria){
        return new ResponseEntity<>(categoriaService.save(categoria), HttpStatus.OK);
    }

}
