package edu.wpi.cs3733.c22.teamB.entity;

import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;

public class SRIDGenerator {
    public static String generateID() {
        DatabaseWrapper dw = DatabaseWrapper.getInstance();
        int i = 0;
        String ret;
        while (dw.srIsInTable(ret = ("SR" + i)))
            System.out.println(++i);
        return ret;
    }
}
