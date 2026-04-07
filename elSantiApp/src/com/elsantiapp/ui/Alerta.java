package com.elsantiapp.ui;

import com.elsantiapp.ui.components.BarraTitulo;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Alerta extends Stage {
	
	private VBox contenedor;
	private VBox boxMensaje;
	private Button btnAceptar;
	private Label lblMensajeSuperior;
	private Label lblMensajeInferior;
	private boolean aceptado;
	private Scene scene;
	
	public Alerta(String lineaSuperior, String lineaInferior, int tipo) {
		
		this.initStyle(StageStyle.UNDECORATED);
		BarraTitulo barraTitulo = new BarraTitulo("Alerta", false);
		this.aceptado = false;
		this.lblMensajeSuperior = new Label(lineaSuperior);
		this.lblMensajeInferior = new Label(lineaInferior);
		this.boxMensaje = new VBox(5, lblMensajeSuperior, lblMensajeInferior);
		this.btnAceptar = new Button("Aceptar");
		this.contenedor = new VBox(10, barraTitulo, boxMensaje, btnAceptar);
		
		this.scene = new Scene(contenedor, 320, 200);

		scene.getStylesheets().add(getClass().getResource("/com/elsantiapp/css/alerta.css").toExternalForm());
		contenedor.getStyleClass().add("alerta-ventana-mensaje");
		boxMensaje.getStyleClass().add("alerta");
		btnAceptar.getStyleClass().add("alerta-boton");
		
		switch(tipo) {
		case 1 -> fondoExito();
		case 2 -> fondoError();
		default -> fondoAlerte();
		}
		
		btnAceptar.setOnAction(c -> {
			aceptado = true;
			close();
		});
		
		setScene(scene);
		initModality(Modality.APPLICATION_MODAL);
	}
	
	private void fondoAlerte() {
		contenedor.setStyle(
			    "-fx-background-image: url('" + 
			    getClass().getResource("/iconos/señal-alerta.png").toExternalForm() + 
			    "');"
			);
	}
	
	private void fondoError() {
		contenedor.setStyle(
			    "-fx-background-image: url('" + 
			    getClass().getResource("/iconos/señal-error.png").toExternalForm() + 
			    "');"
			);
	}
	
	private void fondoExito() {
		contenedor.setStyle(
			    "-fx-background-image: url('" + 
			    getClass().getResource("/iconos/señal-exito.png").toExternalForm() + 
			    "');"
			);
	}
	
	public boolean isAceptado() {
		
		return aceptado;
	}

}
