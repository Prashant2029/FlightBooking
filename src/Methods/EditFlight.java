package Methods;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.EditFlightGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The {@code EditFlight} class provides functionality to edit flight details within a file.
 * It allows searching for a specific flight, displaying an edit form with the flight's current details,
 * and saving the modified flight data back to the file.
 */
public class EditFlight {
    
    // The file name for storing flight details
    private final String FILE_NAME = "Flight.txt";

    /**
     * Searches for a flight by its flight ID and displays an edit screen if found.
     * This method checks the file for a matching flight ID, and if found, opens a GUI
     * allowing the user to edit the flight details.
     *
     * @param event the ActionEvent that triggered this method (used to get the current stage)
     * @param flightIdToSearch the flight ID to search for in the flight data file
     * @return {@code true} if the flight was found and the edit screen is shown, {@code false} otherwise
     * @throws IOException if an error occurs while reading the flight data file or loading the GUI
     */
    public boolean CheckFlight(ActionEvent event, String flightIdToSearch) throws IOException {
        boolean flightFound = false;

        // Try with resources to safely handle file operations
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                String[] flightDetails = line.split("::");
                if (flightDetails[0].equals(flightIdToSearch)) {
                    // Flight found, print details and set flightFound to true
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

        // Return true if the flight was found, false otherwise
        return flightFound;
    }

    /**
     * Displays an alert informing the user that the flight details have been successfully updated.
     */
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Flight Details changed");
        alert.showAndWait();
    }

    /**
     * Saves the edited flight data into the flight data file.
     * This method replaces the old flight record with the new flight details.
     *
     * @param oldFlightNumber the old flight number (to be replaced)
     * @param newFlightNumber the new flight number
     * @param origin the new flight origin
     * @param destination the new flight destination
     * @param date the new flight date
     */
    public void saveEditedData(String oldFlightNumber, String newFlightNumber, String origin, String destination, LocalDate date) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read all lines and update the matching record
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length > 3 && parts[0].equals(oldFlightNumber)) {
                    // Replace the old flight details with new ones
                    line = newFlightNumber + "::" + origin + "::" + destination + "::" + date;
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
            System.out.println("Flight details updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
