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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Methods.AddCustomer;
import Methods.DeleteCustomer;
import Methods.DeleteFlight;
import Methods.EditCustomer;


public class Customer extends InterfaceLoader {
    
    @FXML
    private TextField customerNameLabel, customerIdLabel, passportIdLabel, customerNumberdel, customerIdLabelEdit;     

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
    public void switchToFlight(ActionEvent event) throws IOException {
    	loadPage(event, "/Flight.fxml");
    }
    
    // Fix the method name to avoid conflict
    public void addNewCustomer(ActionEvent event) {
        String customerName = customerNameLabel.getText();
        String customerId = customerIdLabel.getText();
        String passportId = passportIdLabel.getText();

        // Create an instance of AddFlight
        AddCustomer customer = new AddCustomer(customerName, customerId, passportId);
        customer.SaveToFile();
    }
    
    public void deleteCustomer(ActionEvent event) {
        String customerId = customerNumberdel.getText();

        if (customerId.isEmpty()) {
            System.out.println("Please enter a customer number.");
            return;
        }

        // Confirmation Dialog (Optional)
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setHeaderText("Are you sure you want to delete Customer: " + customerId + "?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DeleteCustomer.deleteCustomer(customerId);
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
        String flightIdToSearch = customerIdLabelEdit.getText();
        
        	boolean flightFound;
        	
        	EditCustomer edit = new EditCustomer();
        	flightFound = edit.CheckCustomer(event, flightIdToSearch);

            // If no flight is found
            if (!flightFound) {
                showAlert("Customer Not Found", "No Custom found with ID: " + flightIdToSearch, AlertType.ERROR);
            }

        } 
  
       
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
    


