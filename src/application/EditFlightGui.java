package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

import Methods.EditFlight;

/**
 * Controller class for the Edit Flight view.
 * This class handles user interactions related to editing flight information.
 * It populates the form fields with existing flight data, allows the user to
 * make changes, and saves the updated information.  It also handles navigation.
 */
public class EditFlightGui extends InterfaceLoader {

    @FXML
    private TextField flightNumberEdit, passportIdEdit, customerIdEdit;

    @FXML
    private DatePicker dateEdit;

    private String oldFlightNumber;

    /**
     * Sets the flight details to be displayed in the edit form.
     * This method receives an array of flight details and populates the
     * corresponding text fields and date picker.
     *
     * @param flightDetails An array of strings containing the flight details
     *                      in the order: flightNumber, passportId, customerId, date.
     *                      The date is expected to be in ISO format (e.g., "2023-10-01").
     *                      If the array is null or has fewer than 4 elements, or if the date
     *                      cannot be parsed, an error alert is shown.
     */
    public void setFlightDetails(String[] flightDetails) {
        oldFlightNumber = flightDetails[0];

        if (flightDetails != null && flightDetails.length >= 4) {
            flightNumberEdit.setText(flightDetails[0]);
            passportIdEdit.setText(flightDetails[1]);
            customerIdEdit.setText(flightDetails[2]);
            try {
                dateEdit.setValue(LocalDate.parse(flightDetails[3]));
            } catch (java.time.format.DateTimeParseException e) {
                showAlert("Error", "Invalid date format.", AlertType.ERROR);
            }
        } else {
            showAlert("Error", "Invalid flight details.", AlertType.ERROR);
        }
    }

    /**
     * Navigates to the Home view.
     *
     * @param event The ActionEvent triggered by the navigation button.
     */
    public void HomePage(ActionEvent event) {
        loadPage(event, "/Home.fxml");
    }

    /**
     * Displays an alert dialog with the specified title, message, and alert type.
     *
     * @param title     The title of the alert dialog.
     * @param message   The message to be displayed in the alert dialog.
     * @param alertType The type of the alert dialog (e.g., ERROR, WARNING, INFORMATION).
     */
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Confirms the changes made to the flight details and saves them.
     * Retrieves the updated flight information from the text fields and date picker
     * and calls the `saveEditedData` method of the `EditFlight` class to persist the changes.
     *
     * @param event The ActionEvent triggered by the confirmation button.
     */
    @FXML
    public void ConfirmChange(ActionEvent event) {

        String flightNum = flightNumberEdit.getText();
        String passportId = passportIdEdit.getText();
        String customerId = customerIdEdit.getText();
        LocalDate date = dateEdit.getValue();

        EditFlight confirmEdit = new EditFlight();
        confirmEdit.saveEditedData(oldFlightNumber, flightNum, passportId, customerId, date);

        showAlert("Success", "Flight details updated successfully.", AlertType.INFORMATION); // Example feedback

    }

    /**
     * The start method is required by the Application class but is currently empty.
     * It is overridden here, but currently does nothing. It seems it should be removed.
     *
     * @param arg0 The primary stage for this application.
     * @throws Exception If an error occurs during application startup.
     */
    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub

    }
}