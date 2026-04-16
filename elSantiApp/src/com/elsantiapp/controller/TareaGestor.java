package com.elsantiapp.controller;

import com.elsantiapp.dao.TareaDAO;
import com.elsantiapp.model.Tarea;
import com.elsantiapp.ui.Alerta;
import com.elsantiapp.ui.TareaVista;

import javafx.collections.FXCollections;

public class TareaGestor {
	
	private TareaVista vista;
	private TareaDAO dao;
	private FormularioTareaGestor formulario;
	
	public TareaGestor(TareaDAO dao, FormularioTareaGestor formulario) {

		this.dao = dao;
		this.formulario = formulario;
		inicializar();
	}
	
	public void inicializar() {
		
		this.vista = new TareaVista();
		cargarTabla();
		
		vista.getControles().getBtnAgregar().setOnAction(c -> agregarTarea());
		vista.getControles().getBtnModificar().setOnAction(c -> modificarTarea());
		vista.getControles().getBtnEliminar().setOnAction(c -> eliminarTarea());
	}
	
	private void cargarTabla() {
		
		vista.getTabla().setItems(FXCollections.observableArrayList(dao.obtenerTareasActivas()));		
	}
	
	private void mostrarFormulario() {
		
		formulario.mostrar();
	}
	
	private void mostrarMensaje(String mensaje, int tipo) {
		
		Alerta alerta = new Alerta(mensaje, "", tipo);
		alerta.showAndWait();
	}
	
	private void agregarTarea() {
		
		formulario.limpiarDatos();
		mostrarFormulario();
		
		Tarea tareaNueva = formulario.getTareaGenerada();
		
		if(tareaNueva != null) {
			
			if(tareaValida(tareaNueva)) {
				
				dao.agregarTarea(tareaNueva);
				cargarTabla();
				mostrarMensaje("Tarea guardada correctamente", 1);
			} else {
				
				mostrarMensaje("La tarea no es valida!", 2);
			}
		}
	}
	
	private void modificarTarea() {
		
		Tarea tareaSeleccionada = vista.getTabla().getSelectionModel().getSelectedItem();
		if(tareaSeleccionada != null) {
			
			formulario.cargarDatos(tareaSeleccionada);
			mostrarFormulario();
			
			Tarea tareaModificada = formulario.getTareaGenerada();
			
			if(tareaModificada != null) {
				
				tareaModificada.setId(tareaSeleccionada.getId());
				if(tareaValida(tareaModificada)) {
					
					dao.modificarTarea(tareaModificada);
					cargarTabla();
					mostrarMensaje("Tarea modificada correctamente", 1);
				} else {
					
					mostrarMensaje("La tarea no es valida!", 2);
				}
			}
		}
		else {
			
			mostrarMensaje("No se ha seleccionado tarea!", 2);
		}
	}
	
	private void eliminarTarea() {
		
		Tarea tareaSeleccionada = vista.getTabla().getSelectionModel().getSelectedItem();
		if(tareaSeleccionada != null) {
			
			String nombre = tareaSeleccionada.getNombre();
			
			Alerta alertaConfirmacion = new Alerta("¿Desea eliminar tarea: ", nombre+"?", 3);
			alertaConfirmacion.showAndWait();
			
			if(alertaConfirmacion.isAceptado()) {
				
				dao.eliminarTarea(tareaSeleccionada.getId());
				cargarTabla();
				mostrarMensaje("Tarea eliminada correctamente!", 1);
			}
		}
		else {
			
			mostrarMensaje("No se ha seleccionado tarea!", 2);
		}
	}
	
	private boolean tareaValida(Tarea tarea) {
		
		int errores = 0;
		if(tarea != null) {
			
			errores += tarea.cantCamposInvalidos();
		}
		else {
			
			errores++;
		}
		
		return errores == 0;
	}
	
	public TareaVista getVista() {
		
		return vista;
	}

}
