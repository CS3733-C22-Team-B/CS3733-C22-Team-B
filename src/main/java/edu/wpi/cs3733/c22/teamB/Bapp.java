package edu.wpi.cs3733.c22.teamB;

import java.io.IOException;
import java.util.List;

import edu.wpi.cs3733.c22.teamB.entity.*;
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

    public static Stage getPrimaryStage() { return _primaryStage; }

    @Override
    public void init() throws IOException {
        log.info("Starting Up");

        LocationDBI locationDBI = new LocationDBI();
        locationDBI.createTable();

        EmployeeDBI employeeDBI = new EmployeeDBI();
        employeeDBI.createTable();

        MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
        medicalEquipmentDBI.createTable();

        MedicalEquipmentSRDBI medicalEquipmentSRDBI = new MedicalEquipmentSRDBI();
        medicalEquipmentSRDBI.createTable();

        GiftFloralSRDBI giftFloralSRDBI = new GiftFloralSRDBI();
        giftFloralSRDBI.createTable();

        FoodDeliverySRDBI foodDeliverySRDBI = new FoodDeliverySRDBI();
        foodDeliverySRDBI.createTable();

        MedicineDeliverySRDBI medicineDeliverySRDBI = new MedicineDeliverySRDBI();
        medicineDeliverySRDBI.createTable();

        ExternalTransportSRDBI externalTransportSRDBI = new ExternalTransportSRDBI();
        externalTransportSRDBI.createTable();

        LocationParserI locParser = new LocationParserI();
        CSVReader2 reader = new CSVReader2();

//        List<String> stringList = reader.firstRestore("TowerLocationsB.csv");
//        locationDBI.restore(locParser.fromStringsToObjects(stringList));
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Login.fxml"));
        _primaryStage = primaryStage;

        Parent root = loader.load();
        primaryStage.setTitle("Bapp - Home Page");
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        scene.getStylesheets().add("/edu/wpi/cs3733/c22/teamB/styles/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        log.info("Shutting Down");
    }
}
