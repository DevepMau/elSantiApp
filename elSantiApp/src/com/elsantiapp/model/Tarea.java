package com.elsantiapp.model;

import java.math.BigDecimal;

public class Tarea {
	
	private int id;
	private String nombre;
	private String detalle;
	private BigDecimal precio;
	private String unidad;
	private boolean activo;
	
	public Tarea(int id, String nombre, String detalle, BigDecimal precio, String unidad) {
		
		this.id = id;
		this.nombre = nombre;
		this.detalle = detalle;
		this.precio = precio;
		this.unidad = unidad;
		this.activo = true;
	}
	
	public int cantCamposInvalidos() {
		
		int errores = 0;
		if(nombre.isBlank() || nombre.isEmpty()) {
			
			System.out.println("error de nombre");
			errores++;
		}
		
		return errores;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public String getUnidad() {
		return unidad;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
