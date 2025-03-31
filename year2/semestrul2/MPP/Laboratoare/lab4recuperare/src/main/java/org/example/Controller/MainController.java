package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.example.Repository.*;
import org.example.Service.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private AnchorPane participantView;
    @FXML private AnchorPane concursListView;
    @FXML private AnchorPane registerView;

    private ParticipantListController participantListController;
    private ConcursListController concursListController;
    private RegisterController registerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Load properties
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/bd.proprieties"));

            // Init repositories & service
            DBParticipantRepository participantRepo = new DBParticipantRepository(props);
            DBInscriereRepository inscriereRepo = new DBInscriereRepository(props);
            DBConcursRepository concursRepo = new DBConcursRepository(props);
            AtletismService service = new AtletismServiceImplementation(participantRepo, inscriereRepo, concursRepo);

            // Load ParticipantListView.fxml
            FXMLLoader participantLoader = new FXMLLoader(getClass().getResource("/views/ParticipantListView.fxml"));
            AnchorPane participantPane = participantLoader.load();
            participantView.getChildren().setAll(participantPane);
            participantListController = participantLoader.getController();
            participantListController.setService(service);

            // In the method:
            FXMLLoader concursListLoader = new FXMLLoader(getClass().getResource("/views/ConcursListView.fxml"));
            AnchorPane concursListPane = concursListLoader.load();
            concursListView.getChildren().setAll(concursListPane);
            concursListController = concursListLoader.getController();
            concursListController.setService(service);

            FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("/views/RegisterParticipantView.fxml"));
            GridPane registerPane = registerLoader.load(); // ✅ use GridPane here!
            registerView.getChildren().setAll(registerPane);
            registerController = registerLoader.getController();
            registerController.setService(service);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
