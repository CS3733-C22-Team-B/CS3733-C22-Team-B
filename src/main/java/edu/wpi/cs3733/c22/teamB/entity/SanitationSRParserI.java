package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SanitationSRParserI implements IParser<SanitationSR> {

    @Override
    public SanitationSR fromStringToObject(String string) {
        SanitationSR sanitationSR = new SanitationSR();

        String[] data = string.split(",");

        sanitationSR.setSrID(data[0]);
        sanitationSR.setCondition(data[1]);

        return sanitationSR;
    }

    @Override
    public List<SanitationSR> fromStringsToObjects(List<String> listString) {
        List<SanitationSR> sanitationSRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return sanitationSRList;
    }

    @Override
    public String fromObjectToString(SanitationSR sanitationSR) {
        String str = sanitationSR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<SanitationSR> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(SanitationSR.toStringHeader());
        for (SanitationSR sanitationSR : listT) {
            listString.add(sanitationSR.toStringFields());
        }

        return listString;
    }
}
