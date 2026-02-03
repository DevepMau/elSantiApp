package com.elsantiapp.controller;

import com.elsantiapp.view.BarraTituloView;

import javafx.stage.Stage;

public class BarraTituloController {
	
	private double xOffset = 0;
    private double yOffset = 0;
	private BarraTituloView view;
	private String titulo;
	
	public BarraTituloController(String titulo, BarraTituloView view) {
		
		this.view = view;
		this.titulo = titulo;
		inicializar();
	}
	
	public BarraTituloView getView() {
		
		return view;
	}
	
	private void inicializar() {
		
		view.getBtnCerrar().setOnAction(e -> cerrarVentana());
		view.getBtnMaximizar().setOnAction(e -> maximizarVentana());
		view.getLblTitulo().setText(titulo);
		
		view.setOnMousePressed(event -> {

	        xOffset = event.getSceneX();
	        yOffset = event.getSceneY();
	    });
	    
	   	view.setOnMouseDragged(event -> {

	        view.getScene().getWindow().setX(event.getScreenX() - xOffset);
	        view.getScene().getWindow().setY(event.getScreenY() - yOffset);
	    });
	}

    private void cerrarVentana() {
    	
        Stage stage = (Stage) view.getScene().getWindow();
        stage.close();
    }
    
    private void maximizarVentana() {
    	
    	Stage stage = (Stage) view.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }
}
