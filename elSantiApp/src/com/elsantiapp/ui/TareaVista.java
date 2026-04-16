package com.elsantiapp.ui;

import java.math.BigDecimal;

import com.elsantiapp.model.Tarea;
import com.elsantiapp.ui.components.ControlesTabla;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TareaVista extends VBox{
	
	private ControlesTabla controles;
	private TableView<Tarea> tabla;
	
	public TareaVista() {
		
		this.controles = new ControlesTabla();
		this.tabla = crearTabla();
		
		this.getChildren().addAll(controles, tabla);
	}
	
	@SuppressWarnings("unchecked")
	private TableView<Tarea> crearTabla(){
		
		TableView<Tarea> tabla = new TableView<>();
		
		TableColumn<Tarea, String> colNombre = new TableColumn<>("Nombre");
		TableColumn<Tarea, String> colDetalle = new TableColumn<>("Detalle");
		TableColumn<Tarea, BigDecimal> colPrecio = new TableColumn<>("Precio");
		TableColumn<Tarea, String> colUnidad = new TableColumn<>("Unidad");
		
		colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
		colDetalle.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDetalle()));
		colPrecio.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getPrecio()));
		colUnidad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUnidad()));
		
		colNombre.getStyleClass().add("columna-tabla");
		colDetalle.getStyleClass().add("columna-tabla");
		colPrecio.getStyleClass().add("columna-tabla");
		colUnidad.getStyleClass().add("columna-tabla");
		
		colPrecio.setCellFactory(col -> new TableCell<Tarea, BigDecimal>() {
			@Override
			protected void updateItem(BigDecimal item, boolean empty) {
				
				if(empty || item == null) {
					
					setText("");
					setStyle("");
				}
				else {
					
					setStyle("-fx-text-fill: #ADD8E6");
					setText("AR$ "+formatearPrecio(item));
				}
				setAlignment(Pos.BASELINE_RIGHT);
			}
		});
		
		colDetalle.setCellFactory(tc -> new TableCell<>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);

		        if (empty || item == null) {
		            setText(null);
		            setTooltip(null);
		        } else {
		            // Recortar texto si es muy largo
		            int maxLength = 50; // puedes ajustar este valor
		            
		            if (item.length() > maxLength) {
		                setText(item.substring(0, maxLength) + "...");
		            } else {
		                setText(item);
		            }

		            // Tooltip con texto completo
		            Tooltip tooltip = new Tooltip(item);
		            tooltip.setWrapText(true);
		            tooltip.setMaxWidth(400);
		            setTooltip(tooltip);
		        }
		    }
		});
		
		tabla.getColumns().addAll(
				
				colNombre,
				colDetalle,
				colPrecio,
				colUnidad
				);
		
		tabla.setPrefWidth(Double.MAX_VALUE);
		
		VBox.setVgrow(tabla, Priority.ALWAYS);
    	
    	tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		
		return tabla;
	}
	
	public String formatearPrecio(BigDecimal valor) {
	    return String.format("%,d", valor.intValue());
	}

	public TableView<Tarea> getTabla() {
		return tabla;
	}

	public void setTabla(TableView<Tarea> tabla) {
		this.tabla = tabla;
	}

	public ControlesTabla getControles() {
		return controles;
	}

	public void setControles(ControlesTabla controles) {
		this.controles = controles;
	}

}
