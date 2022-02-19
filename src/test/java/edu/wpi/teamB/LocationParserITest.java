package edu.wpi.teamB;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.parsers.LocationParserI;
import org.junit.jupiter.api.Test;

public class LocationParserITest {

    @Test
    public void testStringToObject() {
        String str = "nodeID1,1,1,floor1,building1,nodeType1,longN1,shortN1";
        Location locationExpected =
                new Location("nodeID1", 1, 1, "floor1", "building1", "nodeType1", "longN1", "shortN1");

        LocationParserI locParser = new LocationParserI();
        Location locationResult = locParser.fromStringToObject(str);

        assertEquals(locationExpected.toString(), locationResult.toString());
    }

    @Test
    public void testStringsToObjects() {
        List<String> stringList = new ArrayList<>();
        String str1 = "nodeID1,1,1,floor1,building1,nodeType1,longN1,shortN1";
        String str2 = "nodeID2,2,2,floor2,building2,nodeType2,longN2,shortN2";
        stringList.add(str1);
        stringList.add(str2);

        Location locationExpected1 =
                new Location("nodeID1", 1, 1, "floor1", "building1", "nodeType1", "longN1", "shortN1");
        Location locationExpected2 =
                new Location("nodeID2", 2, 2, "floor2", "building2", "nodeType2", "longN2", "shortN2");
        List<Location> locationListExpected = new ArrayList<>();
        locationListExpected.add(locationExpected1);
        locationListExpected.add(locationExpected2);

        LocationParserI locParser = new LocationParserI();
        List<Location> locationListResult = locParser.fromStringsToObjects(stringList);

        assertEquals(locationListExpected.toString(), locationListResult.toString());
    }

    @Test
    public void testObjectToString() {
        String stringExpected = "nodeID1,1,1,floor1,building1,nodeType1,longN1,shortN1";
        Location locationExpected =
                new Location("nodeID1", 1, 1, "floor1", "building1", "nodeType1", "longN1", "shortN1");

        LocationParserI locParser = new LocationParserI();
        String stringResult = locParser.fromObjectToString(locationExpected);

        assertEquals(stringExpected, stringResult);
    }

    @Test
    public void testObjectsToStrings() {
        List<String> stringListExpected = new ArrayList<>();
        String header = Location.toStringHeader();
        String str1 = "nodeID1,1,1,floor1,building1,nodeType1,longN1,shortN1";
        String str2 = "nodeID2,2,2,floor2,building2,nodeType2,longN2,shortN2";
        stringListExpected.add(header);
        stringListExpected.add(str1);
        stringListExpected.add(str2);

        Location location1 =
                new Location("nodeID1", 1, 1, "floor1", "building1", "nodeType1", "longN1", "shortN1");
        Location location2 =
                new Location("nodeID2", 2, 2, "floor2", "building2", "nodeType2", "longN2", "shortN2");
        List<Location> locationList = new ArrayList<>();
        locationList.add(location1);
        locationList.add(location2);

        LocationParserI locParser = new LocationParserI();
        List<String> stringListResult = locParser.fromObjectsToStrings(locationList);

        assertEquals(stringListExpected.toString(), stringListResult.toString());
    }
}
