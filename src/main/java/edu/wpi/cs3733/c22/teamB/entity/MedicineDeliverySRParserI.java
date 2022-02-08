package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicineDeliverySRParserI implements IParser<MedicineDeliverySR> {

    @Override
    public MedicineDeliverySR fromStringToObject(String string) {
        MedicineDeliverySR medicineDeliverySR = new MedicineDeliverySR();
        Location destination = new Location();
        Employee assignedEmployee = new Employee();


        String[] data = string.split(",");

        medicineDeliverySR.setSrID(data[0]);
        medicineDeliverySR.setStatus(data[1]);

        destination.setNodeID(data[2]);
        medicineDeliverySR.setDestination(destination);

        medicineDeliverySR.setMedicineID(data[3]);

        assignedEmployee.setEmployeeID(data[4]);
        medicineDeliverySR.setAssignedEmployee(assignedEmployee);

        medicineDeliverySR.setPatientFirstName(data[5]);
        medicineDeliverySR.setPatientLastName(data[6]);
        medicineDeliverySR.setPatientID(data[7]);
        medicineDeliverySR.setDOB(data[8]);
        medicineDeliverySR.setEmail(data[9]);
        medicineDeliverySR.setDosage(data[10]);
        medicineDeliverySR.setMedicineName(data[11]);
        medicineDeliverySR.setDispenseAmount(data[12]);
        medicineDeliverySR.setFrequency(data[13]);
        medicineDeliverySR.setForm(data[14]);
        medicineDeliverySR.setMgPerDose(data[15]);

        return medicineDeliverySR;
    }

    @Override
    public List<MedicineDeliverySR> fromStringsToObjects(List<String> listString) {
        List<MedicineDeliverySR> medicineDeliverySRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return medicineDeliverySRList;
    }

    @Override
    public String fromObjectToString(MedicineDeliverySR medicineDeliverySR) {
        String str = medicineDeliverySR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<MedicineDeliverySR> listT) {
        List<String> listString = new ArrayList<>();
        for (MedicineDeliverySR medicineDeliverySR : listT) {
            listString.add(medicineDeliverySR.toStringFields());
        }

        return listString;
    }
}
