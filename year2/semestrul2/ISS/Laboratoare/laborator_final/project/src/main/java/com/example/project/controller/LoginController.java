package com.example.project.controller;

import com.example.project.ProgrammerApplication;
import com.example.project.TesterApplication;
import com.example.project.controller.alert.LoginActionAlert;
import com.example.project.domain.TypeEnum;
import com.example.project.domain.User;
import com.example.project.observer.Observer;
import com.example.project.service.Service;
import com.example.project.utils.BugTaskChangeEvent;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginController implements Observer<BugTaskChangeEvent> {

    private Service service;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<TypeEnum> function_box;

    @FXML
    private Button login;




    public void setService(Service service){
        this.service = service;
        //this.service.addObserver(this);
        set_combo_box();
    }

    public void handle_login(ActionEvent actionEvent) {
        String username_field = username.getText();
        String password_field = password.getText();
        TypeEnum type = function_box.getValue();
        if (password_field.isEmpty()) {
            LoginActionAlert.showMessage(null, Alert.AlertType.ERROR, "Error", "Eroare! Parola  nu poate sa fie nula!");
            return;
        }
        if(username_field.isEmpty())
        {
            LoginActionAlert.showMessage(null, Alert.AlertType.ERROR, "Error", "Eroare! Username nu poate sa fie nul!");
            return;
        }
        if (type == null) {
            LoginActionAlert.showMessage(null, Alert.AlertType.ERROR, "Error", "Error! Selecteaza o functie din tabel!");
            return;
        }


        User user = service.find_user_by_data(username_field, password_field,type);

        if(user == null) {
            LoginActionAlert.showMessage(null, Alert.AlertType.ERROR, "Error", "Eroare! Nu exista un cont asociat acestor date!");
            username.clear();
            password.clear();
            return;
        }
        else
        {

            System.out.println("gasit user");
            Long id_user = user.getId();
            Stage stage = new Stage();

            if (user.getType() == TypeEnum.PROGRAMMER)
            {
            FXMLLoader fxmlLoader = new FXMLLoader(ProgrammerApplication.class.getResource("programmer-view.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 600, 340);
                stage.setScene(scene);

                ProgrammerController adminController = fxmlLoader.getController();
                adminController.setService(service,user);
                stage.show();
            }
            catch (IOException e) {
                LoginActionAlert.showMessage(null, Alert.AlertType.ERROR, "Error", e.getMessage());
            }
            username.clear();
            password.clear();
            return;
            }
            else if (user.getType() == TypeEnum.TESTER){
                FXMLLoader fxmlLoader = new FXMLLoader(TesterApplication.class.getResource("tester-view.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load(), 590, 260);
                    stage.setScene(scene);

                    TesterController adminController = fxmlLoader.getController();
                    adminController.setService(service,user);
                    stage.show();
                }
                catch (IOException e) {
                    LoginActionAlert.showMessage(null, Alert.AlertType.ERROR, "Error", e.getMessage());
                }
                username.clear();
                password.clear();
                return;
            }
        }
    }

    public void set_combo_box()
    {
        function_box.setItems(FXCollections.observableArrayList(TypeEnum.values()));
    }

    @Override
    public void update(BugTaskChangeEvent bugTaskChangeEvent) {

    }
}
