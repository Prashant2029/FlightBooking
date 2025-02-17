package Methods;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import application.EditCustomerGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The {@code EditCustomer} class is responsible for handling customer-related data modifications. 
 * It allows searching, editing, and saving customer information within a file. 
 * The class manages the process of displaying an edit screen, updating customer details, 
 * and saving the modified data back into the customer data file.
 */
public class EditCustomer {
    
    // The file name for storing customer details
    private final String FILE_NAME = "Customer.txt";

    /**
     * Searches for a customer by their ID and displays an edit screen if found.
     * This method will check if a customer exists in the data file and, if found,
     * load the edit screen with the customer details.
     *
     * @param event the ActionEvent that triggers this method (used to get the current stage)
     * @param customerIdToSearch the customer ID to search for in the customer data file
     * @return {@code true} if the customer was found and the edit screen is shown, {@code false} otherwise
     * @throws IOException if an error occurs while reading the customer data file or loading the GUI
     */
    public boolean CheckCustomer(ActionEvent event, String customerIdToSearch) throws IOException {
        boolean flightFound = false;

        // Try with resources to safely handle file operations
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                String[] customerDetails = line.split("::");
                if (customerDetails[0].equals(customerIdToSearch)) {
                    // Customer found, print details and set flightFound to true
                    System.out.println("Customer found: " + line);
                    flightFound = true;

                    // Load the EditCustomerGui and pass the customer details
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditCustomer.fxml"));
                    Parent root = loader.load();

                    // Get the controller and set customer details
                    EditCustomerGui controller = loader.getController();
                    controller.setCustomerDetails(customerDetails);

                    // Show the EditCustomerGui
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();

                    break;
                }
            }
        }

        // Return true if the customer was found, false otherwise
        return flightFound;
    }

    /**
     * Displays an alert informing the user that the customer details have been successfully updated.
     */
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Customer Details changed");
        alert.showAndWait();
    }

    /**
     * Saves the edited customer data into the customer data file.
     * This method replaces the old customer record with the new details.
     *
     * @param oldCustomerId the old customer ID (to be replaced)
     * @param newCustomerId the new customer ID
     * @param passportId the new passport ID
     * @param customerName the new customer name
     */
    public void saveEditedData(String oldCustomerId, String newCustomerId, String passportId, String customerName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read all lines and update the matching record
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length > 2 && parts[0].equals(oldCustomerId)) {
                    // Replace the old customer details with new ones
                    line = newCustomerId + "::" + passportId + "::" + customerName;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            showAlert();
            System.out.println("Customer details updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
