package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

import Methods.EditCustomer;
import Methods.EditFlight;

public class EditCustomerGui extends InterfaceLoader {

    @FXML
    private TextField customerIdEdit, passportIdEdit, customerNameEdit;

    @FXML
    private DatePicker dateEdit;
    
    private String oldCustomerId;

    public void setCustomerDetails(String[] customerDetails) {
    	oldCustomerId = customerDetails[0];
    	System.out.println(customerDetails);
        // Populate the fields with the flight details
        if (customerDetails != null && customerDetails.length >= 3) {
            customerIdEdit.setText(customerDetails[0]);
            passportIdEdit.setText(customerDetails[1]);
            customerNameEdit.setText(customerDetails[2]);

        } else {
            showAlert("Error", "Invalid customer details.", AlertType.ERROR);
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
    	
    	String customerId = customerIdEdit.getText();
    	String passportId = passportIdEdit.getText();
    	String customerName = customerNameEdit.getText();
    	
    	EditCustomer confirmEdit = new EditCustomer();
    	confirmEdit.saveEditedData(oldCustomerId, customerId, passportId, customerName);
    	
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}