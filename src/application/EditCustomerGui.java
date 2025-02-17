package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

import Methods.EditCustomer;
import Methods.EditFlight;

/**
 * Controller class for the Edit Customer view.
 * This class handles user interactions related to editing customer information.
 * It populates the form fields with existing customer data, allows the user to
 * make changes, and saves the updated information.  It also handles navigation.
 */
public class EditCustomerGui extends InterfaceLoader {

    @FXML
    private TextField customerIdEdit, passportIdEdit, customerNameEdit;

    @FXML
    private DatePicker dateEdit; // Currently unused, consider removing if not needed.

    private String oldCustomerId;

    /**
     * Sets the customer details to be displayed in the edit form.
     * This method receives an array of customer details and populates the
     * corresponding text fields.
     *
     * @param customerDetails An array of strings containing the customer details
     *                        in the order: customerId, passportId, customerName.
     *                        If the array is null or has fewer than 3 elements, an error alert is shown.
     */
    public void setCustomerDetails(String[] customerDetails) {
        oldCustomerId = customerDetails[0];
        System.out.println(customerDetails); // Consider removing this print statement in production.

        if (customerDetails != null && customerDetails.length >= 3) {
            customerIdEdit.setText(customerDetails[0]);
            passportIdEdit.setText(customerDetails[1]);
            customerNameEdit.setText(customerDetails[2]);

        } else {
            showAlert("Error", "Invalid customer details.", AlertType.ERROR);
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
     * Navigates to the More view.
     *
     * @param event The ActionEvent triggered by the navigation button.
     */
    public void switchToMore(ActionEvent event) {
        loadPage(event, "/More.fxml");
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
     * Confirms the changes made to the customer details and saves them.
     * Retrieves the updated customer information from the text fields and
     * calls the `saveEditedData` method of the `EditCustomer` class to persist the changes.
     *
     * @param event The ActionEvent triggered by the confirmation button.
     */
    @FXML
    public void ConfirmChange(ActionEvent event) {

        String customerId = customerIdEdit.getText();
        String passportId = passportIdEdit.getText();
        String customerName = customerNameEdit.getText();

        EditCustomer confirmEdit = new EditCustomer();
        confirmEdit.saveEditedData(oldCustomerId, customerId, passportId, customerName);

        // Consider adding feedback to the user after a successful update or handling potential errors.
        showAlert("Success", "Customer details updated successfully.", AlertType.INFORMATION); // Example feedback
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