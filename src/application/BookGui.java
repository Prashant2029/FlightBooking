package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Methods.BookFlight;

public class BookGui extends InterfaceLoader {

    private String flightNumber;

    @FXML
    private TextField flightNumberLabel, passportIdLabel, customerIdLabel, bookingIdLabel;


    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }


    public void ConfirmBooking(ActionEvent event) {
        String passportId = passportIdLabel.getText();
        String customerId = customerIdLabel.getText();
        String bookingId = bookingIdLabel.getText();

        System.out.println("Flight Number: " + flightNumber);

        if (passportId.isEmpty() || customerId.isEmpty() || bookingId.isEmpty()) {
            System.out.println("All fields must be filled out.");
            return;
        }

        try {
            BookFlight confirmBook = new BookFlight();
            confirmBook.SaveBooking(flightNumber, passportId, customerId, bookingId);
            System.out.println("Booking confirmed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HomePage(ActionEvent event) {
        loadPage(event, "/Home.fxml");
    }

    @Override
    public void start(Stage arg0) throws Exception {
    }
}