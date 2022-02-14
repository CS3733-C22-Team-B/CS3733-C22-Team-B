package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class MedicalEquipmentSRController implements IController {
    @FXML private JFXComboBox<String> equipmentTypeField;
    @FXML private JFXComboBox<String> equipmentNameField;

    private List<MedicalEquipment> medEqpList;
    private Map<String, MedicalEquipment> medEqpMap;
    private int nextID;

    // Important: 3 lines below are necessary
    private MedicalEquipmentSR sr = null;

    public MedicalEquipmentSRController() {}

    public MedicalEquipmentSRController(MedicalEquipmentSR sr) {
        this.sr = sr;
    }

    @FXML
    public void initialize() {
        DatabaseWrapper dw = new DatabaseWrapper();
        medEqpList = dw.getAllMedicalEquipment();
        medEqpMap =
                IntStream.range(0, medEqpList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i ->
                                                (medEqpList.get(i).getEquipmentID()
                                                + ' '
                                                + medEqpList.get(i).getEquipmentName()),
                                        i -> medEqpList.get(i)));
        equipmentTypeField.getItems().add("ALL");
        equipmentTypeField.getItems().addAll(medEqpMap.values().stream().map(MedicalEquipment::getEquipmentType).collect(Collectors.toSet()));
        if (sr == null) {
            clear();
        } else {
            equipmentTypeField.setValue(sr.getMedicalEquipment().getEquipmentType());
            equipmentNameField.setValue(sr.getMedicalEquipment().getEquipmentName());
        }
        equipmentNameField.getItems().addAll(medEqpMap.keySet()
                .stream()
                .filter(
                        lstr -> equipmentTypeField.getValue().equals("ALL")
                                || medEqpMap.get(lstr).getEquipmentType().equals(equipmentTypeField.getValue()))
                .collect(Collectors.toList()));
    }

    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        dw.addSR(new MedicalEquipmentSR(sr, medEqpMap.get(equipmentNameField.getValue())));
    }

    @Override
    public void clear() {
        equipmentTypeField.setValue("ALL");
        equipmentNameField.setValue(null);
    }

    @FXML private void onEquipmentTypeChange(ActionEvent actionEvent) {
        equipmentNameField.setValue(null);
        equipmentNameField.getItems().removeAll();
        equipmentNameField.getItems().clear();
        equipmentNameField.getItems().addAll(medEqpMap.keySet()
                .stream()
                .filter(
                        lstr -> equipmentTypeField.getValue().equals("ALL")
                                || medEqpMap.get(lstr).getEquipmentType().equals(equipmentTypeField.getValue()))
                .collect(Collectors.toList()));
    }
}
