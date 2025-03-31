package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Model.Participant;
import org.example.Service.AtletismService;

import java.net.URL;
import java.util.ResourceBundle;

public class ParticipantListController implements Initializable {

    @FXML private TableView<Participant> participantTable;
    @FXML private TableColumn<Participant, String> nameColumn;
    @FXML private TableColumn<Participant, String> surnameColumn;
    @FXML private TableColumn<Participant, String> ageColumn;
    @FXML private TableColumn<Participant, String> emailColumn;

    private AtletismService service;

    public void setService(AtletismService service) {
        this.service = service;
        loadTable(); // <--- This triggers the display
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("varsta"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadTable() {
        if (service != null) {
            participantTable.getItems().setAll(service.getAllParticipants());
        }
    }
}
