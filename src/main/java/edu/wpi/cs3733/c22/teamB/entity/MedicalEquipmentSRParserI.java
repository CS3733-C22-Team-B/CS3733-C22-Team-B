package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalEquipmentSRParserI implements IParser<MedicalEquipmentSR> {

    @Override
    public MedicalEquipmentSR fromStringToObject(String string) {
        MedicalEquipmentSR medEqSR = new MedicalEquipmentSR();
        Location destination = new Location();
        MedicalEquipment medicalEquipment = new MedicalEquipment();
        Employee employee = new Employee();

        String[] data = string.split(",");

        medEqSR.setSrID(data[0]);

        medEqSR.setStatus(data[1]);

        // Set Location object nodeID
        destination.setNodeID(data[2]);

        // Pass Location object to setLocation
        medEqSR.setDestination(destination);

        // Set MedicalEquipment object equipmentID
        medicalEquipment.setEquipmentID(data[3]);

        // Pass MedicalEquipment object to setMedicalEquipment
        medEqSR.setMedicalEquipment(medicalEquipment);

        // Set Employee object employeeID
        employee.setEmployeeID(data[4]);

        // Pass Employee object to setAssignedEmployee
        medEqSR.setAssignedEmployee(employee);

        return medEqSR;
    }

    @Override
    public List<MedicalEquipmentSR> fromStringsToObjects(List<String> listString) {
        List<MedicalEquipmentSR> medEqSRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return medEqSRList;
    }

    @Override
    public String fromObjectToString(MedicalEquipmentSR medEqSR) {
        String str = medEqSR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<MedicalEquipmentSR> medEqSRList) {
        List<String> listString = new ArrayList<>();
        listString.add(MedicalEquipmentSR.toStringHeader());
        for (MedicalEquipmentSR medEqSR : medEqSRList) {
            listString.add(medEqSR.toStringFields());
        }

        return listString;
    }
}
