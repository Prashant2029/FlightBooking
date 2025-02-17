package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Controller class for the "More" view, which displays customer and booking information in tables.
 */
public class MoreController extends InterfaceLoader {

    @FXML
    private Button home;
    @FXML
    private Button flight;
    @FXML
    private TableView<ObservableList<String>> customerTable;
    @FXML
    private TableColumn<ObservableList<String>, String> customerIdColumn;
    @FXML
    private TableColumn<ObservableList<String>, String> passportIdColumn;
    @FXML
    private TableColumn<ObservableList<String>, String> customerNameColumn;

    @FXML
    private TableView<ObservableList<String>> bookingTable;
    @FXML
    private TableColumn<ObservableList<String>, String> flightNumberColumn;
    @FXML
    private TableColumn<ObservableList<String>, String> bookingIdColumn;
    @FXML
    private TableColumn<ObservableList<String>, String> bookingPassportIdColumn;
    @FXML
    private TableColumn<ObservableList<String>, String> bookingCustomerIdColumn;


    /**
     * Initializes the controller. Loads customer and booking data into the tables.
     */
    @FXML
    public void initialize() {
        loadCustomerData();
        loadBookingData();
    }

    /**
     * Loads customer data from the "Customer.txt" file and populates the customerTable.
     */
    private void loadCustomerData() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("Customer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split("::");
                if (values.length >= 3) { // Ensure there are at least 3 columns
                    ObservableList<String> row = FXCollections.observableArrayList(values);
                    data.add(row);
                } else {
                    System.err.println("Skipping invalid line in Customer.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        customerTable.setItems(data);

        // Set cell value factories
        customerIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(0)));
        passportIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(1)));
        customerNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(2)));
    }

    /**
     * Loads booking data from the "Bookings.txt" file and populates the bookingTable.
     */
    private void loadBookingData() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("Bookings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split("::");
                if (values.length >= 4) { // Ensure there are at least 4 columns
                    ObservableList<String> row = FXCollections.observableArrayList(values);
                    data.add(row);
                } else {
                    System.err.println("Skipping invalid line in Bookings.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        bookingTable.setItems(data);

        // Set cell value factories
        flightNumberColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(0)));
        bookingIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(1)));
        bookingPassportIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(2)));
        bookingCustomerIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(3)));
    }

    /**
     * Switches to the Home view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the Home view cannot be loaded.
     */
    public void switchToHome(ActionEvent event) throws IOException {
        loadPage(event, "/Home.fxml");
    }

    /**
     * Switches to the Customer view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the Customer view cannot be loaded.
     */
    public void switchToCustomer(ActionEvent event) throws IOException {
        loadPage(event, "/Customer.fxml");
    }

    /**
     * Switches to the Flight view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file for the Flight view cannot be loaded.
     */
    public void switchToFlight(ActionEvent event) throws IOException {
        loadPage(event, "/Flight.fxml");
    }

    /**
     * The start method is not needed in a controller.
     * It is used in the Main class to launch the application.
     *
     * @param arg0 The primary stage for this application.
     * @throws Exception If an error occurs during application startup.
     */
    @Override
    public void start(Stage arg0) throws Exception {
        // This method is intentionally left blank.
    }
}