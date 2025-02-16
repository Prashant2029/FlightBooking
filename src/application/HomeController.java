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

    @FXML
    public void initialize() {
        loadFlights();
    }
    
    @FXML
    public void switchToFlight(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Flight.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, 830, 520);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void switchToCustomer(ActionEvent event) throws IOException{
    	loadPage(event, "/Customer.fxml");
    	
    }
    

    private void loadFlights() {
        flightContainer.getChildren().clear();
        File file = new File(FILE_NAME);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split("::");
                if (details.length == 4) {
                	
                    AnchorPane flightPane = createFlightPane(details[0], details[1], details[2], details[3]);
                    flightContainer.getChildren().add(flightPane);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

    
}
