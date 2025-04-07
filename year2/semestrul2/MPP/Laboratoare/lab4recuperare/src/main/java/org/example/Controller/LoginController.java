package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Model.Participant;
import org.example.Service.AtletismService;

import java.util.List;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private AtletismService service;

    public void setService(AtletismService service) {
        this.service = service;
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Missing Fields", "Please enter both email and password.");
            return;
        }

        if (!password.equals("12345678")) {
            showAlert("Login Failed", "Incorrect password.");
            return;
        }

        List<Participant> matches = service.getParticipantsByEmail(email);
        if (matches.isEmpty()) {
            showAlert("Login Failed", "No participant found with that email.");
            return;
        }

        openMainView(); // ✅ open the full original UI
    }

    private void openMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
            Parent root = loader.load();

            MainController controller = loader.getController();
            controller.setService(service); // inject service

            Stage stage = new Stage();
            stage.setTitle("Atletism Manager");
            stage.setScene(new Scene(root));
            stage.show();

            // close login window
            Stage currentStage = (Stage) emailField.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not open main application.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
