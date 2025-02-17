/**
 * Class for adding a new customer and saving their details to a file.
 * This class handles customer information and writes it to "Customer.txt".
 */
package Methods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddCustomer {
    
    /** Customer's name */
    private String customerName;
    
    /** Customer's unique ID */
    private String customerId;
    
    /** Customer's passport ID */
    private String passportId;
    
    /**
     * Constructor to initialize customer details.
     * 
     * @param customerName The name of the customer.
     * @param customerId The unique ID of the customer.
     * @param passportId The passport ID of the customer.
     */
    public AddCustomer(String customerName, String customerId, String passportId) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.passportId = passportId;
    }
    
    /**
     * Displays an alert to notify the user that the customer has been added successfully.
     */
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Customer added Successfully");
        alert.showAndWait();
    }

    /**
     * Saves the customer details to a file named "Customer.txt".
     * The details are written in the format: customerId::passportId::customerName.
     */
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
