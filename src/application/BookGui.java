package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Methods.BookFlight;

/**
 * Controller class for booking flights.
 * Handles booking confirmation, validation, and navigation.
 */
public class BookGui extends InterfaceLoader {

    private String flightNumber;

    @FXML
    private TextField passportIdLabel, customerIdLabel, bookingIdLabel;

    /**
     * Sets the flight number for the booking.
     * 
     * @param flightNumber The flight number to be booked.
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Confirms the flight booking by validating customer details and saving the booking.
     * If successful, it opens the ticket confirmation screen.
     * 
     * @param event The action event triggered by the confirm booking button.
     */
    public void ConfirmBooking(ActionEvent event) {
        String passportId = passportIdLabel.getText();
        String customerId = customerIdLabel.getText();
        String bookingId = bookingIdLabel.getText();

        System.out.println("Flight Number: " + flightNumber);

        if (passportId.isEmpty() || customerId.isEmpty() || bookingId.isEmpty()) {
            System.out.println("All fields must be filled out.");
            return;
        }

        // Check if both customer ID and passport ID exist in Customer.txt
        if (!isCustomerValid(customerId, passportId)) {
        	System.out.println(passportId);
            System.out.println("Error: Customer ID or Passport ID not found in the system.");
            return;
        }

        try {
            BookFlight confirmBook = new BookFlight();
            confirmBook.SaveBooking(flightNumber, passportId, customerId, bookingId);
            System.out.println("Booking confirmed.");

            try {
                // Load Ticket FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ticket.fxml"));
                Parent ticketRoot = loader.load();

                // Get the controller
                TicketController ticketController = loader.getController();

                // Set the ticket data
                ticketController.setTicketData(flightNumber, bookingId, customerId, passportId);

                // Open the ticket window
                Stage ticketStage = new Stage();
                ticketStage.setScene(new Scene(ticketRoot));
                ticketStage.setTitle("Flight Ticket");
                ticketStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates if the customer ID and passport ID exist in the Customer.txt file.
     * 
     * @param customerId The customer ID entered by the user.
     * @param passportId The passport ID entered by the user.
     * @return True if the customer is valid, false otherwise.
     */
    private boolean isCustomerValid(String customerId, String passportId) {
        String filePath = "Customer.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                
                if (parts.length >= 2) {
                    String fileCustomerId = parts[0].trim();
                    String filePassportId = parts[1].trim();

                    if (filePassportId.equalsIgnoreCase(passportId.trim()) && 
                        fileCustomerId.equalsIgnoreCase(customerId.trim())) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Customer.txt: " + e.getMessage());
        }
        showCustomerNotFoundAlert();
        return false;
    }

    /**
     * Displays an error alert when customer validation fails.
     */
    private void showCustomerNotFoundAlert() {
        Alert alert = new Alert(AlertType.ERROR, "Customer ID or Passport ID not found in the system.", ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Navigates back to the home page.
     * 
     * @param event The action event triggered by the home button.
     */
    public void HomePage(ActionEvent event) {
        loadPage(event, "/Home.fxml");
    }

    /**
     * JavaFX application entry point (not implemented).
     */
    @Override
    public void start(Stage arg0) throws Exception {
    }
}
