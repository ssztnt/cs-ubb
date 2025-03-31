package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Model.Concurs;
import org.example.Service.AtletismService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConcursListController implements Initializable {

    @FXML private TableView<Concurs> concursTable;
    @FXML private TableColumn<Concurs, String> nameColumn;
    @FXML private TableColumn<Concurs, String> dateColumn;
    @FXML private TableColumn<Concurs, String> locationColumn;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("locatie"));
    }
}


