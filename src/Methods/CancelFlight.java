package Methods;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code CancelFlight} class is responsible for deleting a flight booking from the booking records.
 * It searches for a booking with the specified flight ID, passport ID, customer ID, and booking ID,
 * and if found, removes it from the records. Alerts are displayed to inform the user of success or failure.
 */
public class CancelFlight {

    /**
     * Deletes a flight booking from the {@code Bookings.txt} file based on the provided details.
     * The method reads the booking records and deletes the matching entry if found.
     * After deletion, the file is updated with the remaining records.
     *
     * @param flightId the flight ID associated with the booking to be canceled
     * @param passportId the passport ID of the customer
     * @param customerId the customer ID of the person who made the booking
     * @param bookingId the booking ID of the reservation to be canceled
     */
    public static void deleteFlight(String flightId, String passportId, String customerId, String bookingId) {
        File inputFile = new File("Bookings.txt");
        File tempFile = new File("temp.txt");

        // Check if the booking record file exists
        if (!inputFile.exists()) {
            showAlert(AlertType.ERROR, "Error", "Booking record file not found.");
            return;
        }

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] bookingDetails = currentLine.split("::");

                if (bookingDetails.length >= 4) {
                    String storedFlightId = bookingDetails[0].trim();
                    String storedPassportId = bookingDetails[1].trim();
                    String storedCustomerId = bookingDetails[2].trim();
                    String storedBookingId = bookingDetails[3].trim();

                    // Match the provided details with the stored records
                    if (storedFlightId.startsWith(flightId) &&
                        passportId.equals(storedPassportId) &&
                        customerId.equals(storedCustomerId) &&
                        bookingId.equals(storedBookingId)) {

                        found = true;
                        continue; // Skip writing this line (deleting it)
                    }
                }
                writer.write(currentLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error reading/writing file: " + e.getMessage());
            return; // Exit on error
        }

        // If the booking was found and deleted, update the file
        if (found) {
            if (!inputFile.delete()) {
                showAlert(AlertType.ERROR, "Error", "Could not delete original file.");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                showAlert(AlertType.ERROR, "Error", "Could not rename temporary file.");
            } else {
                showAlert(AlertType.INFORMATION, "Success", "Booking deleted successfully.");
            }
        } else {
            tempFile.delete(); // Clean up
            showAlert(AlertType.INFORMATION, "Not Found", "No matching booking found.");
        }
    }

    /**
     * Displays an alert with the specified type, title, and message.
     *
     * @param type the type of the alert (e.g., ERROR, INFORMATION)
     * @param title the title of the alert
     * @param message the message to be displayed in the alert
     */
    private static void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);
        alert.showAndWait();
    }
}
