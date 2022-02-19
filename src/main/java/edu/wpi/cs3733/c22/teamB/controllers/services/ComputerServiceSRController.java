package edu.wpi.cs3733.c22.teamB.controllers.services;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.controllers.IController;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ComputerServiceSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

public class ComputerServiceSRController implements IController {
    @FXML private JFXComboBox<String> helpTypeField;

    private ComputerServiceSR sr = null;

    public ComputerServiceSRController() {}

    public ComputerServiceSRController(ComputerServiceSR sr) {
        this.sr = sr;
    }

    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        if (this.sr == null)
            dw.addSR(new ComputerServiceSR(sr, helpTypeField.getValue()));
        else
            dw.updateSR(new ComputerServiceSR(sr, helpTypeField.getValue()));
    }

    @Override
    public void clear() {
        helpTypeField.setValue(null);
    }


    @FXML public void initialize() {
        String st[] = {"Hardware", "Software"};
        helpTypeField.setItems(FXCollections.observableArrayList(st));
        if (sr == null)
            clear();
        else
            helpTypeField.setValue(sr.getHelpType());
    }
}
