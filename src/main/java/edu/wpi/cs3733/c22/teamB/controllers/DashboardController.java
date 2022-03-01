package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.AutoCompleteComboBox;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import javafx.fxml.FXML;

import java.util.stream.Collectors;

public class DashboardController {

    @FXML private JFXComboBox<String> testComboBox;
    @FXML private JFXComboBox<String> cmb;
    private AutoCompleteComboBox<String> cb;

    @FXML
    public void initialize() {
        cb = new AutoCompleteComboBox<>(testComboBox, (DatabaseWrapper.getInstance()).getAllLocation().stream().map(location -> location.getLongName()).collect(Collectors.toList()));

        cmb.getItems().addAll((DatabaseWrapper.getInstance()).getAllLocation().stream().map(location -> location.getLongName()).collect(Collectors.toList()));
    }
}
