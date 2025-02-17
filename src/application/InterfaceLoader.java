package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

import application.BookGui; // Make sure this import is correct
import application.CancelGui; // Make sure this import is correct


/**
 * Abstract base class for controllers that load FXML views.
 * Provides a generic method for loading and switching to different scenes.
 * Also includes a method for creating flight panes.
 */
public abstract class InterfaceLoader extends Application {

    /**
     * Loads and switches to a new scene based on the provided FXML file.
     *
     * @param event     The ActionEvent triggering the scene switch (e.g., a button click).
     * @param fxmlFile  The path to the FXML file representing the new scene.
     */
    public void loadPage(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 850, 520)); // Set scene size here for consistency
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // In a real application, handle this exception more gracefully. Log it or show an error message.
        }
    }

    /**
     * Creates an AnchorPane representing a single flight with booking and cancellation buttons.
     *
     * @param flightNumber The flight number.
     * @param origin       The origin city.
     * @param destination  The destination city.
     * @param date         The departure date.
     * @return An AnchorPane containing the flight information and buttons.
     * @throws IOException If there is an error loading resources (e.g., the image).
     */
    protected AnchorPane createFlightPane(String flightNumber, String origin, String destination, String date) throws IOException {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(506, 150);
        anchorPane.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white; -fx-padding: 10px;");

        String imagePath = "/shit.png"; // Path relative to resources.  Make sure this path is correct!
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);
        imageView.setLayoutX(10);
        imageView.setLayoutY(20);

        Label flightNumberLabel = new Label(flightNumber);
        flightNumberLabel.setLayoutX(250);
        flightNumberLabel.setLayoutY(21);
        flightNumberLabel.setFont(new Font("Courier New", 20));

        Label routeLabel = new Label("Origin: " + origin);
        routeLabel.setLayoutX(200);
        routeLabel.setLayoutY(70);
        routeLabel.setFont(new Font("Arial", 16));

        Label routeLabel1 = new Label("Destination: " + destination);
        routeLabel1.setLayoutX(200);
        routeLabel1.setLayoutY(100);
        routeLabel1.setFont(new Font("Arial", 16));

        Label priceLabel = new Label("Rs. " + "40000"); // Make price dynamic if possible
        priceLabel.setLayoutX(200);
        priceLabel.setLayoutY(130);
        priceLabel.setFont(new Font("Arial", 16));

        Label dateLabel = new Label("Date: " + date);
        dateLabel.setLayoutX(200);
        dateLabel.setLayoutY(160);
        dateLabel.setFont(new Font("Arial", 16));

        Button bookNowBtn = new Button("Book Now");
        bookNowBtn.setLayoutX(400);
        bookNowBtn.setLayoutY(60);
        bookNowBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        bookNowBtn.setFont(new Font("Arial", 16));
        bookNowBtn.setOnAction(event -> handleBookNow(event, flightNumber)); // Call a separate method

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setLayoutX(400);
        cancelBtn.setLayoutY(100);
        cancelBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        cancelBtn.setFont(new Font("Arial", 16));
        cancelBtn.setOnAction(event -> handleCancel(event, flightNumber)); // Call a separate method


        anchorPane.getChildren().addAll(imageView, flightNumberLabel, routeLabel, routeLabel1, priceLabel, dateLabel, bookNowBtn, cancelBtn);

        return anchorPane;
    }

    /**
     * Handles the "Book Now" button click.
     *
     * @param event        The ActionEvent triggered by the button click.
     * @param flightNumber The flight number associated with the button.
     */
    private void handleBookNow(ActionEvent event, String flightNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/BookFlight.fxml"));
            Parent root = loader.load();

            BookGui bookGui = loader.getController();
            bookGui.setFlightNumber(flightNumber);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 850, 520)); // Consistent scene size
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

    /**
     * Handles the "Cancel" button click.
     *
     * @param event        The ActionEvent triggered by the button click.
     * @param flightNumber The flight number associated with the button.
     */
    private void handleCancel(ActionEvent event, String flightNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CancelFlight.fxml"));
            Parent root = loader.load();

            CancelGui cancelgui = loader.getController();
            cancelgui.setFlightNumber(flightNumber);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 850, 520)); // Consistent scene size
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // This method is intentionally left blank as it is not used in this context.
        // The loadPage method is used instead to load the initial view.
    }
}