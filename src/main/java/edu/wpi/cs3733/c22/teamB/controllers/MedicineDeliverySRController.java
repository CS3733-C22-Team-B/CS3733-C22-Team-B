package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MedicineDeliverySRController implements IController, Initializable {
    @FXML private TextField patientID;
    @FXML private TextField medicineID;

    private MedicineDeliverySR sr = null;

    public MedicineDeliverySRController(){}

    public MedicineDeliverySRController(MedicineDeliverySR sr){
        this.sr =sr;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (sr == null) {
            clear();
        } else {
            patientID.setText(sr.getPatientID());
            medicineID.setText(sr.getMedicineID());
        }
    }


    @Override
    public void submit() {

    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        if (this.sr == null)
            dw.addSR(new MedicineDeliverySR(sr, medicineID.getText(), patientID.getText()));
        else
            dw.updateSR(new MedicineDeliverySR(sr, medicineID.getText(), patientID.getText()));
    }

    @Override
    public void clear() {
        patientID.clear();
        medicineID.clear();
    }

}
