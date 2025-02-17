module Project1 {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml;
}
