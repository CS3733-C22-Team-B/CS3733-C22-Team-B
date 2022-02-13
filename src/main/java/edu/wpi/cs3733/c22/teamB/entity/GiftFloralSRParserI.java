package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GiftFloralSRParserI implements IParser<GiftFloralSR> {


    @Override
    public GiftFloralSR fromStringToObject(String string) {
        GiftFloralSR giftFloralSR = new GiftFloralSR();

        String[] data = string.split(",");

        giftFloralSR.setSrID(data[0]);
        giftFloralSR.setGiftName(data[1]);

        return giftFloralSR;
    }

    @Override
    public List<GiftFloralSR> fromStringsToObjects(List<String> listString) {
        List<GiftFloralSR> giftFloralSR =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return giftFloralSR;
    }

    @Override
    public String fromObjectToString(GiftFloralSR giftFloralSR) {
        String str = giftFloralSR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<GiftFloralSR> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(GiftFloralSR.toStringHeader());
        for (GiftFloralSR giftFloralSR : listT) {
            listString.add(giftFloralSR.toStringFields());
        }

        return listString;
    }
}
