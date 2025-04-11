package com.example.project;

import com.example.project.controller.LoginController;
import com.example.project.controller.TesterController;
import com.example.project.repo.BugsRepository;
import com.example.project.repo.UserRepository;
import com.example.project.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TesterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Properties props = new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        UserRepository userRepository = new UserRepository(props);
        BugsRepository bugsRepository = new BugsRepository(props);
        Service srv = new Service(userRepository,bugsRepository);

        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("tester-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 260);
        stage.setTitle("Hello tester!");
        stage.setScene(scene);
        TesterController controller = fxmlLoader.getController();
      //  controller.setService(srv);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
