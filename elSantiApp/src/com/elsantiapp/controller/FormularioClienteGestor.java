package com.elsantiapp.controller;

import java.time.LocalDate;

import com.elsantiapp.model.Cliente;
import com.elsantiapp.ui.forms.FormularioClienteVista;

import javafx.scene.paint.Color;

public class FormularioClienteGestor {
	
	private FormularioClienteVista vista;
	private Cliente clienteGenerado;
	
	public FormularioClienteGestor() {
		
		inicializar();
	}
	
	private void inicializar() {
		
		this.vista = new FormularioClienteVista();
		this.setClienteGenerado(null);

		vista.getBtnElegirColor().setOnAction(c -> {
			vista.getColorPicker().show();	
		});
		
		vista.getColorPicker().setOnAction(c -> {
			String hexa = convertirColorEnHexa();
			vista.getBtnElegirColor().setStyle("-fx-background-color: " + hexa + ";");
		});
		
		vista.getBtnGuardar().setOnAction(c -> {
			if(validarDatos()) {
				
				this.setClienteGenerado(generarCliente());
				cerrar();
			}
		});
	}
	
	public FormularioClienteVista getVista() {
		
		return vista;
	}
	
	public void mostrar() {
		
		configurarEventoEsBarrio();
		ejecutarEventoEsBarrio();
		vista.showAndWait();
	}
	
	public void cerrar() {
		
		vista.close();
	}
	
	public void cargarDatos(Cliente cliente) {

		limpiarDatos();
		
		vista.getTxtNombre().setText(cliente.getNombre());
		vista.getTxtTelefono().setText(cliente.getTelefono());
		vista.getTxtEmail().setText(cliente.getEmail());
		vista.getTxtLocalidad().setText(cliente.getLocalidad());
		vista.getTxtDireccion().setText(cliente.getCalleBarrio());
		vista.getChkEsBarrio().setSelected(cliente.isBarrioPrivado());
		if(vista.getChkEsBarrio().isSelected()) {
			
			vista.getTxtBarrio().setText(cliente.getCalleBarrio());
			vista.getTxtLote().setText(String.valueOf(cliente.getAlturaLote()));
		} else {
			
			vista.getTxtDireccion().setText(cliente.getCalleBarrio());
			vista.getTxtAltura().setText(String.valueOf(cliente.getAlturaLote()));
		}
		vista.getColorPicker().setValue(Color.web(cliente.getColor()));
		vista.getBtnElegirColor().setStyle("-fx-background-color: " + convertirColorEnHexa() + ";");
	}
	
	public void limpiarDatos() {
		
		clienteGenerado = null;
		
		vista.getTxtNombre().clear();
		vista.getTxtTelefono().clear();
		vista.getTxtEmail().clear();
		vista.getTxtLocalidad().clear();
		vista.getTxtDireccion().clear();
		vista.getChkEsBarrio().setSelected(false);
		vista.getTxtBarrio().clear();
		vista.getTxtLote().clear();
		vista.getColorPicker().setValue(Color.WHITE);
		vista.getBtnElegirColor().setStyle("-fx-background-color: " + convertirColorEnHexa() + ";");
	}
	
	private void configurarEventoEsBarrio() {
		
		Boolean esBarrio = vista.getChkEsBarrio().isSelected();
		
		vista.getTxtBarrio().setDisable(!esBarrio);
		vista.getTxtLote().setDisable(!esBarrio);
		vista.getTxtDireccion().setDisable(esBarrio);
		vista.getTxtAltura().setDisable(esBarrio);
	}
	
	private void ejecutarEventoEsBarrio() {
		vista.getChkEsBarrio().setOnAction(e -> {
			
			Boolean esBarrio = vista.getChkEsBarrio().isSelected();
	    	
	    	vista.getTxtBarrio().setDisable(!esBarrio);
	    	vista.getTxtLote().setDisable(!esBarrio);
	    	vista.getTxtDireccion().setDisable(esBarrio);
	    	vista.getTxtAltura().setDisable(esBarrio);
	    });
	}
	
	private boolean validarDatos() {
		
		String nombre = vista.getTxtNombre().getText();
		String telefono = vista.getTxtTelefono().getText();
		
		if(nombre.isEmpty()) {
			
			vista.getLblErrorNombre().setText("El nombre no puede estar vacío");
			vista.getLblErrorNombre().setVisible(true);
		} else {

			vista.getLblErrorNombre().setVisible(false);
		}

		return !nombre.isEmpty() && telefonoValido(telefono);
	}
	
	private boolean telefonoValido(String telefono) {
		
		int errores = 0;
		if(telefono.isBlank() || telefono.isEmpty()) {
			
			vista.getLblErrorTelefono().setText("El telefono no puede estar vacío");
			vista.getLblErrorTelefono().setVisible(true);
			errores++;
		}
		else if(!telefono.matches("\\d{8,15}")) {
			
			vista.getLblErrorTelefono().setText("El telefono debe contener entre 7 y 15 dígitos");
			vista.getLblErrorTelefono().setVisible(true);
			errores++;
		}
		else {
			
			vista.getLblErrorTelefono().setVisible(false);
		}
		
		return errores == 0;
	}
	
	private Cliente generarCliente() {

		String nombre = vista.getTxtNombre().getText();
		String telefono = vista.getTxtTelefono().getText();
		String email = vista.getTxtEmail().getText();
		String localidad = vista.getTxtLocalidad().getText();
		Boolean esBarrio = vista.getChkEsBarrio().isSelected();
		
		String calleBarrio;
		String alturaLote;
		
		if(esBarrio) {
			
			calleBarrio = vista.getTxtBarrio().getText();
			alturaLote = vista.getTxtLote().getText();
			
			vista.getTxtDireccion().clear();
			vista.getTxtAltura().clear();
		}
		else {
			
			calleBarrio = vista.getTxtDireccion().getText();
			alturaLote = vista.getTxtAltura().getText();
			
			vista.getTxtBarrio().clear();
			vista.getTxtLote().clear();
		}
		
		if(alturaLote.isEmpty()) { alturaLote = "0"; }
		
		String colorHexa = convertirColorEnHexa();

		Cliente clienteGenerado = new Cliente(
				0, 
				LocalDate.now(), 
				nombre, 
				telefono, 
				email, 
				localidad, 
				esBarrio, 
				calleBarrio, 
				Integer.parseInt(alturaLote), 
				colorHexa
				);

		return clienteGenerado;
	}
	
	private String convertirColorEnHexa() {
		
		Color color = vista.getColorPicker().getValue();

		String hexa = String.format("#%02X%02X%02X",
		    (int)(color.getRed() * 255),
		    (int)(color.getGreen() * 255),
		    (int)(color.getBlue() * 255)
		);
		
		return hexa;
	}
	
	public void setClienteGenerado(Cliente clienteGenerado) {
		this.clienteGenerado = clienteGenerado;
	}

	public Cliente getClienteGenerado() {
		return clienteGenerado;
	}
}
