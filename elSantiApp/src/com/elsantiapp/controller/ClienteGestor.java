package com.elsantiapp.controller;

import com.elsantiapp.dao.ClienteDAO;
import com.elsantiapp.model.Cliente;
import com.elsantiapp.ui.Alerta;
import com.elsantiapp.ui.ClienteVista;

import javafx.collections.FXCollections;

public class ClienteGestor {
	
	private ClienteVista vista;
	private ClienteDAO dao;
	private FormularioClienteGestor formulario;
	
	public ClienteGestor(ClienteDAO dao, FormularioClienteGestor formulario) {
		
		this.dao = dao;
		this.formulario = formulario;
		inicializar();
	}

	private void inicializar() {
		
		vista = new ClienteVista();
		cargarTabla();
		
		vista.getControles().getBtnAgregar().setOnAction(c -> agregarCliente());
		vista.getControles().getBtnModificar().setOnAction(c -> modificarCliente());
		vista.getControles().getBtnEliminar().setOnAction(c -> eliminarCliente());
	}
	
	private void cargarTabla() {
		
		vista.getTabla().setItems(FXCollections.observableArrayList(dao.obtenerClientesActivos()));
	}
	
	private void mostrarFormulario() {
		
		formulario.mostrar();
	}
	
	private void mostrarMensaje(String mensaje, int tipo) {

		Alerta alerta = new Alerta(mensaje, "", tipo);
		alerta.showAndWait();
	}
	
	private void agregarCliente() {
		
		formulario.limpiarDatos();
		mostrarFormulario();
		
		Cliente clienteNuevo = formulario.getClienteGenerado();
		
		if(clienteNuevo != null) {
			
			if (clienteValido(clienteNuevo)) {

				dao.agregarCliente(clienteNuevo);
				cargarTabla();
				mostrarMensaje("Cliente guardado correctamente!", 1);
			} else {

				mostrarMensaje("El cliente no es válido!", 2);
			}
		}
		
	}
	
	private void modificarCliente() {
		
		Cliente clienteSeleccionado = vista.getTabla().getSelectionModel().getSelectedItem();
		if(clienteSeleccionado != null) {
			
			formulario.cargarDatos(clienteSeleccionado);
			mostrarFormulario();
			
			Cliente clienteModificado = formulario.getClienteGenerado();
			
			if (clienteModificado != null) {

				clienteModificado.setId(clienteSeleccionado.getId());
				if (clienteValido(clienteModificado)) {

					dao.modificarCliente(clienteModificado);
					cargarTabla();
					mostrarMensaje("Cliente modificado correctamente!", 1);
				} else {

					mostrarMensaje("El cliente no es válido!", 2);
				}
			}
		}
		else {
			
			mostrarMensaje("No se ha seleccionado cliente!", 3);
		}
	}
	
	private void eliminarCliente() {
		
		Cliente clienteSeleccionado = vista.getTabla().getSelectionModel().getSelectedItem();
		if(clienteSeleccionado != null) {
			
			String nombre = clienteSeleccionado.getNombre();
			
			Alerta alertaConfirmacion = new Alerta("¿Desea eliminar el cliente ", nombre+"?",3);
			alertaConfirmacion.showAndWait();
			
			if(alertaConfirmacion.isAceptado()) {
				
				dao.eliminarCliente(clienteSeleccionado.getId());
				cargarTabla();
				mostrarMensaje("Cliente eliminado correctamente!", 1);
			}
		}
		else {
			
			mostrarMensaje("No se ha seleccionado cliente!", 3);
		}
	}
	
	private boolean clienteValido(Cliente cliente) {
		
		int errores = 0;
		if(cliente != null) {
			
			errores += cliente.cantCamposInvalidos();		
		}
		else {
			
			errores++;
		}
		
		return errores == 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public ClienteVista getVista() {
		return vista;
	}
}
