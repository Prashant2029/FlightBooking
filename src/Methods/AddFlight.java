package Methods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddFlight {
    
    private String origin;
    private String destination;
    private String flightNumber;
    private LocalDate departureDate;
    
    // Constructor
    public AddFlight(String origin, String destination, String flightNumber, LocalDate departureDate) {
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
    }
    
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succecss");
        alert.setHeaderText(null);
        alert.setContentText("Flight added Successfully");
        alert.showAndWait();
    }

    // Method to save flight details to a file
    public void SaveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Flight.txt", true))) {
            writer.write(flightNumber + "::" + origin + "::" + destination + "::" + departureDate);
            writer.newLine();
            showAlert();
            System.out.println("Flight details saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
