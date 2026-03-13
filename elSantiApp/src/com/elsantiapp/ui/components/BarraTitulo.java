package com.elsantiapp.ui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class BarraTitulo extends HBox {
	
	private Button btnCerrar;
	private Button btnMaximizar;
	private HBox contenedorBotones;
	private Label lblTitulo;
	private Region separador;
	private double xOffset = 0;
    private double yOffset = 0;
	
	public BarraTitulo(String titulo) {
		
		crearComponentes(titulo);
		inicializar();   
	}
	
	private void crearComponentes(String titulo) {
		this.btnCerrar = new Button("X");
		this.btnMaximizar = new Button("⬜");
		this.contenedorBotones = new HBox(btnMaximizar, btnCerrar);
		this.lblTitulo = new Label(titulo);
		this.separador = new Region();
		
		
		this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.getChildren().addAll(lblTitulo, separador, contenedorBotones);
	    HBox.setMargin(lblTitulo, new Insets(8, 10, 0, 10));
	    HBox.setHgrow(separador, Priority.ALWAYS);
	    
	    this.getStylesheets().add(
	    		getClass().getResource("/com/elsantiapp/css/barra-titulo.css").toExternalForm());
	    this.getStyleClass().add("barra-superior");
	    btnCerrar.getStyleClass().add("barra-boton");
	    btnMaximizar.getStyleClass().add("barra-boton");
	    lblTitulo.getStyleClass().add("barra-titulo");
	}
	
	private void inicializar() {
		
		btnCerrar.setOnAction(e -> cerrarVentana());
		btnMaximizar.setOnAction(e -> maximizarVentana());
		
		this.setOnMousePressed(event -> {

	        xOffset = event.getSceneX();
	        yOffset = event.getSceneY();
	    });
	    
	   	this.setOnMouseDragged(event -> {

	        this.getScene().getWindow().setX(event.getScreenX() - xOffset);
	        this.getScene().getWindow().setY(event.getScreenY() - yOffset);
	    });
	}

    private void cerrarVentana() {
    	
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
    
    private void maximizarVentana() {
    	
    	Stage stage = (Stage) this.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

}
