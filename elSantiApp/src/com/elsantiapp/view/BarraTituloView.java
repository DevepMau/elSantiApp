package com.elsantiapp.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class BarraTituloView extends HBox {
	
	private Button btnCerrar;
	private Button btnMaximizar;
	private HBox contenedorBotones;
	private Label lblTitulo;
	private Region separador;
	
	public BarraTituloView() {
		
		this.btnCerrar = new Button("X");
		this.btnMaximizar = new Button("⬜");
		this.contenedorBotones = new HBox(btnMaximizar, btnCerrar);
		this.lblTitulo = new Label();
		this.separador = new Region();
		
		
		this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.getChildren().addAll(lblTitulo, separador, contenedorBotones);
	    HBox.setMargin(lblTitulo, new Insets(8, 10, 0, 10));
	    HBox.setHgrow(separador, Priority.ALWAYS);
	    
	    this.getStylesheets().add(
	    		getClass().getResource("/com/elsantiapp/css/barraTitulo.css").toExternalForm());
	    this.getStyleClass().add("barra-superior");
	    btnCerrar.getStyleClass().add("barra-boton");
	    btnMaximizar.getStyleClass().add("barra-boton");
	    lblTitulo.getStyleClass().add("barra-titulo");
	}

	public Button getBtnCerrar() {
		return btnCerrar;
	}

	public Button getBtnMaximizar() {
		return btnMaximizar;
	}

	public Label getLblTitulo() {
		return lblTitulo;
	}
}
