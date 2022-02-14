package edu.wpi.cs3733.c22.teamB.entity;

import edu.wpi.cs3733.c22.teamB.Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainSRParserI implements IParser<AbstractSR> {


    @Override
    public AbstractSR fromStringToObject(String string) {
        AbstractSR mainSR = new MainSR();
        Location location = new Location();
        Employee requestor = new Employee();
        Employee assignedEmployee = new Employee();

        String[] data = string.split(",");

        mainSR.setSrID(data[0]);
        mainSR.setSrType(data[1]);
        mainSR.setStatus(data[2]);

        location.setNodeID(data[3]);
        mainSR.setLocation(location);

        requestor.setEmployeeID(data[4]);
        mainSR.setRequestor(requestor);

        assignedEmployee.setEmployeeID(data[5]);
        mainSR.setAssignedEmployee(assignedEmployee);

        mainSR.setDateRequested(LocalDate.parse(data[6]));
        mainSR.setNotes(data[7]);

        return mainSR;
    }

    @Override
    public List<AbstractSR> fromStringsToObjects(List<String> listString) {
        List<AbstractSR> mainSRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return mainSRList;
    }

    @Override
    public String fromObjectToString(AbstractSR mainSR) {
        String str = ((MainSR) mainSR).toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<AbstractSR> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(MainSR.toStringHeader());
        for (AbstractSR mainSR : listT) {
            listString.add(((MainSR) mainSR).toStringFields());
        }

        return listString;
    }
}
