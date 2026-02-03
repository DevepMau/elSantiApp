module elSantiApp {
	requires javafx.controls;
	requires java.sql;
	
	opens com.elsantiapp.application to javafx.graphics, javafx.fxml;
}
