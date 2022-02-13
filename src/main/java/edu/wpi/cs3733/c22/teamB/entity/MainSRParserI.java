package edu.wpi.cs3733.c22.teamB.entity;

import edu.wpi.cs3733.c22.teamB.Main;

import java.time.LocalDate;
import java.util.List;

public class MainSRParserI implements IParser<MainSR> {


    @Override
    public MainSR fromStringToObject(String string) {
        MainSR mainSR = new MainSR();
        Location location = new Location();
        Employee requestor = new Employee();
        Employee assignedEmployee = new Employee();

        String[] data = string.split(",");



        return mainSR;
    }

    @Override
    public List<MainSR> fromStringsToObjects(List<String> listString) {
        return null;
    }

    @Override
    public String fromObjectToString(MainSR object) {
        return null;
    }

    @Override
    public List<String> fromObjectsToStrings(List<MainSR> listT) {
        return null;
    }
}
