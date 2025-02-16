package Methods;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import application.EditFlightGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditFlight {
    
    private final String FILE_NAME = "Flight.txt";

    public boolean CheckFlight(ActionEvent event, String flightIdToSearch) throws IOException {
        String filename = "Flight.txt"; // Path to your file containing flight details
        boolean flightFound = false;

        // Try with resources for safe closing of file reader
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line by "::" and check if the flightId matches the first element
                String[] flightDetails = line.split("::");
                if (flightDetails[0].equals(flightIdToSearch)) {
                    // If match found, print the flight details and set flightFound to true
                    System.out.println("Flight found: " + line);
                    flightFound = true;

                    // Load the EditFlightGui and pass the flight details
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditFlight.fxml"));
                    Parent root = loader.load();

                    // Get the controller and set the flight details
                    EditFlightGui controller = loader.getController();
                    controller.setFlightDetails(flightDetails);

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
        alert.setContentText("Flight Details changed");
        alert.showAndWait();
    }
    
    
    public void saveEditedData(String oldFlightNumber, String newFlightNumber, String origin, String destination, LocalDate date) {
        String filePath = "Flight.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read all lines and update the matching record
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length > 3 && parts[0].equals(oldFlightNumber)) {
                    // Replace the whole line with new details
                    line = newFlightNumber + "::" + origin + "::" + destination + "::" + date;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write back the updated content using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            showAlert();
            System.out.println("Flight details updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
    

