package edu.wpi.cs3733.c22.teamB;

import java.io.IOException;
import java.util.Locale;

import edu.wpi.cs3733.c22.teamB.entity.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bapp extends Application {
    private static Stage _primaryStage;

    public static Stage getPrimaryStage() {
        return _primaryStage;
    }

    @Override
    public void init() {
        log.info("Starting Up");

//        LocationDBI locationDBI = new LocationDBI();
//        locationDBI.createTable();
//
//        MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
//        medicalEquipmentDBI.createTable();
//
//        EmployeeDBI employeeDBI = new EmployeeDBI();
//        employeeDBI.createTable();
//
//        MedicalEquipmentSRDBI medicalEquipmentSRDBI = new MedicalEquipmentSRDBI();
//        medicalEquipmentSRDBI.createTable();

        GiftFloralSRDBI giftFloralSRDBI = new GiftFloralSRDBI();
        giftFloralSRDBI.createTable();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Home.fxml"));
        _primaryStage = primaryStage;

        Parent root = loader.load();
        primaryStage.setTitle("Bapp - Home Page");
        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);
        scene.getStylesheets().add("/edu/wpi/cs3733/c22/teamB/styles/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        log.info("Shutting Down");
    }
}
