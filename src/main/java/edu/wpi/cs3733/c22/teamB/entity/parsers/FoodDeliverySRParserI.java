package edu.wpi.cs3733.c22.teamB.entity.parsers;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.IParser;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDeliverySRParserI implements IParser<FoodDeliverySR> {


    @Override
    public FoodDeliverySR fromStringToObject(String string) {
        FoodDeliverySR foodDeliverySR = new FoodDeliverySR();

        String[] data = string.split(",");

        foodDeliverySR.setSrID(data[0]);
        foodDeliverySR.setFoodName(data[1]);
        foodDeliverySR.setDrinkName(data[2]);

        return foodDeliverySR;
    }

    @Override
    public List<FoodDeliverySR> fromStringsToObjects(List<String> listString) {
        List<FoodDeliverySR> foodDeliverySRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return foodDeliverySRList;
    }

    @Override
    public String fromObjectToString(FoodDeliverySR foodDeliverySR) {
        String str = foodDeliverySR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<FoodDeliverySR> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(FoodDeliverySR.toStringHeader());
        for (FoodDeliverySR foodDeliverySR : listT) {
            listString.add(foodDeliverySR.toStringFields());
        }

        return listString;
    }
}
