package com.elsantiapp.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ControlesTabla extends HBox {

    private static final int ICONO_ALTURA = 50;

    private Button btnEliminar;
    private Button btnModificar;
    private Button btnAgregar;
    private Button btnSwap;

    public ControlesTabla() {
    	
    	btnEliminar = new Button();
        btnModificar = new Button();
        btnAgregar = new Button();
        btnSwap = new Button();

        btnEliminar.setGraphic(crearIcono("/iconos/eliminar.png"));
        btnModificar.setGraphic(crearIcono("/iconos/modificar.png"));
        btnAgregar.setGraphic(crearIcono("/iconos/agregar.png"));
        btnSwap.setGraphic(crearIcono("/iconos/swap.png"));

        btnEliminar.setTooltip(new Tooltip("Eliminar"));
        btnModificar.setTooltip(new Tooltip("Modificar"));
        btnAgregar.setTooltip(new Tooltip("Agregar"));
        btnSwap.setTooltip(new Tooltip("Cambiar tabla"));

        HBox boxBotones = new HBox(2, btnSwap, btnEliminar, btnModificar, btnAgregar);

        setAlignment(Pos.CENTER_RIGHT);
        getChildren().add(boxBotones);

        getStylesheets().add(
            getClass().getResource("/com/elsantiapp/css/controles-tabla.css").toExternalForm()
        );

        getStyleClass().add("controles-tabla-contenedor");

        btnEliminar.getStyleClass().add("controles-tabla-boton");
        btnModificar.getStyleClass().add("controles-tabla-boton");
        btnAgregar.getStyleClass().add("controles-tabla-boton");
        btnSwap.getStyleClass().add("controles-tabla-boton");
    }

    private ImageView crearIcono(String ruta) {

        Image img = new Image(getClass().getResource(ruta).toExternalForm());
        ImageView icono = new ImageView(img);

        icono.setFitHeight(ICONO_ALTURA);
        icono.setFitWidth(ICONO_ALTURA);

        return icono;
    }

	public Button getBtnEliminar() {
		return btnEliminar;
	}

	public Button getBtnModificar() {
		return btnModificar;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public Button getBtnSwap() {
		return btnSwap;
	}
}