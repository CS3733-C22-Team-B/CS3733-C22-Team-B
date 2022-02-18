package edu.wpi.cs3733.c22.teamB.entity.parsers;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.IParser;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ComputerServiceSR;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerServiceSRParserI implements IParser<ComputerServiceSR> {

    @Override
    public ComputerServiceSR fromStringToObject(String string) {

        ComputerServiceSR computerServiceSR = new ComputerServiceSR();

        String[] data = string.split(",");

         computerServiceSR.setSrID(data[0]);
         computerServiceSR.setHelpType(data[1]);

         return computerServiceSR;
    }

    @Override
    public List<ComputerServiceSR> fromStringsToObjects(List<String> listString) {

        List<ComputerServiceSR> computerServiceSRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return computerServiceSRList;
    }

    @Override
    public String fromObjectToString(ComputerServiceSR computerServiceSR) {

        String str = computerServiceSR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<ComputerServiceSR> listT) {

        List<String> listString = new ArrayList<>();
        listString.add(ComputerServiceSR.toStringHeader());
        for (ComputerServiceSR computerServiceSR : listT) {
            listString.add(computerServiceSR.toStringFields());
        }

        return listString;
    }
}
