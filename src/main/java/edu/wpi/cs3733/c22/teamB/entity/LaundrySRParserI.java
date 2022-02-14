package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LaundrySRParserI implements IParser<LaundrySR> {


    @Override
    public LaundrySR fromStringToObject(String string) {
        LaundrySR laundrySR = new LaundrySR();

        String[] data = string.split(",");

        laundrySR.setSrID(data[0]);

        return laundrySR;
    }

    @Override
    public List<LaundrySR> fromStringsToObjects(List<String> listString) {
        List<LaundrySR> laundrySRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return laundrySRList;
    }

    @Override
    public String fromObjectToString(LaundrySR laundrySR) {
        String str = laundrySR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<LaundrySR> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(LaundrySR.toStringHeader());
        for (LaundrySR laundrySR : listT) {
            listString.add(laundrySR.toStringFields());
        }

        return listString;
    }
}
