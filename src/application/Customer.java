package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Methods.AddCustomer;
import Methods.DeleteCustomer;
import Methods.DeleteFlight;
import Methods.EditCustomer;


/**
 * Controller class for the Customer management view.
 * This class handles user interactions related to adding, deleting, and editing customer information.
 * It also manages navigation to other views within the application.
 */
public class Customer extends InterfaceLoader {

    @FXML
    private TextField customerNameLabel, customerIdLabel, passportIdLabel, customerNumberdel, customerIdLabelEdit;

    /**
     * Switches to the Home view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the Home view cannot be loaded.
     */
    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        loadPage(event, "/Home.fxml"); // Uses the loadPage method from InterfaceLoader
    }

    /**
     * Switches to the Flight view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the Flight view cannot be loaded.
     */
    @FXML
    public void switchToFlight(ActionEvent event) throws IOException {
        loadPage(event, "/Flight.fxml"); // Uses the loadPage method from InterfaceLoader
    }

    /**
     * Switches to the More view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the More view cannot be loaded.
     */
    @FXML
    public void switchToMore(ActionEvent event) throws IOException {
        loadPage(event, "/More.fxml"); // Uses the loadPage method from InterfaceLoader
    }

    /**
     * Adds a new customer to the system.
     * Retrieves customer information from the input fields and saves it to a file.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    public void addNewCustomer(ActionEvent event) {
        String customerName = customerNameLabel.getText();
        String customerId = customerIdLabel.getText();
        String passportId = passportIdLabel.getText();

        AddCustomer customer = new AddCustomer(customerName, customerId, passportId);
        customer.SaveToFile();
    }

    /**
     * Deletes a customer from the system.
     * Retrieves the customer ID from the input field and deletes the corresponding customer record.
     * Displays a confirmation dialog before deleting the customer.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    public void deleteCustomer(ActionEvent event) {
        String customerId = customerNumberdel.getText();

        if (customerId.isEmpty()) {
            System.out.println("Please enter a customer number.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setHeaderText("Are you sure you want to delete Customer: " + customerId + "?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DeleteCustomer.deleteCustomer(customerId);
        }
    }

    /**
     * Displays an alert dialog with the specified title, message, and alert type.
     *
     * @param title     The title of the alert dialog.
     * @param message   The message to be displayed in the alert dialog.
     * @param alertType The type of the alert dialog (e.g., ERROR, WARNING, INFORMATION).
     */
    public static void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Opens the edit customer view for a specific customer.
     * Checks if a customer with the given ID exists. If found, opens the edit view. Otherwise, displays an error message.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the edit view cannot be loaded.
     */
    public void Edit(ActionEvent event) throws IOException {
        String flightIdToSearch = customerIdLabelEdit.getText();

        boolean flightFound;

        EditCustomer edit = new EditCustomer();
        flightFound = edit.CheckCustomer(event, flightIdToSearch);

        if (!flightFound) {
            showAlert("Customer Not Found", "No Customer found with ID: " + flightIdToSearch, AlertType.ERROR);
        }
    }

    /**
     * The start method is required by the Application class but is currently empty.
     * It is overridden here, but currently does nothing.  It seems it should be removed.
     *
     * @param arg0 The primary stage for this application.
     * @throws Exception If an error occurs during application startup.
     */
    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub

    }
}