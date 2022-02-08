package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalTransportSRParserI implements IParser<ExternalTransportSR> {

    @Override
    public ExternalTransportSR fromStringToObject(String string) {
        ExternalTransportSR externalTransportSR = new ExternalTransportSR();
        Employee employee = new Employee();

        String[] data = string.split(",");

        externalTransportSR.setSrID(data[0]);
        externalTransportSR.setStatus(data[1]);
        externalTransportSR.setPickupLoc(data[2]);
        externalTransportSR.setDestination(data[3]);
        externalTransportSR.setInfo(data[4]);
        externalTransportSR.setDate(data[5]);
        externalTransportSR.setFormOfTransport(data[6]);

        employee.setEmployeeID(data[7]);
        externalTransportSR.setEmployee(employee);

        return externalTransportSR;
    }

    @Override
    public List<ExternalTransportSR> fromStringsToObjects(List<String> listString) {
        List<ExternalTransportSR> externalTransportSRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return externalTransportSRList;
    }

    @Override
    public String fromObjectToString(ExternalTransportSR externalTransportSR) {
        String str = externalTransportSR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<ExternalTransportSR> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(ExternalTransportSR.toStringHeader());
        for (ExternalTransportSR externalTransportSR : listT) {
            listString.add(externalTransportSR.toStringFields());
        }

        return listString;
    }
}
