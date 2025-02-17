package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import application.BookGui;
import java.io.*;

import Methods.BookFlight;
import application.CancelGui;

/**
 * The HomeController class is responsible for managing the home screen of the flight booking application.
 * It loads the list of available flights from a text file and provides methods to switch between different
 * pages, such as the flight, customer, and more pages.
 * This class also provides functionality to dynamically display available flight information in the UI.
 */
public class HomeController extends InterfaceLoader {

    private Parent root;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox flightContainer;

    @FXML
    private Button addFlightBtn;

    @FXML
    private Button flight;

    private final String FILE_NAME = "Flight.txt";

    /**
     * Initializes the controller by loading the list of flights into the UI.
     */
    @FXML
    public void initialize() {
        loadFlights();
    }

    /**
     * Switches to the Flight page when the corresponding button is clicked.
     * 
     * @param event The ActionEvent triggered when the button is clicked.
     * @throws IOException If there is an error loading the Flight.fxml page.
     */
    @FXML
    public void switchToFlight(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Flight.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 830, 520);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the Customer page when the corresponding button is clicked.
     * 
     * @param event The ActionEvent triggered when the button is clicked.
     * @throws IOException If there is an error loading the Customer.fxml page.
     */
    @FXML
    public void switchToCustomer(ActionEvent event) throws IOException {
        loadPage(event, "/Customer.fxml");
    }

    /**
     * Switches to the More page when the corresponding button is clicked.
     * 
     * @param event The ActionEvent triggered when the button is clicked.
     * @throws IOException If there is an error loading the More.fxml page.
     */
    @FXML
    public void switchToMore(ActionEvent event) throws IOException {
        loadPage(event, "/More.fxml");
    }

    /**
     * Loads available flights from a file and displays them in the UI.
     * Each flight's details are displayed in individual AnchorPanes and added to the VBox container.
     */
    private void loadFlights() {
        flightContainer.getChildren().clear();
        File file = new File(FILE_NAME);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split("::");
                if (details.length == 4) {
                    // Create a flight pane for each available flight and add to the container
                    AnchorPane flightPane = createFlightPane(details[0], details[1], details[2], details[3]);
                    flightContainer.getChildren().add(flightPane);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A placeholder method for starting the application, inherited from the InterfaceLoader class.
     * 
     * @param arg0 The primary Stage for the application.
     * @throws Exception If there is an error during initialization.
     */
    @Override
    public void start(Stage arg0) throws Exception {
        // This method is not currently implemented
    }
}
