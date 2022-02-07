package edu.wpi.cs3733.c22.teamB;

import edu.wpi.cs3733.c22.teamB.entity.CSVWriter2;
import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.LocationDBI;
import edu.wpi.cs3733.c22.teamB.entity.LocationParserI;
import java.io.File;
import java.io.IOException;

public class bDB {

    public static void main(String[] args) throws IOException {

        /* Sample Program Test */

        String url = "jdbc:derby:bDB;create=true";
        String USERID = "admin";
        String PASSWORD = "admin";

        LocationDBI locationDBI = new LocationDBI();
//        locationDBI.initConnection(url, USERID, PASSWORD);

        Location location1 =
                new Location(
                        "FDEPT00101",
                        1617,
                        825,
                        "1",
                        "Tower",
                        "DEPT",
                        "Center for International Medicine",
                        "CIM");
        Location location88 =
                new Location(
                        "HHALL00603",
                        1735,
                        925,
                        "3",
                        "Tower",
                        "HALL",
                        "Hallway Connector 6 Floor 3",
                        "Hallway H00603");
        Location locationTest =
                new Location("TEST1", 6, 7, "1", "building", "type", "name", "shortname");

        LocationParserI locParser = new LocationParserI();

        // TEST FOR RESTORE FROM BACKUP

        String pathString = new File("").getAbsolutePath();
        File f = new File(pathString);

        //        File dir = new File(f.getAbsolutePath() + "/backup1/TowerLocations.csv");
        //        CSVReader2 reader = new CSVReader2(dir);
        //        locationDBI.restore(locParser.fromStringsToObjects(reader.read()));

        CSVWriter2 writer = new CSVWriter2("Version1");

        // TESTING BACKUP FUNCTIONALITY, VERSION 1 FILE
        writer.writeAll(locParser.fromObjectsToStrings(locationDBI.getAllNodes()));
        //        System.out.println(locationDBI.getAllNodes());
        //        System.out.println(locParser.fromObjectsToStrings(locationDBI.getAllNodes()));

        locationDBI.updateNode(location1);
        locationDBI.insertNode(locationTest);
        locationDBI.deleteNode(location88.getNodeID());

        CSVWriter2 writer2 = new CSVWriter2("Version2");

        // TESTING BACKUP FUNCTIONALITY, VERSION 2 FILE
        writer2.writeAll(locParser.fromObjectsToStrings(locationDBI.getAllNodes()));
    }
}
// App.launch(App.class, args);
