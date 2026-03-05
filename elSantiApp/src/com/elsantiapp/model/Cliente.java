package com.elsantiapp.model;

import java.time.LocalDate;

public class Cliente {
	
	private int id;
	private LocalDate fechaCreacion;
	private String nombre;
	private String telefono;
	private String email;
	private String localidad;
	private boolean barrioPrivado;
	private String calleBarrio;
	private int alturaLote;
	private String color;
	
	public Cliente(int id, LocalDate fechaCreacion, String nombre, String telefono, String email, String localidad, boolean barrioPrivado, String calleBarrio, int alturaLote, String color) {
		
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.localidad = localidad;
		this.barrioPrivado = barrioPrivado;
		this.calleBarrio = calleBarrio;
		this.alturaLote = alturaLote;
		this.color = color;
	}
	
	public boolean validarCliente() {
		
		int errores = 0;
		if(nombre.isBlank() || nombre.isEmpty()) {
			
			errores++;
		}
		if(fechaCreacion == null) {
			
			errores++;
		}
		if(telefono.isBlank() || telefono.isEmpty() || telefono.matches("\\d{7,15}")) {
			
			errores++;
		}
		return errores == 0;
	}
	
	//GETTERS & SETTERS ///////////////////////////////////////////////////////////////////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public boolean isBarrioPrivado() {
		return barrioPrivado;
	}

	public void setBarrioPrivado(boolean barrioPrivado) {
		this.barrioPrivado = barrioPrivado;
	}

	public String getCalleBarrio() {
		return calleBarrio;
	}

	public void setCalleBarrio(String calleBarrio) {
		this.calleBarrio = calleBarrio;
	}

	public int getAlturaLote() {
		return alturaLote;
	}

	public void setAlturaLote(int alturaLote) {
		this.alturaLote = alturaLote;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	

}
