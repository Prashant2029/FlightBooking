package Methods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The {@code AddFlight} class is responsible for storing flight details, such as the origin,
 * destination, flight number, and departure date, into a file. It also displays an alert
 * message when the flight details are successfully saved.
 */
public class AddFlight {
    
    private String origin;
    private String destination;
    private String flightNumber;
    private LocalDate departureDate;
    
    /**
     * Constructs a new {@code AddFlight} object with the specified flight details.
     *
     * @param origin the origin of the flight
     * @param destination the destination of the flight
     * @param flightNumber the flight number
     * @param departureDate the departure date of the flight
     */
    public AddFlight(String origin, String destination, String flightNumber, LocalDate departureDate) {
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
    }
    
    /**
     * Displays an alert indicating that the flight was added successfully.
     */
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Flight added successfully");
        alert.showAndWait();
    }

    /**
     * Saves the flight details to a text file called {@code Flight.txt}.
     * The flight details are written in the format:
     * {@code flightNumber::origin::destination::departureDate}.
     * If the file is written successfully, an alert is shown to the user.
     */
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
