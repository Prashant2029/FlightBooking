package Methods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddCustomer {
    
    private String customerName;
    private String customerId;
    private String passportId;
    
    // Constructor
    public AddCustomer(String customerName, String customerId, String passportId) {
    	this.customerName = customerName;
    	this.customerId = customerId;
    	this.passportId = passportId;
    }
    
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succecss");
        alert.setHeaderText(null);
        alert.setContentText("Customer added Successfully");
        alert.showAndWait();
    }

    // Method to save flight details to a file
    public void SaveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Customer.txt", true))) {
            writer.write(customerId + "::" + passportId + "::" + customerName);
            writer.newLine();
            showAlert();
            System.out.println("Customer details saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
