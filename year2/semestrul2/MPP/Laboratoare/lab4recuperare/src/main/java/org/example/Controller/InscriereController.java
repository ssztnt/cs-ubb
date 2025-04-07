package org.example.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Model.Concurs;
import org.example.Model.Inscriere;
import org.example.Model.Participant;
import org.example.Service.AtletismService;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class InscriereController implements Initializable {

    @FXML private TableView<Inscriere> inscriereTable;
    @FXML private TableColumn<Inscriere, String> idColumn;
    @FXML private TableColumn<Inscriere, String> participantColumn;
    @FXML private TableColumn<Inscriere, String> concursColumn;
    @FXML private TableColumn<Inscriere, String> timestampColumn;

    @FXML private TextField participantEmailField;
    @FXML private TextField concursNameField;
    @FXML private TextField filterConcursField;

    private List<Inscriere> allInscrieri;
    private AtletismService service;

    public void setService(AtletismService service) {
        this.service = service;
        this.allInscrieri = service.getAllInscrieri();  // Load once
        loadTable(allInscrieri);
    }

    private void loadTable(List<Inscriere> list) {
        inscriereTable.getItems().setAll(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId_inscriere()));
        participantColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId_participant()));
        concursColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getConcurs_name()));
        timestampColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTimestamp()));
    }

    @FXML
    private void handleAddInscriere() {
        String email = participantEmailField.getText();
        String concursName = concursNameField.getText();

        if (email.isEmpty() || concursName.isEmpty()) {
            showAlert("Missing Input", "Please provide both email and concurs name.");
            return;
        }

        List<Participant> participants = service.getParticipantsByEmail(email);
        List<Concurs> concursuri = service.getConcursByName(concursName);

        if (participants.isEmpty() || concursuri.isEmpty()) {
            showAlert("Not found", "Participant or Concurs not found.");
            return;
        }

        Participant p = participants.get(0);
        Concurs c = concursuri.get(0);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Inscriere inscriere = new Inscriere(
                UUID.randomUUID().toString(),
                p.getId_participant(),
                c.getNume(),
                timestamp
        );

        service.saveInscriere(inscriere);

        // Refresh full list + reset filter field
        this.allInscrieri = service.getAllInscrieri();
        filterConcursField.clear();
        loadTable(allInscrieri);
    }

    @FXML
    private void handleFilterByConcurs() {
        String filter = filterConcursField.getText().trim().toLowerCase();

        if (filter.isEmpty()) {
            loadTable(allInscrieri);
            return;
        }

        List<Inscriere> filtered = allInscrieri.stream()
                .filter(i -> i.getConcurs_name().toLowerCase().contains(filter))
                .toList();

        loadTable(filtered);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
