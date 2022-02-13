package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationParserI implements IParser<Location> {


    @Override
    public Location fromStringToObject(String string) {
        Location location = new Location();

        String[] data = string.split(",");

        location.setNodeID(data[0]);
        location.setXcoord(Integer.parseInt(data[1]));
        location.setYcoord(Integer.parseInt(data[2]));
        location.setFloor(data[3]);
        location.setBuilding(data[4]);
        location.setNodeType(data[5]);
        location.setLongName(data[6]);
        location.setShortName(data[7]);

        return location;
    }

    @Override
    public List<Location> fromStringsToObjects(List<String> listString) {
        List<Location> locationList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return locationList;
    }

    @Override
    public String fromObjectToString(Location location) {
        String str = location.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<Location> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(Location.toStringHeader());
        for (Location location : listT) {
            listString.add(location.toStringFields());
        }

        return listString;
    }

}
