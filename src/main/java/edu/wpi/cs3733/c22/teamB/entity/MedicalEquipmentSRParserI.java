package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalEquipmentSRParserI implements IParser<MedicalEquipmentSR> {


    @Override
    public MedicalEquipmentSR fromStringToObject(String string) {
        MedicalEquipmentSR medicalEquipmentSR = new MedicalEquipmentSR();

        String[] data = string.split(",");

        medicalEquipmentSR.setSrID(data[0]);

        MedicalEquipment medicalEquipment = new MedicalEquipment();
        medicalEquipment.setEquipmentID(data[1]);

        medicalEquipmentSR.setMedicalEquipment(medicalEquipment);

        return medicalEquipmentSR;
    }

    @Override
    public List<MedicalEquipmentSR> fromStringsToObjects(List<String> listString) {
        List<MedicalEquipmentSR> medicalEquipmentSRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return medicalEquipmentSRList;
    }

    @Override
    public String fromObjectToString(MedicalEquipmentSR medicalEquipmentSR) {
        String str = medicalEquipmentSR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<MedicalEquipmentSR> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(MedicalEquipmentSR.toStringHeader());
        for (MedicalEquipmentSR medicalEquipmentSR : listT) {
            listString.add(medicalEquipmentSR.toStringFields());
        }

        return listString;
    }
}
