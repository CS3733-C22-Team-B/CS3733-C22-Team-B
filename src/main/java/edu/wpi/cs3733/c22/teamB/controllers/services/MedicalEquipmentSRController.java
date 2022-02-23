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
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicalEquipmentSR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.StringConverter;

public class MedicalEquipmentSRController implements IController {
    @FXML private JFXComboBox<String> equipmentTypeField;
    private AutoCompleteComboBox<String> equipmentTypeAC;
    @FXML private JFXComboBox<MedicalEquipment> equipmentNameField;
    private AutoCompleteComboBox<MedicalEquipment> equipmentNameAC;

    private List<MedicalEquipment> medEqpList;

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

        List<String> equipmentTypeFieldList = new ArrayList<>();
        equipmentTypeFieldList.add("ALL");
        equipmentTypeFieldList.addAll(medEqpList.stream().map(MedicalEquipment::getEquipmentType).collect(Collectors.toSet()));
        equipmentTypeField.getItems().addAll(equipmentTypeFieldList);

        equipmentNameField.setConverter(new StringConverter<>() {
            @Override
            public String toString(MedicalEquipment object) {
                return object != null ? (object.getEquipmentID() + ' ' + object.getEquipmentName()) : "";
            }
            @Override
            public MedicalEquipment fromString(String string) {
                return null;
            }
        });

        if (sr == null) {
            clear();
        } else {
            equipmentTypeField.setValue(sr.getMedicalEquipment().getEquipmentType());
            equipmentNameField.setValue(sr.getMedicalEquipment());
        }
        equipmentNameAC = new AutoCompleteComboBox<>(equipmentNameField, medEqpList
                .stream()
                .filter(
                        lstr -> equipmentTypeField.getValue().equals("ALL")
                                || lstr.getEquipmentType().equals(equipmentTypeField.getValue()))
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
            dw.addSR(new MedicalEquipmentSR(sr, equipmentNameField.getValue()));
        else
            dw.updateSR(new MedicalEquipmentSR(sr, equipmentNameField.getValue()));
    }

    @Override
    public void clear() {
        equipmentTypeField.setValue("ALL");
        equipmentNameField.setValue(null);
    }

    @FXML private void onEquipmentTypeChange(ActionEvent actionEvent) {
//        equipmentNameField.getItems().clear();
//        equipmentNameField.getItems().removeAll();
        equipmentNameAC.updateData(medEqpList
                .stream()
                .filter(
                        lstr -> equipmentTypeField.getValue().equals("ALL")
                                || lstr.getEquipmentType().equals(equipmentTypeField.getValue()))
                .collect(Collectors.toList()));
        equipmentNameField.setValue(null);
        equipmentNameField.getEditor().setText(null);
    }
}
