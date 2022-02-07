package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalEquipmentParserI implements IParser<MedicalEquipment> {

    @Override
    public MedicalEquipment fromStringToObject(String string) {
        MedicalEquipment medEq = new MedicalEquipment();
        Location location = new Location();

        String[] data = string.split(",");

        medEq.setEquipmentID(data[0]);
        medEq.setEquipmentName(data[1]);
        medEq.setEquipmentType(data[2]);
        medEq.setManufacturer(data[3]);

        // Set location object nodeID
        location.setNodeID(data[4]);

        // Pass location object to setLocation
        medEq.setLocation(location);

        medEq.setStatus(data[5]);
        medEq.setColor(data[6]);
        medEq.setSize(data[7]);
        medEq.setDescription(data[8]);

        return medEq;
    }

    @Override
    public List<MedicalEquipment> fromStringsToObjects(List<String> listString) {
        List<MedicalEquipment> medEqList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return medEqList;
    }

    @Override
    public String fromObjectToString(MedicalEquipment medEq) {
        String str = medEq.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<MedicalEquipment> medEqList) {
        List<String> listString = new ArrayList<>();
        for (MedicalEquipment medEq : medEqList) {
            listString.add(medEq.toStringFields());
        }

        return listString;
    }
}
