package Methods;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import application.EditCustomerGui;
import application.EditFlightGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditCustomer {
	
    private final String FILE_NAME = "Customer.txt";

    
    public boolean CheckCustomer(ActionEvent event, String customerIdToSearch) throws IOException {
        boolean flightFound = false;

        // Try with resources for safe closing of file reader
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line by "::" and check if the flightId matches the first element
                String[] customerDetails = line.split("::");
                if (customerDetails[0].equals(customerIdToSearch)) {
                    // If match found, print the flight details and set flightFound to true
                    System.out.println("Customer found: " + line);
                    flightFound = true;

                    // Load the EditFlightGui and pass the flight details
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditCustomer.fxml"));
                    Parent root = loader.load();

                    // Get the controller and set the flight details
                    EditCustomerGui controller = loader.getController();
                    controller.setCustomerDetails(customerDetails);

                    // Show the EditFlightGui
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();

                    break;
                }
            }
    
        }
        
        if(flightFound) {
        	return true;
        }
        
        return false;   
    }
    
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succecss");
        alert.setHeaderText(null);
        alert.setContentText("Customer Details changed");
        alert.showAndWait();
    }
    
    
    public void saveEditedData(String oldCustomerId, String newCustomerId, String passportId, String customerName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read all lines and update the matching record
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                System.out.println(parts.length);
                if (parts.length > 2 && parts[0].equals(oldCustomerId)) {
                    // Replace the whole line with new details
                    line = newCustomerId + "::" + passportId + "::" + customerName;
                    System.out.println("Shit guy");
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write back the updated content using BufferedWriter
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
    

