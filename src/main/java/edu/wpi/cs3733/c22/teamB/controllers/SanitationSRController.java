package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SanitationSRController implements IController {
    @FXML private JFXComboBox<String> conditionField;

    private SanitationSR sr = null;

    // Important: you MUST have 2 constructors - including the default one
    public SanitationSRController() {}
    public SanitationSRController(SanitationSR sr) {
        this.sr = sr;
    }

    @FXML
    public void initialize() {
        conditionField.getItems().addAll("DRY","WET","GLASS");
        if (sr == null) {
            clear();
        } else {
            conditionField.setValue(sr.getCondition());
        }
    }

    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        if (this.sr == null)
            dw.addSR(new SanitationSR(sr, conditionField.getValue()));
        else
            dw.updateSR(new SanitationSR(sr, conditionField.getValue()));
    }

    @Override
    public void clear() {
        conditionField.setValue(null);
    }
}
