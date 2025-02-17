package Methods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The {@code BookFlight} class is responsible for managing flight bookings. It allows saving
 * booking details to a file and displays relevant alerts for success or errors.
 */
public class BookFlight {
        
    /**
     * Displays an alert with the specified title, message, and alert type.
     *
     * @param title the title of the alert
     * @param message the message to be displayed in the alert
     * @param type the type of the alert (e.g., ERROR, INFORMATION)
     */
    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

    /**
     * Saves the booking details, including the flight number, passport ID, customer ID, and booking ID,
     * into a file called {@code Bookings.txt}. If the flight number is missing, an error alert is displayed.
     * If the booking is successful, a success alert is shown.
     *
     * @param flightNumber the flight number for the booking
     * @param passportId the passport ID of the customer
     * @param customerId the ID of the customer making the booking
     * @param bookingId the ID of the booking
     */
    public void SaveBooking(String flightNumber, String passportId, String customerId, String bookingId) {
        
        // Check if the flight number is provided
        if (flightNumber == null || flightNumber.trim().isEmpty()) {
            showAlert("Error", "Flight number is missing. Please select a flight before booking.", AlertType.ERROR);
            return;
        }
        
        // Try to save the booking details to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Bookings.txt", true))) {
            writer.write(flightNumber + "::" + passportId + "::" + customerId + "::" + bookingId);
            writer.newLine();
            showAlert("Success", "Booked Successfully!", AlertType.INFORMATION);
            
            System.out.println("Booking details saved successfully: " + flightNumber);
        } catch (IOException e) {
            showAlert("Error", "Failed to save booking. Please try again.", AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
