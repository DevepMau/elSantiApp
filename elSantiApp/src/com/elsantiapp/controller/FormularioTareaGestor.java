package com.elsantiapp.controller;

import java.math.BigDecimal;

import com.elsantiapp.model.Tarea;
import com.elsantiapp.ui.forms.FormularioTareaVista;

public class FormularioTareaGestor {
	
	private FormularioTareaVista vista;
	private Tarea tareaGenerada;
	
	public FormularioTareaGestor() {
		
		inicializar();
	}
	
	private void inicializar() {
		
		this.vista = new FormularioTareaVista();
		this.setTareaGenerada(null);
		
		vista.getBtnGuardar().setOnAction(c -> {
			if(validarDatos()) {
				
				this.setTareaGenerada(generarTarea());
				cerrar();
			}
		});
	}
	
	public FormularioTareaVista getVista() {
		
		return vista;
	}
	
	public void mostrar() {
		
		vista.showAndWait();
	}
	
	public void cerrar() {
		
		vista.close();
	}
	
	public void cargarDatos(Tarea tarea) {
		
		limpiarDatos();
		
		vista.getTxtNombre().setText(tarea.getNombre());
		vista.getTxtDetalle().setText(tarea.getDetalle());
		vista.getTxtPrecio().setText(tarea.getPrecio() + "");
		vista.getCmbUnidad().setValue(tarea.getUnidad());
	}
	
	public void limpiarDatos() {

		tareaGenerada = null;
		
		vista.getTxtNombre().clear();
		vista.getTxtDetalle().clear();
		vista.getTxtPrecio().clear();
		vista.getCmbUnidad().setValue(vista.getUnidad(0));
	}
	
	private boolean validarDatos() {
		
		String nombre = vista.getTxtNombre().getText();
		String precio = vista.getTxtPrecio().getText();
		
		Boolean condicion1 = !nombre.isEmpty() && !nombre.isBlank();
		Boolean condicion2 = precio.matches("-?\\d+(\\.\\d+)?");
		
		if(!condicion1) {
			
			vista.getLblErrorNombre().setText("El nombre no puede estar vacío.");
			vista.getLblErrorNombre().setVisible(true);
		} else {
			
			vista.getLblErrorNombre().setVisible(false);
		}
		
		if(!condicion2) {
			
			vista.getLblErrorPrecio().setText("Solo numeros enteros");
			vista.getLblErrorPrecio().setVisible(true);
		} else {
			
			vista.getLblErrorPrecio().setVisible(false);
		}
		
		return condicion1 && condicion2;
	}

	private Tarea generarTarea() {
		
		String nombre = vista.getTxtNombre().getText();
		String detalle = vista.getTxtDetalle().getText();
		if(vista.getTxtPrecio().getText().isEmpty() ||
		   vista.getTxtPrecio().getText().isBlank()) {
			
			vista.getTxtPrecio().setText("0");
		}
		BigDecimal precio = parsePrecio(vista.getTxtPrecio().getText());
		String unidad = vista.getCmbUnidad().getValue();
		
		Tarea tareaGenerada = new Tarea(
				0,
				nombre,
				detalle,
				precio,
				unidad
				);
		
		return tareaGenerada;
	}
	
	private static BigDecimal parsePrecio(String texto) {
	    texto = texto.replace(".", "");
	    return new BigDecimal(texto);
	}
	
	public Tarea getTareaGenerada() {
		return tareaGenerada;
	}

	public void setTareaGenerada(Tarea tareaGenerada) {
		this.tareaGenerada = tareaGenerada;
	}

}
