module elSantiApp {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	
	opens com.elsantiapp.application to javafx.graphics, javafx.fxml;
}
