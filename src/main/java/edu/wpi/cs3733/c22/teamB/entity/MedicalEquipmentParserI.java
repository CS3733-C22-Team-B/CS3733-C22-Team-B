package edu.wpi.cs3733.c22.teamB.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalEquipmentParserI implements IParser<MedicalEquipment> {

    @Override
    public MedicalEquipment fromStringToObject(String string) {
        MedicalEquipment medicalEquipment = new MedicalEquipment();
        Location location = new Location();

        String[] data = string.split(",");

        medicalEquipment.setEquipmentID(data[0]);
        medicalEquipment.setEquipmentName(data[1]);
        medicalEquipment.setEquipmentType(data[2]);
        medicalEquipment.setManufacturer(data[3]);

        // Set location object nodeID
        location.setNodeID(data[4]);

        // Pass location object to setLocation
        medicalEquipment.setLocation(location);

        medicalEquipment.setStatus(data[5]);
        medicalEquipment.setColor(data[6]);
        medicalEquipment.setSize(data[7]);
        medicalEquipment.setDescription(data[8]);
        medicalEquipment.setAmount(Integer.parseInt(data[9]));

        return medicalEquipment;
    }

    @Override
    public List<MedicalEquipment> fromStringsToObjects(List<String> listString) {
        List<MedicalEquipment> medicalEquipmentList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return medicalEquipmentList;
    }

    @Override
    public String fromObjectToString(MedicalEquipment medicalEquipment) {
        String str = medicalEquipment.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<MedicalEquipment> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(MedicalEquipment.toStringHeader());
        for (MedicalEquipment medicalEquipment : listT) {
            listString.add(medicalEquipment.toStringFields());
        }

        return listString;
    }
}
