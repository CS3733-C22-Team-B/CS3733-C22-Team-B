package edu.wpi.teamB;

import static org.junit.jupiter.api.Assertions.*;

import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.LocationDBI;
import org.junit.jupiter.api.Test;

public class LocationDBTest {

    // assertEquals(expected, actual);

    LocationDBI locationDBI = new LocationDBI();
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
    Location locationTest = new Location("TEST1", 6, 7, "1", "building", "type", "name", "shortname");
    Location emptyLocation = new Location();

    Location location2 =
            new Location(
                    "CDEPT002L1",
                    1980,
                    844,
                    "L1",
                    "Tower",
                    "DEPT",
                    "Day Surgery Family Waiting Floor L1",
                    "Department C002L1");

    Location location3 = new Location("InsertTest", 1, 1, "L1", "T", "w", "w", "W");

    Location location4 = new Location("UpdateTest", 1, 1, "L1", "T", "w", "w", "W");
    // assertEquals(expected, actual)
    @Test
    public void testGetNode1() {

        locationDBI.updateNode(
                new Location(
                        "FDEPT00101",
                        1617,
                        825,
                        "1",
                        "Tower",
                        "DEPT",
                        "Center for International Medicine",
                        "CIM"));
        assertEquals(location1.toString(), locationDBI.getNode("FDEPT00101").toString());
    }

    @Test
    public void testGetNode2() {
        assertEquals(location88.toString(), locationDBI.getNode("HHALL00603").toString());
    }

    @Test
    public void testInsertNode() {
        locationDBI.insertNode(location3);
        locationDBI.isInTable("InsertTest");
        assertEquals("InsertTest", location3.getNodeID());
        locationDBI.deleteNode("InsertTest");
    }

    @Test
    public void testInsertEmptyNode() {
        assertNull(emptyLocation.getNodeID());
    }

    @Test
    public void testDeleteNode() {
        locationDBI.deleteNode("CDEPT002L1");
        assertFalse(locationDBI.getAllNodes().contains(location2));
        locationDBI.insertNode(location2);
    }

    @Test
    public void testUpdateNode() {
        locationDBI.insertNode(location4);
        Location locationTest1 =
                new Location("UpdateTest", 6, 7, "1", "building", "type", "name", "shortname");

        locationDBI.updateNode(locationTest1);
        assertEquals(locationTest1.toString(), locationDBI.getNode("UpdateTest").toString());
        locationDBI.deleteNode("UpdateTest");
    }
}
