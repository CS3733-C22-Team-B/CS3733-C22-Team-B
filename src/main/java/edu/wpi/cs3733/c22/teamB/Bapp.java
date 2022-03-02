package edu.wpi.cs3733.c22.teamB;

import edu.wpi.cs3733.c22.teamB.controllers.AnchorHomeController;
import edu.wpi.cs3733.c22.teamB.entity.*;
import java.io.IOException;
import java.util.List;


import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.MongoDB.MongoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

        DatabaseWrapper db = DatabaseWrapper.getInstance();
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
        scene.getStylesheets().add("/edu/wpi/cs3733/c22/teamB/styles/style.css");
        Bapp.getPrimaryStage().setMinHeight(600);
        Bapp.getPrimaryStage().setMinWidth(900);
        Bapp.getPrimaryStage().getIcons().add(new Image("/edu/wpi/cs3733/c22/teamB/images/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        log.info("Shutting Down");
        if (AnchorHomeController.visionThread != null && AnchorHomeController.visionThread.isAlive()) {
            AnchorHomeController.visionThread.stop();
        }
        DatabaseWrapper.getInstance().closeConnection();
    }
}
