package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import Methods.CancelFlight;

public class CancelGui extends InterfaceLoader{
	
	@FXML
	private TextField flightNumberLabel, passportIdLabel, customerIdLabel, bookingIdLabel;

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void flightCancelClick(ActionEvent event) {
		
		loadPage(event, "/CancelFlight.fxml");
		
	}
	
	public void HomePage(ActionEvent event) {
		
		loadPage(event, "/Home.fxml");
	}
	
	
	public void ConfirmCancel() {
		String flightNum = flightNumberLabel.getText();
		String passportId = passportIdLabel.getText();
		String customerId = customerIdLabel.getText();
		String bookingId = bookingIdLabel.getText();
		
        if (flightNum.isEmpty() || passportId.isEmpty() || customerId.isEmpty() || bookingId.isEmpty()) {
            System.out.println("All fields must be filled out.");
            return;
        }
		
        CancelFlight confirmCancel = new CancelFlight();
        CancelFlight.deleteFlight(flightNum);
        System.out.println("Successfully deleted");
	}
//	
	
}
