package edu.wpi.cs3733.c22.teamB;

import edu.wpi.cs3733.c22.teamB.entity.*;
import java.io.IOException;
import java.util.List;


import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.MongoDB.MongoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bapp extends Application {
    private static Stage _primaryStage;

    public static Stage getPrimaryStage() { return _primaryStage; }

    @Override
    public void init() throws IOException {
        log.info("Starting Up");
//        MongoDB.getConnection();

        DatabaseWrapper db = new DatabaseWrapper();
        db.isFirstRestore();

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Login.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/ServiceRequestMenu.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/ServiceRequestManager.fxml"));
        _primaryStage = primaryStage;

        Parent root = loader.load();
        primaryStage.setTitle("Bapp - Home Page");
        Scene scene = new Scene(root);
        primaryStage.setResizable(true);
        scene.getStylesheets().add("/edu/wpi/cs3733/c22/teamB/styles/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        log.info("Shutting Down");
    }
}
