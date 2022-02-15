package edu.wpi.teamB;

import static org.junit.jupiter.api.Assertions.*;

import edu.wpi.cs3733.c22.teamB.entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocationDBTest {
    static DatabaseWrapper databaseWrapper;

    Location location2 =
            new Location(
                    "C1244T0281",
                    180,
                    84,
                    "03",
                    "Towr",
                    "DPT",
                    "Dy Surgery Family Waiting Floor L1",
                    "Deartment C002L1");

    Location location =
            new Location(
                    "2",
                    280,
                    284,
                    "03",
                    "Towr",
                    "DPT",
                    "restroom",
                    "Deartment C002L1");


    Employee employee1 = new Employee("14", "n", "n", "1", 1, "n", "n", "n", "n");
    Employee employee2 = new Employee("23", "a", "b", "c", 1, "1", "1", "d", "a");

    MedicalEquipment equipment = new MedicalEquipment("12", "Awdd", "asd", "asd", location2, "asd", "asd", "12", "asd", 12);

    LocalDate date = LocalDate.parse("2022-02-12");

    AbstractSR ben = new ExternalTransportSR("12", "12", location2, employee1, employee2, date, "Ben", "Ben", "Ben", "Ben");
    AbstractSR kiki = new ExternalTransportSR("asd", "ads", location2, employee1, employee2, date, "kl", "asd", "asd", "asd");
    AbstractSR Pushman = new FoodDeliverySR("121", "12", location2, employee1, employee2, date, "no", "Hushmand", "Ben");
    //AbstractSR Noah = new GiftFloralSR("123", "12", location2, employee1, employee2, date, "no", "no");
    AbstractSR Sits = new LaundrySR("1123123", "12", location2, employee1, employee2, date, "no");
    AbstractSR Duc = new MedicalEquipmentSR("12321321321", "12", location2, employee1, employee2, date, "no", equipment);
    AbstractSR Nick = new MedicineDeliverySR("1112", "as", location2, employee1, employee2, date, "sad", "sad", "sad");
    AbstractSR Andrew = new MedicineDeliverySR("1112", "DONE", location2, employee2, employee1, date, "asd", "asd", "asd");
    AbstractSR Chris = new ComputerServiceSR("chris", "Done", location2, employee1, employee2, date, "as", "asd");
    AbstractSR Calvin = new SanitationSR("calvin", "WAITING", location2, employee1, employee2, date, "bye", "GLASS");

    @BeforeAll
    public static void setup() {
        databaseWrapper = new DatabaseWrapper();
    }
    @Test
    public void test() {
        databaseWrapper.dropAll();

        databaseWrapper.createTableLocation();
        databaseWrapper.createTableEmployee();
        databaseWrapper.createTableMedicalEquipment();
        databaseWrapper.createTableSR();




        databaseWrapper.addLocation(location2);


        databaseWrapper.addLocation(location);

        databaseWrapper.addEmployee(employee1);
        databaseWrapper.addEmployee(employee2);

        databaseWrapper.addMedicalEquipment(equipment);

        databaseWrapper.addSR(ben);
        databaseWrapper.addSR(kiki);
        databaseWrapper.addSR(Pushman);
       // databaseWrapper.addSR(Noah);
        databaseWrapper.addSR(Sits);
        databaseWrapper.addSR(Duc);
        databaseWrapper.addSR(Nick);
        databaseWrapper.addSR(Chris);
        databaseWrapper.addSR(Calvin);



        databaseWrapper.updateSR(Andrew);

        databaseWrapper.getSR("12");
        databaseWrapper.getSR("121");

        FoodDeliverySRDaoI foodDeliverySRDaoI = new FoodDeliverySRDaoI();
        System.out.println(foodDeliverySRDaoI.getAllValues());

        databaseWrapper.getSR("asd");
        databaseWrapper.getSR("123");
        databaseWrapper.getSR("1123123");
        databaseWrapper.getSR("12321321321");
        databaseWrapper.getSR("1112");



//        databaseWrapper.getAllSR();
        System.out.println(databaseWrapper.getAllLocation());
        databaseWrapper.nodeTypeCountLocation("Hall", "03");
        databaseWrapper.nodeTypeCountLocation("DPT", "3");

        databaseWrapper.getAllSR();

        assertEquals(databaseWrapper.getMedicalEquipment("12").getLocation(),location2);
        databaseWrapper.updateMedicalEquipment(new MedicalEquipment("12", "Awdd", "asd", "asd", location, "asd", "asd", "12", "asd", 12));
        assertEquals(databaseWrapper.getMedicalEquipment("12").getLocation(),location);


    }

    @AfterAll
    public void cleanUp() {
        databaseWrapper.deleteLocation(location.getNodeID());
//        databaseWrapper.deleteLocation(location2.getNodeID());

        databaseWrapper.deleteEmployee(employee1.getEmployeeID());
//        databaseWrapper.deleteEmployee(employee2.getEmployeeID());

//        databaseWrapper.deleteMedicalEquipment(equipment.getEquipmentID());

        databaseWrapper.deleteSR(ben.getSrID());
        databaseWrapper.deleteSR(Pushman.getSrID());
        //databaseWrapper.deleteSR(Noah.getSrID());
        databaseWrapper.deleteSR(Sits.getSrID());
        databaseWrapper.deleteSR(Duc.getSrID());
        databaseWrapper.deleteSR(Nick.getSrID());
    }
    /*
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

        locationDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

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

        locationDBI.closeConnection();
    }

    @Test
    public void testGetNode2() {
        locationDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        assertEquals(location88.toString(), locationDBI.getNode("HHALL00603").toString());
        locationDBI.closeConnection();
    }

    @Test
    public void testInsertNode() {
        locationDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        locationDBI.insertNode(location3);
        locationDBI.isInTable("InsertTest");
        assertEquals("InsertTest", location3.getNodeID());
        locationDBI.deleteNode("InsertTest");

        locationDBI.closeConnection();
    }

    @Test
    public void testInsertEmptyNode() {
        locationDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        assertNull(emptyLocation.getNodeID());

        locationDBI.closeConnection();
    }

    @Test
    public void testDeleteNode() {
        locationDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        locationDBI.deleteNode("CDEPT002L1");
        assertFalse(locationDBI.getAllNodes().contains(location2));
        locationDBI.insertNode(location2);
        locationDBI.closeConnection();
    }

    @Test
    public void testUpdateNode() {
        locationDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        locationDBI.insertNode(location4);
        Location locationTest1 =
                new Location("UpdateTest", 6, 7, "1", "building", "type", "name", "shortname");

        locationDBI.updateNode(locationTest1);
        assertEquals(locationTest1.toString(), locationDBI.getNode("UpdateTest").toString());
        locationDBI.deleteNode("UpdateTest");

        locationDBI.closeConnection();
    }

    */
}

                                                                    // assertEquals(expected, actual);
