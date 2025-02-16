package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

import Methods.EditFlight;

public class EditFlightGui extends InterfaceLoader {

    @FXML
    private TextField flightNumberEdit, passportIdEdit, customerIdEdit;

    @FXML
    private DatePicker dateEdit;
    
    private String oldFlightNumber;

    public void setFlightDetails(String[] flightDetails) {
    	oldFlightNumber = flightDetails[0];
        // Populate the fields with the flight details
        if (flightDetails != null && flightDetails.length >= 4) {
            flightNumberEdit.setText(flightDetails[0]);
            passportIdEdit.setText(flightDetails[1]);
            customerIdEdit.setText(flightDetails[2]);
            dateEdit.setValue(LocalDate.parse(flightDetails[3])); // Assuming the date is in ISO format (e.g., "2023-10-01")

        } else {
            showAlert("Error", "Invalid flight details.", AlertType.ERROR);
        }
    }

    public void HomePage(ActionEvent event) {
        loadPage(event, "/Home.fxml");
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    public void ConfirmChange(ActionEvent event) {
    	
    	String flightNum = flightNumberEdit.getText();
    	String passportId = passportIdEdit.getText();
    	String customerId = customerIdEdit.getText();
    	LocalDate date = dateEdit.getValue();
    	
    	EditFlight confirmEdit = new EditFlight();
    	confirmEdit.saveEditedData(oldFlightNumber, flightNum, passportId, customerId, date);
    	
    	
    	
    	
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}