package edu.wpi.cs3733.c22.teamB.oldEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDeliveryParserI implements IParser<FoodDeliverySR> {

    @Override
    public FoodDeliverySR fromStringToObject(String string) {
        FoodDeliverySR foodDeliverySR = new FoodDeliverySR();
        Location destination = new Location();
        Employee employee = new Employee();

        String[] data = string.split(",");

        foodDeliverySR.setSrID(data[0]);
        foodDeliverySR.setStatus(data[1]);

        destination.setNodeID(data[2]);
        foodDeliverySR.setDestination(destination);

        foodDeliverySR.setFoodName(data[3]);

        foodDeliverySR.setFoodRecipientName(data[4]);

        employee.setEmployeeID(data[5]);
        foodDeliverySR.setAssignedEmployee(employee);

        return foodDeliverySR;
    }

    @Override
    public List<FoodDeliverySR> fromStringsToObjects(List<String> listString) {
        List<FoodDeliverySR> foodDeliverySRS =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return foodDeliverySRS;
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
