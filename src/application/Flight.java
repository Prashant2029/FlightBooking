package application;

import java.io.BufferedReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Methods.AddFlight;
import Methods.DeleteFlight;
import Methods.EditFlight;
import application.EditFlightGui;


public class Flight extends InterfaceLoader {
    
    @FXML
    private TextField flightNumberLabel, originLabel, destinationLabel, flightNumberdel, flightNumberLabelEdit;
    
    @FXML
    private DatePicker dateLabel;
    

    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    public void switchToCustomer(ActionEvent event) throws IOException{
    	loadPage(event, "/Customer.fxml");
    	
    }
    
    // Fix the method name to avoid conflict
    public void addNewFlight(ActionEvent event) {
        String flightNum = flightNumberLabel.getText();
        String origin = originLabel.getText();
        String destination = destinationLabel.getText();
        LocalDate departureDate = dateLabel.getValue();

        // Create an instance of AddFlight
        AddFlight flight = new AddFlight(origin, destination, flightNum, departureDate);
        flight.SaveToFile();
    }
    
    public void deleteFlightById(ActionEvent event) {
        String flightNumdel = flightNumberdel.getText();

        if (flightNumdel.isEmpty()) {
            System.out.println("Please enter a flight number.");
            return;
        }

        // Confirmation Dialog (Optional)
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Flight");
        alert.setHeaderText("Are you sure you want to delete flight: " + flightNumdel + "?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DeleteFlight.deleteFlight(flightNumdel);
        }
    }
    
    public static void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void Edit(ActionEvent event) throws IOException {
        String flightIdToSearch = flightNumberLabelEdit.getText();
        
        	boolean flightFound;
        	
        	EditFlight edit = new EditFlight();
        	flightFound = edit.CheckFlight(event, flightIdToSearch);

            // If no flight is found
            if (!flightFound) {
                showAlert("Flight Not Found", "No flight found with ID: " + flightIdToSearch, AlertType.ERROR);
            }

        } 
  
       

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
    


