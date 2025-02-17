package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import Methods.CancelFlight;

/**
 * Controller class for handling flight cancellations.
 * Provides functionality to confirm and process flight cancellations.
 */
public class CancelGui extends InterfaceLoader {
    
    @FXML
    private TextField passportIdLabel, customerIdLabel, bookingIdLabel;
    
    private String flightNumber;

    /**
     * JavaFX application entry point (not implemented).
     */
    @Override
    public void start(Stage arg0) throws Exception {
        // Method not implemented
    }
    
    /**
     * Sets the flight number for cancellation.
     * 
     * @param flightNumber The flight number to be cancelled.
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Navigates to the flight cancellation page.
     * 
     * @param event The action event triggered by the cancel button.
     */
    public void flightCancelClick(ActionEvent event) {
        loadPage(event, "/CancelFlight.fxml");
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
     * Confirms and processes the flight cancellation.
     * Validates required fields before deleting the booking.
     */
    public void ConfirmCancel() {
        String passportId = passportIdLabel.getText();
        String customerId = customerIdLabel.getText();
        String bookingId = bookingIdLabel.getText();
        
        if (flightNumber == null || flightNumber.isEmpty() || passportId.isEmpty() || customerId.isEmpty() || bookingId.isEmpty()) {
            System.out.println("All fields must be filled out.");
            return;
        }
        
        CancelFlight.deleteFlight(flightNumber, passportId, customerId, bookingId);
        System.out.println("Successfully deleted");
    }
}
