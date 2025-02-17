package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Methods.AddFlight;
import Methods.DeleteFlight;
import Methods.EditFlight;


/**
 * Controller class for the Flight management view.
 * This class handles user interactions related to adding, deleting, and editing
 * flight information. It also manages navigation to other views.
 */
public class Flight extends InterfaceLoader {

    @FXML
    private TextField flightNumberLabel, originLabel, destinationLabel, flightNumberdel, flightNumberLabelEdit;

    @FXML
    private DatePicker dateLabel;

    /**
     * Switches to the Home view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the Home view cannot be loaded.
     */
    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        loadPage(event, "/Home.fxml");
    }

    /**
     * Switches to the Customer view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the Customer view cannot be loaded.
     */
    @FXML
    public void switchToCustomer(ActionEvent event) throws IOException {
        loadPage(event, "/Customer.fxml");
    }

    /**
     * Switches to the More view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the More view cannot be loaded.
     */
    @FXML
    public void switchToMore(ActionEvent event) throws IOException {
        loadPage(event, "/More.fxml");
    }

    /**
     * Adds a new flight to the system.
     * Retrieves flight information from the input fields and saves it to a file.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    public void addNewFlight(ActionEvent event) {
        String flightNum = flightNumberLabel.getText();
        String origin = originLabel.getText();
        String destination = destinationLabel.getText();
        LocalDate departureDate = dateLabel.getValue();

        AddFlight flight = new AddFlight(origin, destination, flightNum, departureDate);
        flight.SaveToFile();
    }

    /**
     * Deletes a flight from the system by flight number.
     * Retrieves the flight number from the input field and deletes the
     * corresponding flight record. Displays a confirmation dialog before
     * deleting the flight.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    public void deleteFlightById(ActionEvent event) {
        String flightNumdel = flightNumberdel.getText();

        if (flightNumdel.isEmpty()) {
            System.out.println("Please enter a flight number.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Flight");
        alert.setHeaderText("Are you sure you want to delete flight: " + flightNumdel + "?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DeleteFlight.deleteFlight(flightNumdel);
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
     * Opens the edit flight view for a specific flight.
     * Checks if a flight with the given ID exists. If found, opens the edit view.
     * Otherwise, displays an error message.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an error occurs while loading the edit view.
     */
    public void Edit(ActionEvent event) throws IOException {
        String flightIdToSearch = flightNumberLabelEdit.getText();

        boolean flightFound;

        EditFlight edit = new EditFlight();
        flightFound = edit.CheckFlight(event, flightIdToSearch);

        if (!flightFound) {
            showAlert("Flight Not Found", "No flight found with ID: " + flightIdToSearch, AlertType.ERROR);
        }
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