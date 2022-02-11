package edu.wpi.cs3733.c22.teamB.oldEntity;

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

        // main table
//        AbstractSR sr = Factory.createSR(data[1]); // creates XSR according to srType argument
//        sr.setSrID();
//        sr.setStatus();
//
//        // Factory.createSR(type) {
////        if type == "MEdicalEquipmentSR":
////        return new MedicalEquipmentSR();
////    }
//
//        //
//        sr = fromStringToObject();
//        maindb.insert(sr);
//
//        // maindb.insert()
//        getsrID;
//        getType;
//        getlocation;
//
//        // by this time main table is populated
//        // now populate medeqpsr table:
//        // csv: srID,equipment
//        MedicalEquipmentSR sr = new MedicalEquipmentSR(data[0], data[1]); // new constructor
//        medeqprsdb.insert(sr);
//
//        // restore()
//        sr.getID();
//        sr.getMedicalEquipment();
//
//        // process: load main.csv -> load xsr.csv
//        // main csv
//        // id  status  location
//        //
//
//        // medequipsr table
//        // id  equipment


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
