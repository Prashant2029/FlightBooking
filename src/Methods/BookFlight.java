package Methods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BookFlight {
        
    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

    public void SaveBooking(String flightNumber, String passportId, String customerId, String bookingId) {
        
        if (flightNumber == null || flightNumber.trim().isEmpty()) {
            showAlert("Error", "Flight number is missing. Please select a flight before booking.", AlertType.ERROR);
            return;
        }
        
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
