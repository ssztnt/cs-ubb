package com.example.project;

import com.example.project.controller.LoginController;
import com.example.project.domain.TypeEnum;
import com.example.project.domain.User;
import com.example.project.repo.BugsRepository;
import com.example.project.repo.UserRepository;
import com.example.project.service.Service;
import javafx.application.Application;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Properties props = new Properties();
        try {
         //   props.load(new FileReader("bd.config"));
            props.load(StartApplication.class.getResourceAsStream("bd.properties"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        UserRepository userRepository = new UserRepository(props);
        BugsRepository bugsRepository = new BugsRepository(props);
        Service srv = new Service(userRepository,bugsRepository);

       FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 440, 400);
        stage.setTitle("LogIn");
        stage.setScene(scene);
        LoginController controller = fxmlLoader.getController();
        controller.setService(srv);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
