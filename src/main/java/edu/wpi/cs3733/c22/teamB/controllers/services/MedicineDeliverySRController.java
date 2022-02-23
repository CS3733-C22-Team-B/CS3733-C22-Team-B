package edu.wpi.cs3733.c22.teamB.controllers.services;

import edu.wpi.cs3733.c22.teamB.controllers.IController;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicineDeliverySR;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
        DatabaseWrapper dw = DatabaseWrapper.getInstance();
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
