package com.elsantiapp.ui;

import java.time.LocalDate;

import com.elsantiapp.model.Cliente;
import com.elsantiapp.ui.components.ControlesTabla;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ClienteVista extends VBox {
	
	private ControlesTabla controles;
	private TableView<Cliente> tabla;

	public ClienteVista() {
		
		this.controles = new ControlesTabla();
		this.tabla = crearTabla();
		
		this.getChildren().addAll(controles, tabla);
	}
	
	@SuppressWarnings("unchecked")
	private TableView<Cliente> crearTabla() {
		
		TableView<Cliente> tabla = new TableView<>();
		
		TableColumn<Cliente, LocalDate> colFechaCreacion = new TableColumn<>("Fecha de\nRegistro");
        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Cliente, String> colTelefono = new TableColumn<>("Teléfono");
        TableColumn<Cliente, String> colEmail = new TableColumn<>("Email");
        TableColumn<Cliente, String> colLocalidad = new TableColumn<>("Localidad");
        TableColumn<Cliente, Boolean> colBarrioPrivado = new TableColumn<>("Barrio\nPrivado");
        TableColumn<Cliente, String> colDireccion = new TableColumn<>("Direccion");
        TableColumn<Cliente, Number> colAlturaLote = new TableColumn<>("Altura\nLote");
        
        colFechaCreacion.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getFechaCreacion()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colTelefono.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
        colEmail.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmail()));
        colLocalidad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLocalidad()));
        colBarrioPrivado.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().isBarrioPrivado()));
        colAlturaLote.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getAlturaLote()));
        colDireccion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCalleBarrio()));
        
        colFechaCreacion.getStyleClass().add("columna-tabla");
        colNombre.getStyleClass().add("columna-tabla");
        colTelefono.getStyleClass().add("columna-tabla");
        colLocalidad.getStyleClass().add("columna-tabla");
        colDireccion.getStyleClass().add("columna-tabla");
        colEmail.getStyleClass().add("columna-tabla");
        colBarrioPrivado.getStyleClass().add("columna-tabla");
        colAlturaLote.getStyleClass().add("columna-tabla");
        
        colNombre.setCellFactory(col -> new TableCell<Cliente, String>(){
        	
        	protected void updateItem(String item, boolean empty) {
        		
        		super.updateItem(item, empty);
        		if(empty || item == null) {
        			
        			setText(null);
        			setStyle("");
        		} else {
        			Cliente cliente = getTableRow().getItem();
               	 	if(cliente != null) {
               	 		
               	 		String color = cliente.getColor();
               	 		setText(item);
               	 		setStyle("-fx-text-fill: "+color+"; -fx-font-weight: bold;");
               	 	}
        		}
        	}
        });
        
        colBarrioPrivado.setCellFactory(col -> new TableCell<Cliente, Boolean>() {
        	
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    if (item) {
                        setText("Si");
                        setStyle("-fx-text-fill: #00ff00; -fx-padding: 0 0 -1 0;");
                    } else {
                        setText("No");
                        setStyle("-fx-text-fill: red; -fx-padding: 0 0 -1 0;");
                    }
                }
                setAlignment(Pos.CENTER);
            }
        });

        tabla.getColumns().addAll(
        		
        	    colFechaCreacion,
        	    colNombre,
        	    colTelefono,
        	    colEmail,
        	    colBarrioPrivado,
        	    colLocalidad,
        	    colDireccion,
        	    colAlturaLote
        	);

        
    	tabla.setPrefWidth(Double.MAX_VALUE);
   
    	VBox.setVgrow(tabla, Priority.ALWAYS);
    	
    	tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		
		return tabla;
	}

	public TableView<Cliente> getTabla() {
		return tabla;
	}

	public void setTabla(TableView<Cliente> tabla) {
		this.tabla = tabla;
	}

	public ControlesTabla getControles() {
		return controles;
	}

	public void setControles(ControlesTabla controles) {
		this.controles = controles;
	}
}
