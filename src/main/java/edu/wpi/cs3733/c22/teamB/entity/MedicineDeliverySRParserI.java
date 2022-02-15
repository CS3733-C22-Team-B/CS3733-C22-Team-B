package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicineDeliverySRParserI implements IParser<MedicineDeliverySR> {

    @Override
    public MedicineDeliverySR fromStringToObject(String string) {
        MedicineDeliverySR medicineDeliverySR = new MedicineDeliverySR();

        String[] data = string.split(",");

        medicineDeliverySR.setSrID(data[0]);
        medicineDeliverySR.setMedicineID(data[1]);
        medicineDeliverySR.setPatientID(data[2]);

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
        listString.add(MedicineDeliverySR.toStringHeader());
        for (MedicineDeliverySR medicineDeliverySR : listT) {
            listString.add(medicineDeliverySR.toStringFields());
        }

        return listString;
    }
}
