package com.elsantiapp.ui.forms;

import com.elsantiapp.ui.components.BarraTitulo;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FormularioClienteVista extends Stage {
	
	private GridPane formulario;
	private VBox contenedor;
	private Scene scene;
	private Button btnGuardar;
	private Button btnElegirColor;
	
	private TextField txtNombre;
	private TextField txtTelefono;
	private TextField txtEmail;
	private TextField txtLocalidad;
	private TextField txtBarrio;
	private TextField txtLote;
	private TextField txtDireccion;
	private TextField txtAltura;
	private CheckBox chkEsBarrio;
	private ColorPicker colorPicker;
	
	private Label lblErrorNombre;
	private Label lblErrorTelefono;
	
	public FormularioClienteVista() {
		
		this.initStyle(StageStyle.UNDECORATED);
		BarraTitulo barraTitulo = new BarraTitulo("Formulario de Clientes", false);
		this.btnElegirColor = new Button();
		this.btnElegirColor.getStyleClass().add("formulario-boton-color");
		this.btnElegirColor.setTooltip(new Tooltip("Elegir color para el cliente"));
		this.formulario = crearFormulario();
		this.btnGuardar = new Button("Guardar");
		this.btnGuardar.getStyleClass().add("formulario-boton");
		this.contenedor = new  VBox(3, barraTitulo, colorPicker, formulario, btnGuardar);
		this.scene = new Scene(contenedor, 400, 580);
		
		scene.getStylesheets().add(getClass().getResource("/com/elsantiapp/css/global.css").toExternalForm());
		contenedor.getStyleClass().add("contenedor-ventana");
		
		setScene(scene);
		initModality(Modality.APPLICATION_MODAL);
	}
	
	private GridPane crearFormulario() {
		
		GridPane formulario = new GridPane();
		
		txtNombre = new TextField();
		txtNombre.setPromptText("Nombre completo");
		
		txtTelefono = new TextField();
		txtTelefono.setPromptText("Telefono");
		
		txtEmail = new TextField();
		txtEmail.setPromptText("Correo electronico");
		
		txtLocalidad = new TextField();
		txtLocalidad.setPromptText("Localidad");
		
		chkEsBarrio = new CheckBox();
		txtBarrio = new TextField();
		txtBarrio.setPromptText("Nombre de Barrio");
		
		txtLote = new TextField();
		txtLote.setPromptText("Lote");
		txtLote.setMaxWidth(200);
		
		txtDireccion = new TextField();
		txtDireccion.setPromptText("Direccion/Calle");
		
		txtAltura = new TextField();
		txtAltura.setPromptText("Altura");
		txtAltura.setMaxWidth(200);
		
		colorPicker = new ColorPicker();
		colorPicker.setVisible(false);
		
		HBox boxNombreColor = new HBox(10, txtNombre, btnElegirColor);
		HBox boxDireccion = new HBox(2, txtDireccion, txtAltura);
		HBox boxDatoBarrio = new HBox(2, txtBarrio, txtLote);
		HBox boxEsBarrio = new HBox(10, new Label("¿Barrio Privado?"), chkEsBarrio);
		boxEsBarrio.getStyleClass().add("box-chk");
		
		VBox boxBarrio = new VBox(10, boxEsBarrio, boxDatoBarrio);
		boxBarrio.getStyleClass().add("box-dato-contenedor");
		
		lblErrorNombre = new Label("Error de nombre");
		lblErrorNombre.getStyleClass().add("label-error");
		lblErrorNombre.setVisible(false);
		
		lblErrorTelefono = new Label("Error de telefono");
		lblErrorTelefono .getStyleClass().add("label-error");
		lblErrorTelefono.setVisible(false);
		
		formulario.setVgap(20);
		
		formulario.addRow(0, lblErrorNombre);
		formulario.addRow(1, boxNombreColor);
		formulario.addRow(2, lblErrorTelefono);
		formulario.addRow(3, txtTelefono);
		formulario.addRow(4, txtEmail);
		formulario.addRow(5, txtLocalidad);
		formulario.addRow(6, boxBarrio);
		formulario.addRow(7, boxDireccion);
		
		formulario.getStyleClass().add("formulario");
		
		GridPane.setMargin(lblErrorNombre, new Insets(0, 0, -18, 10));
		GridPane.setMargin(boxNombreColor, new Insets(0, 0, -18, 0));
		GridPane.setMargin(lblErrorTelefono, new Insets(0, 0, -18, 10));
		
		return formulario;
	}

	public Button getBtnGuardar() {
		return btnGuardar;
	}
	
	public Button getBtnElegirColor() {
		return btnElegirColor;
	}

	public TextField getTxtNombre() {
		return txtNombre;
	}

	public TextField getTxtTelefono() {
		return txtTelefono;
	}

	public TextField getTxtEmail() {
		return txtEmail;
	}

	public TextField getTxtLocalidad() {
		return txtLocalidad;
	}

	public TextField getTxtBarrio() {
		return txtBarrio;
	}

	public TextField getTxtLote() {
		return txtLote;
	}

	public TextField getTxtDireccion() {
		return txtDireccion;
	}

	public TextField getTxtAltura() {
		return txtAltura;
	}

	public CheckBox getChkEsBarrio() {
		return chkEsBarrio;
	}

	public ColorPicker getColorPicker() {
		return colorPicker;
	}

	public Label getLblErrorNombre() {
		return lblErrorNombre;
	}

	public Label getLblErrorTelefono() {
		return lblErrorTelefono;
	}
}
