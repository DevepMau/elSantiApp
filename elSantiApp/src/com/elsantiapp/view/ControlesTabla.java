package com.elsantiapp.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ControlesTabla extends HBox {
	
	private final int ICONO_ALTURA = 50;
	
	private HBox boxBotones;
	private Button btnEliminar;
	private Button btnModificar;
	private Button btnAgregar;
	private Button btnSwap;
	private Image eliminar;
	private Image modificar;
	private Image agregar;
	private Image swap;
	private ImageView iconoEliminar;
	private ImageView iconoModificar;
	private ImageView iconoAgregar;
	private ImageView iconoSwap;
	private Tooltip ttEliminar;
	private Tooltip ttModificar;
	private Tooltip ttAgregar;
	private Tooltip ttSwap;
	
	public ControlesTabla() {
		
		this.btnEliminar = new Button();
		this.btnModificar = new Button();
		this.btnAgregar = new Button();
		this.btnSwap = new Button();
		this.boxBotones = new HBox(2, btnSwap, btnEliminar, btnModificar, btnAgregar);
		this.eliminar = new Image(getClass().getResource("/iconos/eliminar.png").toExternalForm());
		this.modificar = new Image(getClass().getResource("/iconos/modificar.png").toExternalForm());
		this.agregar = new Image(getClass().getResource("/iconos/agregar.png").toExternalForm());
		this.swap = new Image(getClass().getResource("/iconos/swap.png").toExternalForm());
		this.iconoEliminar = new ImageView(eliminar);
		this.iconoModificar = new ImageView(modificar);
		this.iconoAgregar = new ImageView(agregar);
		this.iconoSwap = new ImageView(swap);
		this.ttEliminar = new Tooltip("Eliminar");
		this.ttModificar = new Tooltip("Modificar");
		this.ttAgregar = new Tooltip("Agregar");
		this.ttSwap = new Tooltip("Cambiar tabla");
		
		this.iconoEliminar.setFitHeight(ICONO_ALTURA);
		this.iconoEliminar.setFitWidth(ICONO_ALTURA);
		this.iconoModificar.setFitHeight(ICONO_ALTURA);
		this.iconoModificar.setFitWidth(ICONO_ALTURA);
		this.iconoAgregar.setFitHeight(ICONO_ALTURA);
		this.iconoAgregar.setFitWidth(ICONO_ALTURA);
		this.iconoSwap.setFitHeight(ICONO_ALTURA);
		this.iconoSwap.setFitWidth(ICONO_ALTURA);
		
		btnEliminar.setGraphic(iconoEliminar);
		btnModificar.setGraphic(iconoModificar);
		btnAgregar.setGraphic(iconoAgregar);
		btnSwap.setGraphic(iconoSwap);
		
		btnEliminar.setTooltip(ttEliminar);
		btnModificar.setTooltip(ttModificar);
		btnAgregar.setTooltip(ttAgregar);
		btnSwap.setTooltip(ttSwap);
		
		this.setAlignment(Pos.CENTER_RIGHT);
		
		this.getChildren().add(boxBotones);
		
		this.getStylesheets().add(
	    		getClass().getResource("/com/elsantiapp/css/controles-tabla.css").toExternalForm());
		this.getStyleClass().add("controles-tabla-contenedor");
		btnEliminar.getStyleClass().add("controles-tabla-boton");
		btnModificar.getStyleClass().add("controles-tabla-boton");
		btnAgregar.getStyleClass().add("controles-tabla-boton");
		btnSwap.getStyleClass().add("controles-tabla-boton");
	}
	

}
