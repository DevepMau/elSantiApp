package com.elsantiapp.ui.forms;

import com.elsantiapp.ui.components.BarraTitulo;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FormularioTareaVista extends Stage {
	
	private final String[] UNIDADES = {"N/A", "Mensual", "Quincenal", "Semanal", "Diario", "Hora", "Unidad", "Paquete"};
	
	private GridPane formulario;
	private VBox contenedor;
	private Scene scene;
	private Button btnGuardar;
	
	private TextField txtNombre;
	private TextArea txtDetalle;
	private TextField txtPrecio;
	private ComboBox<String> cmbUnidad;
	
	private Label lblErrorNombre;
	private Label lblErrorPrecio;
	
	public FormularioTareaVista() {
		
		this.initStyle(StageStyle.UNDECORATED);
		BarraTitulo barraTitulo = new BarraTitulo("Formulario de tarea", false);
		this.formulario = crearFormulario();
		this.btnGuardar = new Button("Guardar");
		this.btnGuardar.getStyleClass().add("formulario-boton");
		this.contenedor = new VBox(3, barraTitulo, formulario, btnGuardar);
		this.scene = new Scene(contenedor, 400, 500);
		
		scene.getStylesheets().add(getClass().getResource("/com/elsantiapp/css/global.css").toExternalForm());
		contenedor.getStyleClass().add("contenedor-ventana");
		
		setScene(scene);
		initModality(Modality.APPLICATION_MODAL);
	}
	
	private GridPane crearFormulario() {
		
		GridPane formulario = new GridPane();
		
		txtNombre = new TextField();
		txtNombre.setPromptText("Nombre de la tarea");
		
		txtDetalle = new TextArea();
		txtDetalle.setPromptText("¿Que se hace?");
		txtDetalle.setWrapText(true);
		
		txtPrecio = new TextField();
		txtPrecio.setPromptText("Precio de la tarea");
		
		cmbUnidad = new ComboBox<>();
		cmbUnidad.getItems().addAll(UNIDADES);
		
		Label x = new Label("X");
		x.setStyle("-fx-text-fill: white; -fx-padding: 15 0 0 0;");
		
		HBox boxPrecioUnidad = new HBox(10, txtPrecio, x, cmbUnidad);
		
		lblErrorNombre = new Label("");
		lblErrorNombre.getStyleClass().add("label-error");
		lblErrorNombre.setVisible(false);
		
		lblErrorPrecio = new Label("");
		lblErrorPrecio.getStyleClass().add("label-error");
		lblErrorPrecio.setVisible(false);
		
		formulario.setVgap(20);
		
		formulario.addRow(0, lblErrorNombre);
		formulario.addRow(1, txtNombre);
		formulario.addRow(2, txtDetalle);
		formulario.addRow(3, lblErrorPrecio);
		formulario.addRow(4, boxPrecioUnidad);
		
		formulario.getStyleClass().add("formulario");
		
		GridPane.setMargin(lblErrorNombre, new Insets(0, 0, -18, 10));
		GridPane.setMargin(txtDetalle, new Insets(0, 0, -2, 0));
		GridPane.setMargin(lblErrorPrecio, new Insets(0, 0, -18, 10));
		
		return formulario;
	}

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public TextField getTxtNombre() {
		return txtNombre;
	}

	public TextArea getTxtDetalle() {
		return txtDetalle;
	}

	public TextField getTxtPrecio() {
		return txtPrecio;
	}

	public ComboBox<String> getCmbUnidad() {
		return cmbUnidad;
	}

	public Label getLblErrorNombre() {
		return lblErrorNombre;
	}
	
	public Label getLblErrorPrecio() {
		return lblErrorPrecio;
	}

	public void setBtnGuardar(Button btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public void setTxtNombre(TextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void setTxtDetalle(TextArea txtDetalle) {
		this.txtDetalle = txtDetalle;
	}

	public void setTxtPrecio(TextField txtPrecio) {
		this.txtPrecio = txtPrecio;
	}

	public void setCmbUnidad(ComboBox<String> cmbUnidad) {
		this.cmbUnidad = cmbUnidad;
	}

	public void setLblErrorNombre(Label lblErrorNombre) {
		this.lblErrorNombre = lblErrorNombre;
	}
	
	public void setLblErrorPrecio(Label lblErrorPrecio) {
		this.lblErrorPrecio = lblErrorPrecio;
	}
	
	public String getUnidad(int index) {
		
		int indexSolicitado = index;
		int indexMax = UNIDADES.length - 1;
		if(indexSolicitado > indexMax) {
			
			indexSolicitado = 0;
		}
		return UNIDADES[indexSolicitado];
	}

}
