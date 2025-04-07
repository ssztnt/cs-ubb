package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Controller.LoginController;
import org.example.Repository.DBConcursRepository;
import org.example.Repository.DBInscriereRepository;
import org.example.Repository.DBParticipantRepository;
import org.example.Service.AtletismService;
import org.example.Service.AtletismServiceImplementation;

import java.io.FileInputStream;
import java.util.Properties;

public class FXMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load DB properties
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/bd.proprieties"));

        // Create repositories
        DBParticipantRepository participantRepo = new DBParticipantRepository(props);
        DBInscriereRepository inscriereRepo = new DBInscriereRepository(props);
        DBConcursRepository concursRepo = new DBConcursRepository(props);

        // ✅ Create the service
        AtletismService service = new AtletismServiceImplementation(participantRepo, inscriereRepo, concursRepo);

        // Load the login view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
        Parent root = loader.load();

        // ✅ Inject the service into the LoginController
        LoginController loginController = loader.getController();
        loginController.setService(service); // ← HERE you inject it

        // Show the login window
        primaryStage.setTitle("Participant Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
