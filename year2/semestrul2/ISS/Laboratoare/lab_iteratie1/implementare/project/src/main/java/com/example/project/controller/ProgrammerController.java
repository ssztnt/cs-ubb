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

public class ProgrammerController implements Observer<BugTaskChangeEvent> {

    private Service service;
    private User user;

    @FXML
    private TextField programmer_name;
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
    private Button exit_button;

    public void setService(Service srv, User user)
    {
        this.service = srv;
        service.addObserver(this);
        this.user = user;
        programmer_name.setText(user.getUsername());
        programmer_name.setEditable(false);
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
            if (cellData.getValue() != null ) {
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

    public void resolve_bug() {
        Bug bug = bugs_list.getSelectionModel().getSelectedItem();
        if (bug == null) {
            UserActionsAlert.showMessage(null, Alert.AlertType.ERROR, "Error", "Please select a bug to resolve!");
            return;
        }
        if(bug.getStatus() == StatusType.RESOLVED) {
            UserActionsAlert.showMessage(null, Alert.AlertType.ERROR, "Error", "Please select an unresolved BUG!");
            return;
        }

        try {
            service.resolveBug(bug,this.user);
            service.notifyObservers(new BugTaskChangeEvent(ChangeEventType.UPDATE, null));
            UserActionsAlert.showMessage(null, Alert.AlertType.INFORMATION, "Message","Successfully done!");



        }
        catch(Exception e)
        {}

    }


    @Override
    public void update(BugTaskChangeEvent bugTaskChangeEvent) {
        initialize_table();
    }
}
