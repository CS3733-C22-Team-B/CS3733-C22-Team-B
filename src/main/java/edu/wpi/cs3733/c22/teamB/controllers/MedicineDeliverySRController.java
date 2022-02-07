package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicineDeliverySRController implements IController, Initializable {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField patientID;
    @FXML private TextField DOB;
    @FXML private TextField email;
//    TODO add allergies
    @FXML private TextField dosage;
    @FXML private TextField medicineName;
    @FXML private TextField dispenseAmount;
    @FXML private TextField frequency;
    @FXML private TextField form;
    @FXML private TextField mgPerDose;
    @FXML private JFXComboBox<String> roomNumber;



    private MedicineDeliverySRDBI medicineDeliverySRDBI = new MedicineDeliverySRDBI();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void submit() {
//        medicineDeliverySRDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
//        medicineDeliverySRDBI.closeConnection();
        MedicineDeliverySR medicineDeliverySR = new MedicineDeliverySR(
                "1",
                "WAITING",
                new Location(),
                new Medicine(),
                new Employee(),
                firstName.getText(),
                lastName.getText(),
                patientID.getText(),
                DOB.getText(),
                email.getText(),
                roomNumber.getValue(),
                dosage.getText(),
                medicineName.getText(),
                dispenseAmount.getText(),
                frequency.getText(),
                form.getText(),
                mgPerDose.getText());
        System.out.println(medicineDeliverySR.toString());
    }


    @Override
    public void clear() {
        firstName.clear();
        lastName.clear();
        patientID.clear();
        DOB.clear();
        email.clear();
        dosage.clear();
        medicineName.clear();;
        dispenseAmount.clear();
        frequency.clear();
        form.clear();
        mgPerDose.clear();
        roomNumber.setValue("");
    }

}
