package com.elsantiapp.ui.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PieDePagina extends HBox {
	
	private final String TEXTO = "© 2026 ElSantiApp. Todos los derechos reservados.";
	
	public PieDePagina() {
		
		this.getChildren().add(new Label(TEXTO));
		this.getStyleClass().add("pie-de-pagina");
	}

}
