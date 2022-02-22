package edu.wpi.cs3733.c22.teamB.controllers.services;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.AutoCompleteComboBox;
import edu.wpi.cs3733.c22.teamB.controllers.IController;
import edu.wpi.cs3733.c22.teamB.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicalEquipmentSR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MedicalEquipmentSRController implements IController {
    @FXML private JFXComboBox<String> equipmentTypeField;
    private AutoCompleteComboBox<String> equipmentTypeAC;
    @FXML private JFXComboBox<String> equipmentNameField;
    private AutoCompleteComboBox<String> equipmentNameAC;

    private List<MedicalEquipment> medEqpList;
    private Map<String, MedicalEquipment> medEqpMap;
    private int nextID;

    // Important: 3 lines below are necessary
    private MedicalEquipmentSR sr = null;

    // Important: you MUST have 2 constructors - including the default one
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
//        equipmentTypeField.getItems().add("ALL");
//        equipmentTypeField.getItems().addAll(medEqpMap.values().stream().map(MedicalEquipment::getEquipmentType).collect(Collectors.toSet()));
        List<String> equipmentTypeFieldList = new ArrayList<>();
        equipmentTypeFieldList.add("ALL");
        equipmentTypeFieldList.addAll(medEqpMap.values().stream().map(MedicalEquipment::getEquipmentType).collect(Collectors.toSet()));
        equipmentTypeAC = new AutoCompleteComboBox<>(equipmentTypeField, equipmentTypeFieldList);

        if (sr == null) {
            clear();
        } else {
            equipmentTypeField.setValue(sr.getMedicalEquipment().getEquipmentType());
            equipmentNameField.setValue(sr.getMedicalEquipment().getEquipmentID() + ' ' + sr.getMedicalEquipment().getEquipmentName());
        }
//        equipmentNameField.getItems().addAll(medEqpMap.keySet()
//                .stream()
//                .filter(
//                        lstr -> equipmentTypeField.getValue().equals("ALL")
//                                || medEqpMap.get(lstr).getEquipmentType().equals(equipmentTypeField.getValue()))
//                .collect(Collectors.toList()));
        equipmentNameAC = new AutoCompleteComboBox<>(equipmentNameField, medEqpMap.keySet()
                .stream()
                .filter(
                        lstr -> equipmentTypeField.getValue().equals("ALL")
                                || medEqpMap.get(lstr).getEquipmentType().equals(equipmentTypeField.getValue()))
                .collect(Collectors.toList()));
    }

    @Override
    public void submit() {
    }

    // Use this submit with argument
    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        if (this.sr == null)
            dw.addSR(new MedicalEquipmentSR(sr, medEqpMap.get(equipmentNameField.getValue())));
        else
            dw.updateSR(new MedicalEquipmentSR(sr, medEqpMap.get(equipmentNameField.getValue())));
    }

    @Override
    public void clear() {
        equipmentTypeField.setValue("ALL");
        equipmentNameField.setValue(null);
        equipmentNameField.getEditor().setText(null);
    }

    @FXML private void onEquipmentTypeChange(ActionEvent actionEvent) {
        equipmentNameField.getItems().clear();
        equipmentNameField.getItems().removeAll();
        equipmentNameField.getItems().addAll(medEqpMap.keySet()
                .stream()
                .filter(
                        lstr -> equipmentTypeField.getValue().equals("ALL")
                                || medEqpMap.get(lstr).getEquipmentType().equals(equipmentTypeField.getValue()))
                .collect(Collectors.toList()));
        equipmentNameField.setValue(null);
        equipmentNameField.getEditor().setText(null);
    }
}
