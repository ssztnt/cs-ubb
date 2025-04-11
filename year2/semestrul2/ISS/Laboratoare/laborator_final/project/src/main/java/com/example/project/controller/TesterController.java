package com.example.project.controller;

import com.example.project.controller.alert.UserActionsAlert;
import com.example.project.domain.Bug;
import com.example.project.domain.StatusType;
import com.example.project.domain.User;
import com.example.project.observer.Observer;
import com.example.project.service.Service;
import com.example.project.utils.BugTaskChangeEvent;
import com.example.project.utils.ChangeEventType;
import com.example.project.utils.TaskChangeEvent;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TesterController  implements Observer<BugTaskChangeEvent> {
    private Service service;
    private User user;

    ObservableList<Bug> model = FXCollections.observableArrayList();

    @FXML
    public TableView<Bug> bugs_list;
    @FXML
    public TableColumn<Bug, String> name;
    @FXML
    public TableColumn<Bug, String> description;
    @FXML
    public TableColumn<Bug, String> status;
    @FXML
    public TableColumn<Bug, String> resolved_by;
    @FXML
    private TextField tester_name;

    @FXML
    private TextField add_name;
    @FXML
    private TextArea add_description;

    @FXML
    private Button add_button;
    @FXML
    private Button exit_button;
    public void setService(Service srv,User user)
    {
        this.service = srv;
        service.addObserver(this);
        this.user = user;
        tester_name.setText(user.getUsername());
        tester_name.setEditable(false);
        initialize_table();
    }

    public void handle_exit(){
        Node src = exit_button;
        Stage stage = (Stage) src.getScene().getWindow();
        stage.close();
    }
    public void initialize_table() {
        try {
            Iterable<Bug> bugs = service.findAllBugs();
            List<Bug> bugsList = StreamSupport.stream(bugs.spliterator(), false)
                    .collect(Collectors.toList());

            ObservableList<Bug> allBugs = FXCollections.observableArrayList(bugsList);
            bugs_list.setItems(allBugs);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void initialize() {

        name.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null) {
                String nameText = cellData.getValue().getName().toString();
                return new SimpleObjectProperty<>(nameText);
            }
            return null;
        });

        description.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null) {
                String distanceText = cellData.getValue().getDescription().toString();
                return new SimpleObjectProperty<>(distanceText);
            }
            return null;
        });
        status.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null) {
                String statusText = cellData.getValue().getStatus().toString();
                return new SimpleObjectProperty<>(statusText);
            }
            return null;
        });
        resolved_by.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null && cellData.getValue().getStatus().toString().equals("RESOLVED"))  {
                Long id_programmer = cellData.getValue().getResolved_by();
                User programmer = service.find_Programmer(id_programmer);
                return new SimpleObjectProperty<>(programmer.getUsername());
            }
            return null;
        });


        bugs_list.setItems(model);
    }


    public void add_bug() {
        String name_bug = add_name.getText();
        String description_bug = add_description.getText();

        if(name_bug.isEmpty() || description_bug.isEmpty())
        {
            UserActionsAlert.showMessage(null, Alert.AlertType.ERROR, "Error", "Error! The fields have to be filled!");
            add_name.clear();
            add_description.clear();
            return;
        }
        Bug bug = new Bug(name_bug,description_bug, StatusType.UNRESOLVED,null);
        try {
            service.addBug(bug);
            add_name.clear();
            add_description.clear();
            service.notifyObservers(new BugTaskChangeEvent(ChangeEventType.UPDATE,bug));
            UserActionsAlert.showMessage(null, Alert.AlertType.INFORMATION, "Message","Successfully done!");


        }
        catch (Exception e)
        {
            UserActionsAlert.showMessage(null, Alert.AlertType.ERROR, "Error", e.getMessage());
        }

    }

    @Override
    public void update(BugTaskChangeEvent taskChangeEvent) {
        initialize_table();
    }
}
