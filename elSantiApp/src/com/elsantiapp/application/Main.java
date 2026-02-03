package com.elsantiapp.application;
	
import com.elsantiapp.controller.BarraTituloController;
import com.elsantiapp.db.InicializadorDB;
import com.elsantiapp.view.BarraTituloView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
    	
    	InicializadorDB.inicializar();
        stage.initStyle(StageStyle.UNDECORATED);

        // Logo
        Image logo = new Image(getClass().getResource("/logo/logo.png").toExternalForm());
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        // Botones del menú lateral
        Button btnTablas = new Button("Tablas");
        Button btnCronograma = new Button("Cronograma");
        Button btnReportes = new Button("Reportes");
        Button btnKanban = new Button("Tablero");

        Button[] botones = {btnTablas, btnCronograma, btnReportes, btnKanban};
        
        BarraTituloView barraTituloView = new BarraTituloView();
        BarraTituloController barraTitulo = new BarraTituloController("ElSantiApp v1", barraTituloView);

        // Clase común para todos los botones
        for (Button btn : botones) {
            btn.getStyleClass().add("menu-boton");
        }

        VBox menuLateral = new VBox(1, logoView, btnTablas, btnCronograma, btnReportes, btnKanban);
        menuLateral.getStyleClass().add("menu-lateral");

        // Panel principal
        BorderPane root = new BorderPane();
        root.setTop(barraTitulo.getView());
        root.setLeft(menuLateral);
        root.getStyleClass().add("panel");

        // Función para manejar selección de botón
        for (Button btn : botones) {
            btn.setOnAction(e -> {
                // Quitar estado "activo" de todos
                for (Button b : botones) b.getStyleClass().remove("activo");
                // Marcar el botón clickeado como activo
                btn.getStyleClass().add("activo");

                // Cambiar el contenido central
                if (btn == btnTablas) root.setCenter(new javafx.scene.control.Label("Vista de Tablas"));
                else if (btn == btnCronograma) root.setCenter(new javafx.scene.control.Label("Vista de Cronograma"));
                else if (btn == btnReportes) root.setCenter(new javafx.scene.control.Label("Vista de Reportes"));
                else if (btn == btnKanban) root.setCenter(new javafx.scene.control.Label("Vista de Tablero"));
            });
        }

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/elsantiapp/css/global.css").toExternalForm());
        stage.setTitle("Sistema de Gestión");
        stage.setScene(scene);
        stage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
