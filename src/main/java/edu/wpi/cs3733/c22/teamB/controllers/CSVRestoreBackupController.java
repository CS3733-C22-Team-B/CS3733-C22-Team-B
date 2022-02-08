package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CSVRestoreBackupController {
    @FXML
    JFXButton HomeB;
    @FXML
    JFXButton RestoreB;
    @FXML
    JFXButton BackupB;

//    File filePathLocation;
//    File filePathEmployee;
//    File filePathExtTrans;
//    File filePathFood;
//    File filePathEq;
//    File filePathEqSR;
//    File filePathMedSR;

    LocationParserI locParser = new LocationParserI();
    EmployeeParserI employeeParserI = new EmployeeParserI();
    ExternalTransportSRParserI extTransSRParserI = new ExternalTransportSRParserI();
    FoodDeliveryParserI foodDeliveryParserI = new FoodDeliveryParserI();
    MedicalEquipmentSRParserI medicalEquipmentSRParserI = new MedicalEquipmentSRParserI();
    MedicalEquipmentParserI medicalEquipmentParserI = new MedicalEquipmentParserI();
    MedicineDeliverySRParserI medicineDeliverySRParserI = new MedicineDeliverySRParserI();

    DatabaseManager databaseManager = new DatabaseManager();

    public void Backup() throws FileNotFoundException {
        MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
        MedicalEquipmentSRDBI medicalEquipmentSRDBI = new MedicalEquipmentSRDBI();
//        GiftFloralSRDBI giftFloralSRDBI = new GiftFloralSRDBI();
        FoodDeliverySRDBI foodDeliverySRDBI = new FoodDeliverySRDBI();
        MedicineDeliverySRDBI medicineDeliverySRDBI = new MedicineDeliverySRDBI();
        ExternalTransportSRDBI externalTransportSRDBI = new ExternalTransportSRDBI();
        EmployeeDBI employeeDBI = new EmployeeDBI();
        LocationDBI locationDBI = new LocationDBI();

        CSVWriter2 writer = new CSVWriter2("TowerLocationsB");

        // Backup Location table
        writer.backupDir("TowerLocationsB");
        writer.writeAll(locParser.fromObjectsToStrings(locationDBI.getAllNodes()));

        // Backup Employee table
        writer.backupDir("EmployeeB");
        writer.writeAll(employeeParserI.fromObjectsToStrings(employeeDBI.getAllNodes()));

        // Backup MedicalEquipment table
        writer.backupDir("MedicalEquipmentB");
        writer.writeAll(medicalEquipmentParserI.fromObjectsToStrings(medicalEquipmentDBI.getAllNodes()));

        // Backup ExternalTransportSR table
        writer.backupDir("ExternalTransportSRB");
        writer.writeAll(extTransSRParserI.fromObjectsToStrings(externalTransportSRDBI.getAllNodes()));

        // Backup MedicineDeliverySR table
        writer.backupDir("MedicineDeliverySRB");
        writer.writeAll(medicineDeliverySRParserI.fromObjectsToStrings(medicineDeliverySRDBI.getAllNodes()));

        // Backup FoodDeliverySR table
        writer.backupDir("FoodDeliverySRB");
        writer.writeAll(foodDeliveryParserI.fromObjectsToStrings(foodDeliverySRDBI.getAllNodes()));

        // Backup MedicalEquipmentSR table
        writer.backupDir("MedicalEquipmentSRB");
        writer.writeAll(medicalEquipmentSRParserI.fromObjectsToStrings(medicalEquipmentSRDBI.getAllNodes()));

    }
    public void Restore() {
        //call DB restore here
//        String pathString = new File("").getAbsolutePath();
//        File f = new File(pathString);
//
//        File backDir = new File(f.getAbsolutePath() + "/backup");
//
//        File filePathLocation = new File(backDir.getAbsolutePath() + "/TowerLocationsB.csv");
//
//        File filePathEmployee = new File(backDir.getAbsolutePath() + "/EmployeeB.csv");
//
//        File filePathExtTrans = new File(backDir.getAbsolutePath() + "/ExternalTransportSRB.csv");
//
//        File filePathFood = new File(backDir.getAbsolutePath() + "/FoodDeliverySRB.csv");
//
//        File filePathEq = new File(backDir.getAbsolutePath() + "/MedicalEquipmentB.csv");
//
//        File filePathEqSR = new File(backDir.getAbsolutePath() + "/MedicalEquipmentSRB.csv");
//
//        File filePathMedSR = new File(backDir.getAbsolutePath() + "/MedicineDeliverySRB.csv");

    }
    // Go to the home fxml when the home button is pressed
    @FXML
    void goToHome(ActionEvent event) {
        // Try to go home
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
            // Print stack trace if unable to go home
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
