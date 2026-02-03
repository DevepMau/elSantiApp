module elSantiApp {
	requires javafx.controls;
	
	opens com.elsantiapp.application to javafx.graphics, javafx.fxml;
}
