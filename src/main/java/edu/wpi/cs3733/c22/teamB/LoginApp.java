package edu.wpi.cs3733.c22.teamB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginApp extends Application {
    private static Stage _primaryStage;

    public static Stage getPrimaryStage() {
        return _primaryStage;
    }

    @Override
    public void init() throws IOException {
        log.info("Starting Up");
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(LoginApp.class.getResource("views/Login.fxml"));
        _primaryStage = primaryStage;

        Parent root = loader.load();
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String[] login(){
        String[] login = new String[2];
        return login;
    }

    @Override
    public void stop() {
        log.info("Shutting Down");
    }
}
