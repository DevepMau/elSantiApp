package com.elsantiapp.application;
	
import javafx.util.Duration;

import com.elsantiapp.controller.BarraTituloController;
import com.elsantiapp.db.InicializadorDB;
import com.elsantiapp.view.BarraTituloView;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	VBox menuTablas;
	boolean abierto = false;

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
        Button btnClientes = new Button("Clientes");
        Button btnTrabajos = new Button("Trabajos");
        Button btnServicios = new Button("Servicios");
        
        menuTablas = new VBox(1, btnClientes, btnTrabajos, btnServicios);
        menuTablas.setVisible(false);
        menuTablas.setManaged(false);
        menuTablas.setOpacity(0);
        menuTablas.setScaleY(0);
        
        Button btnCronograma = new Button("Cronograma");
        Button btnReportes = new Button("Reportes");
        Button btnKanban = new Button("Tablero");

        Button[] botones = {btnTablas, btnCronograma, btnReportes, btnKanban};
        Button[] botonesSubMenu = {btnClientes, btnTrabajos, btnServicios};
        
        BarraTituloView barraTituloView = new BarraTituloView();
        BarraTituloController barraTitulo = new BarraTituloController("ElSantiApp v1", barraTituloView);

        for (Button btn : botones) {
            btn.getStyleClass().add("menu-boton");
        }
        
		for (Button btn : botonesSubMenu) {
			btn.getStyleClass().add("submenu-boton");
		}

        VBox menuLateral = new VBox(1, logoView, btnTablas, menuTablas, btnCronograma, btnReportes, btnKanban);
        menuLateral.getStyleClass().add("menu-lateral");

        // Panel principal
        BorderPane root = new BorderPane();
        root.setTop(barraTitulo.getView());
        root.setLeft(menuLateral);
        root.getStyleClass().add("panel");

        for (Button btn : botones) {
        	
            btn.setOnAction(e -> {
            	
            	for (Button b : botonesSubMenu) b.getStyleClass().remove("activo-submenu");
      
            	if(btn != btnTablas) {
            		cerrar(menuTablas);
            		abierto = false;
            	}
            	
                for (Button b : botones) b.getStyleClass().remove("activo");
                btn.getStyleClass().add("activo");

                if (btn == btnTablas) menuTablas();
                else if (btn == btnCronograma) root.setCenter(new javafx.scene.control.Label("Vista de Cronograma"));
                else if (btn == btnReportes) root.setCenter(new javafx.scene.control.Label("Vista de Reportes"));
                else if (btn == btnKanban) root.setCenter(new javafx.scene.control.Label("Vista de Tablero"));
            });
        }
        
        for (Button btn : botonesSubMenu) {
        	
        	btn.setOnAction(e -> {
        		
        		for(Button b : botonesSubMenu) b.getStyleClass().remove("activo-submenu");
        		btn.getStyleClass().add("activo-submenu");
        		
				if (btn == btnClientes) root.setCenter(new javafx.scene.control.Label("Vista de Clientes"));
				else if (btn == btnTrabajos) root.setCenter(new javafx.scene.control.Label("Vista de Trabajos"));
				else if (btn == btnServicios) root.setCenter(new javafx.scene.control.Label("Vista de Servicios"));
        	});
        }

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/elsantiapp/css/global.css").toExternalForm());
        stage.setTitle("Sistema de Gestión");
        stage.setScene(scene);
        stage.show();
    }
    
    private void menuTablas() {
    	abierto = !abierto;
    	if(abierto) {
    		abrir(menuTablas);
    	}
    	else {
    		cerrar(menuTablas);
    	}
    }
    
    private void abrir(VBox box) {
        box.setManaged(true);
        box.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.millis(250), box);
        fade.setFromValue(0);
        fade.setToValue(1);

        ScaleTransition scale = new ScaleTransition(Duration.millis(250), box);
        scale.setFromY(1);
        scale.setToY(1);

        new ParallelTransition(fade, scale).play();
    }

    private void cerrar(VBox box) {
        FadeTransition fade = new FadeTransition(Duration.millis(100), box);
        fade.setFromValue(1);
        fade.setToValue(0);

        ScaleTransition scale = new ScaleTransition(Duration.millis(100), box);
        scale.setFromY(0);
        scale.setToY(0);

        ParallelTransition anim = new ParallelTransition(fade, scale);
        anim.setOnFinished(e -> {
            box.setVisible(false);
            box.setManaged(false);
        });
        anim.play();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
