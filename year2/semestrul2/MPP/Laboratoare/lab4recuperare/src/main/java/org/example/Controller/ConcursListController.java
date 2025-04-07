package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Model.Concurs;
import org.example.Service.AtletismService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ConcursListController implements Initializable {

    @FXML private TableView<Concurs> concursTable;
    @FXML private TableColumn<Concurs, String> nameColumn;
    @FXML private TableColumn<Concurs, String> dateColumn;
    @FXML private TableColumn<Concurs, String> locationColumn;
    @FXML private DatePicker datePicker;

    private List<Concurs> allConcursuri;
    private AtletismService service;

    public void setService(AtletismService service) {
        this.service = service;
        loadTable(); // call after setting service
    }

    private void loadTable() {
        if (service != null) {
            List<Concurs> list = service.getConcursList();

            // 👇 THIS is the line to debug
            System.out.println("Service returns: " + list.size());

            // optional: print each Concurs
            list.forEach(c -> System.out.println(" → " + c));

            concursTable.getItems().setAll(list);
        } else {
            System.out.println("Service is null in loadTable()");
        }
    }

    private void loadTable(List<Concurs> concursuri) {
        concursTable.getItems().setAll(concursuri);
    }

    @FXML
    private void handleFilterByDate() {
        if (datePicker.getValue() == null) {
            loadTable(allConcursuri); // fără filtrare
            return;
        }

        LocalDate selectedDate = datePicker.getValue();

        List<Concurs> filtered = allConcursuri.stream()
                .filter(c -> {
                    try {
                        LocalDate concursDate = LocalDate.parse(c.getData()); // sau parse c.getTimestamp()
                        return !concursDate.isBefore(selectedDate); // >= selectedDate
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toList();

        loadTable(filtered);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("locatie"));
    }

    public void handleFilterByDate(ActionEvent actionEvent) {
    }
}


