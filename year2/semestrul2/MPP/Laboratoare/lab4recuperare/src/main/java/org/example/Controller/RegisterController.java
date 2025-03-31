package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Model.Participant;
import org.example.Service.AtletismService;
import org.example.Service.AtletismServiceImplementation;
import org.example.Repository.DBParticipantRepository;
import org.example.Repository.DBInscriereRepository;
import org.example.Repository.DBConcursRepository;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField emailField;

    private AtletismService atletismService;

    public RegisterController() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("/Users/plaiurares/cs-ubb/year2/semestrul2/MPP/Laboratoare/lab4recuperare/src/main/resources/bd.proprieties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBParticipantRepository participantRepository = new DBParticipantRepository(properties);
        DBInscriereRepository inscriereRepository = new DBInscriereRepository(properties);
        DBConcursRepository concursRepository = new DBConcursRepository(properties);
        this.atletismService = new AtletismServiceImplementation(participantRepository, inscriereRepository, concursRepository);
    }
    @FXML
    private void handleOpenInscriereWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InscriereWindow.fxml"));
            Parent root = loader.load();

            InscriereController controller = loader.getController();
            controller.setService(atletismService); // Pass the service

            Stage stage = new Stage();
            stage.setTitle("Inscriere Manager");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleRegisterButtonAction() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String age = ageField.getText();
        String email = emailField.getText();

        Participant participant = new Participant(name, surname, age, email);
        atletismService.saveParticipant(participant);
    }

    public void setService(AtletismService service) {
    }



}