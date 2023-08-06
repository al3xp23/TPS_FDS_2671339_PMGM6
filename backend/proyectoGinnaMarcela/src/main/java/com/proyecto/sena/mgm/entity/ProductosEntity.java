package com.proyecto.sena.mgm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "productos")
public class ProductosEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	@OneToOne
    @JoinColumn(name = "idCategoria")
	private CategoriaEntity idCategoria;
	
	@Column(name = "nombre_producto")
	private String nombreProducto;
	
	@Column(name = "precio_producto")
	private Double precioProducto;
	
	@Column(name = "descripcion_producto")
	private String descripcionProducto;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "referencia")
	private String referencia;
	
	public ProductosEntity(Integer idProducto, CategoriaEntity idCategoria, String nombreProducto, Double precioProducto,
			String descripcionProducto, String marca, String referencia) {
		this.idProducto = idProducto;
		this.idCategoria = idCategoria;
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
		this.descripcionProducto = descripcionProducto;
		this.marca = marca;
		this.referencia = referencia;
	}
	
	public ProductosEntity() {}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public CategoriaEntity getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(CategoriaEntity idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(Double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	

}
