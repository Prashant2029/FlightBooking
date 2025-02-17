package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main class for the Flight Booking application.
 * This class is responsible for launching the application and setting up the primary stage.
 */
public class Main extends Application {

    /**
     * Starts the application by loading the initial FXML view (LoginPage.fxml)
     * and setting up the primary stage.
     *
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml")); // Load the login page
            Scene scene = new Scene(root, 850, 520); // Set initial scene size
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // Load CSS

            primaryStage.setTitle("Flight Booking"); // Set application title

            // Set application icon. Make sure the path "/Logo.jpg" is correct and the image exists.
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Logo.jpg")));

            primaryStage.setResizable(false); // Prevent window resizing (optional)
            primaryStage.setScene(scene);
            primaryStage.show(); // Display the stage
        } catch (Exception e) {
            e.printStackTrace(); // In a real application, handle this exception more gracefully. Log it or show an error message.
        }
    }

    /**
     * The main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}