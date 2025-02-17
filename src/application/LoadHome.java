package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the login view.
 * Handles user authentication by comparing entered credentials with those stored in a file.
 */
public class LoadHome extends InterfaceLoader {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    /**
     * Checks user login credentials against stored credentials.
     * If authentication is successful, loads the Home view.
     * If authentication fails, displays an error message.
     *
     * @param event The ActionEvent triggered by the login button.
     */
    public void checkLogin(ActionEvent event) {
        String usernameEntered = username.getText();
        String passwordEntered = password.getText();

        try (BufferedReader br = new BufferedReader(new FileReader("Login.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split("::");
                if (credentials.length == 2) {
                    String storedUsername = credentials[0].trim(); // Trim whitespace for accurate comparison
                    String storedPassword = credentials[1].trim(); // Trim whitespace for accurate comparison

                    if (usernameEntered.equals(storedUsername) && passwordEntered.equals(storedPassword)) {
                        loadPage(event, "/Home.fxml"); // Authentication successful
                        return; // Important: Stop after successful login
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // In a real application, handle the exception more gracefully. Log it or show a user-friendly error message.
            showAlert("Error", "Error reading user credentials file.");
            return; // Stop after error
        }

        // If the loop completes without a match, login failed
        showAlert("Login Failed", "Invalid username or password.");
    }

    /**
     * Displays an alert dialog with the specified title and message.
     *
     * @param title   The title of the alert dialog.
     * @param message The message to be displayed in the alert dialog.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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