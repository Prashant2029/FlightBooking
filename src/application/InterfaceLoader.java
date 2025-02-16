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

import Methods.BookFlight;

public abstract class InterfaceLoader extends Application {

    // 🔹 Generic method to load any page
    public void loadPage(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 850, 520));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected AnchorPane createFlightPane(String flightNumber, String origin, String destination, String date) throws IOException {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(506, 150);
        anchorPane.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white; -fx-padding: 10px;");
        
        String imagePath = "/shit.png";
        Image image = new Image(getClass().getResourceAsStream(imagePath));// Path relative to resources
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200); // Adjust size as needed
        imageView.setFitHeight(150);
        imageView.setLayoutX(10); // Adjust X position
        imageView.setLayoutY(20);   // Adjust Y position (above airlineLabel)

        // Airline Label
        Label flightNumberLabel = new Label(flightNumber);
        flightNumberLabel.setLayoutX(250);
        flightNumberLabel.setLayoutY(21);
        flightNumberLabel.setFont(new Font("Courier New", 20));  // Increased font size

        // Route Label
        Label routeLabel = new Label("Origin: " + origin);
        routeLabel.setLayoutX(200);
        routeLabel.setLayoutY(70);
        routeLabel.setFont(new Font("Arial", 16));  // Increased font size
        
        Label routeLabel1 = new Label("Destination: " + destination);
        routeLabel1.setLayoutX(200);
        routeLabel1.setLayoutY(100);
        routeLabel1.setFont(new Font("Arial", 16));  // Increased font size

        // Price Label
        Label priceLabel = new Label("Rs. " + "40000");
        priceLabel.setLayoutX(200);
        priceLabel.setLayoutY(130);
        priceLabel.setFont(new Font("Arial", 16));  // Increased font size
        
        // Date Label
        Label dateLabel = new Label("Date: " + date);
        dateLabel.setLayoutX(200);
        dateLabel.setLayoutY(160);
        dateLabel.setFont(new Font("Arial", 16));  // Increased font size

        // Book Now Button
        Button bookNowBtn = new Button("Book Now");
        bookNowBtn.setLayoutX(400);
        bookNowBtn.setLayoutY(60);
        bookNowBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        bookNowBtn.setFont(new Font("Arial", 16));  // Increased font size
        bookNowBtn.setOnAction(event -> {
        	
            try {
                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/BookFlight.fxml"));
                Parent root = loader.load();

                // Get the controller and set the flightNumber
                BookGui bookGui = loader.getController();
                bookGui.setFlightNumber(flightNumber);

                // Show the new scene
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 850, 520));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        // Cancel Button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setLayoutX(400);
        cancelBtn.setLayoutY(100);
        cancelBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        cancelBtn.setFont(new Font("Arial", 16));  // Increased font size
        cancelBtn.setOnAction(event -> {
        	
        	CancelGui cancelflight = new CancelGui();
        	cancelflight.flightCancelClick(event);
        	
        });

        // Adding all components to the AnchorPane
        anchorPane.getChildren().addAll(imageView, flightNumberLabel, routeLabel, routeLabel1, priceLabel, dateLabel, bookNowBtn, cancelBtn);
        
        return anchorPane;
    }
}
