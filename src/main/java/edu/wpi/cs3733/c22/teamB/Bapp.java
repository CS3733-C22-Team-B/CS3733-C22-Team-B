package edu.wpi.cs3733.c22.teamB;

import java.io.IOException;
import java.util.List;

import edu.wpi.cs3733.c22.teamB.oldEntity.*;

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

//        DatabaseManager databaseManager = new DatabaseManager();
//        databaseManager.createTable();

        CSVReader2 reader = new CSVReader2();

        List<String> locationList = reader.firstRestore("TowerLocationsB.csv");
        List<String> employeeList = reader.firstRestore("EmployeeB.csv");
        List<String> externalTransportList = reader.firstRestore("ExternalTransportSRB.csv");
        List<String> foodDeliveryList = reader.firstRestore("FoodDeliverySRB.csv");
        List<String> equipmentList = reader.firstRestore("MedicalEquipmentB.csv");
        List<String> equipmentSRList = reader.firstRestore("MedicalEquipmentSRB.csv");
        List<String> medicineDeliveryList = reader.firstRestore("MedicineDeliverySRB.csv");

        LocationParserI locParser = new LocationParserI();
        EmployeeParserI employeeParserI = new EmployeeParserI();
        //ExternalTransportSRParserI extTransSRParserI = new ExternalTransportSRParserI();
        FoodDeliveryParserI foodDeliveryParserI = new FoodDeliveryParserI();
        MedicalEquipmentSRParserI medicalEquipmentSRParserI = new MedicalEquipmentSRParserI();
        MedicalEquipmentParserI medicalEquipmentParserI = new MedicalEquipmentParserI();
        MedicineDeliverySRParserI medicineDeliverySRParserI = new MedicineDeliverySRParserI();

        List<Location> locationList1 = locParser.fromStringsToObjects(locationList);
        List<Employee> employeeList1 = employeeParserI.fromStringsToObjects(employeeList);
        //List<ExternalTransportSR> externalTransportSRList1 = extTransSRParserI.fromStringsToObjects(externalTransportList);
        List<FoodDeliverySR> foodDeliverySRList1 = foodDeliveryParserI.fromStringsToObjects(foodDeliveryList);
        List<MedicalEquipment> medicalEquipmentList1 = medicalEquipmentParserI.fromStringsToObjects(equipmentList);
        List<MedicalEquipmentSR> medicalEquipmentSRList1 = medicalEquipmentSRParserI.fromStringsToObjects(equipmentSRList);
        List<MedicineDeliverySR> medicineDeliverySRList1 = medicineDeliverySRParserI.fromStringsToObjects(medicineDeliveryList);

        //databaseManager.restoreTables(locationList1, employeeList1, externalTransportSRList1, medicineDeliverySRList1,
        //        foodDeliverySRList1, medicalEquipmentSRList1, medicalEquipmentList1);

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
