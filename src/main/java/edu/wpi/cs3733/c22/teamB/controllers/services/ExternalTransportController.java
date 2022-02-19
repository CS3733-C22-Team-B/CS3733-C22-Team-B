package edu.wpi.cs3733.c22.teamB.controllers.services;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.controllers.IController;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ExternalTransportSR;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ExternalTransportController implements IController {
    @FXML TextField patientID;
    @FXML TextField DestinationTxt;
    @FXML JFXComboBox<String> FormOfTransport;

    private ExternalTransportSR sr = null;

    public ExternalTransportController() {}
    public ExternalTransportController(ExternalTransportSR sr) {this.sr = sr;}

    @FXML
    private void initialize() {
        String[] st = {"Car", "Helicopter", "Ambulance", "Wheelchair", "Plane", "Boat", "Spaceship"};
        FormOfTransport.getItems().addAll(st);
        if (sr == null)
            clear();
        else {
            FormOfTransport.setValue(sr.getFormOfTransport());
            System.out.println(sr);
            patientID.setText(sr.getPatientID());
            DestinationTxt.setText(sr.getDropOffLocation());
        }
    }


    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        if (this.sr == null)
            dw.addSR(new ExternalTransportSR(sr,patientID.getText(), DestinationTxt.getText(), FormOfTransport.getValue()));
        else
            dw.updateSR(new ExternalTransportSR(sr,patientID.getText(), DestinationTxt.getText(), FormOfTransport.getValue()));
    }

    @Override
    public void clear() {
        patientID.clear();
        DestinationTxt.clear();
        FormOfTransport.setValue(null);
    }
}
