/**
 * Controller class for handling the ticket display in a flight booking system.
 * This class is responsible for setting and displaying ticket-related data
 * including flight number, booking ID, customer ID, passport ID, and customer name.
 *
 * The customer name is retrieved from a text file based on the provided customer ID.
 */
package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TicketController {

    /** Label to display the flight number. */
    @FXML
    public Label flightNumberLabel;

    /** Label to display the booking ID. */
    @FXML
    public Label bookingIdLabel;

    /** Label to display the customer ID. */
    @FXML
    public Label customerIdLabel;

    /** Label to display the passport ID. */
    @FXML
    public Label passportIdLabel;

    /** Label to display the customer's name. */
    @FXML
    public Label nameLabel;

    /** Stores the customer's name retrieved from the file. */
    private String name;

    /**
     * Sets the ticket data and updates the labels accordingly.
     * Retrieves the customer's name from "Customer.txt" based on the customer ID.
     *
     * @param flightNumber The flight number associated with the ticket.
     * @param bookingId The unique booking ID for the ticket.
     * @param customerId The customer ID associated with the booking.
     * @param passportId The passport ID of the customer.
     */
    public void setTicketData(String flightNumber, String bookingId, String customerId, String passportId) {
        try (BufferedReader br = new BufferedReader(new FileReader("Customer.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length >= 2 && parts[0].equals(customerId)) {
                    this.name = parts[2];
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        
        System.out.println(this.name);
        flightNumberLabel.setText(flightNumber);
        bookingIdLabel.setText(bookingId);
        customerIdLabel.setText(customerId);
        passportIdLabel.setText(passportId);
        nameLabel.setText(this.name);
    }
}
