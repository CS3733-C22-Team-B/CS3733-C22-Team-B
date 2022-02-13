package edu.wpi.teamB;

import static org.junit.jupiter.api.Assertions.*;

import edu.wpi.cs3733.c22.teamB.entity.*;
import edu.wpi.cs3733.c22.teamB.oldEntity.MedicalEquipmentDBI;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocationDBTest {
    @Test
    public void test() {



        MainSRDaoI mainSRDaoI = new MainSRDaoI();

        ExternalTransportSRDaoI externalTransportSRDaoI = new ExternalTransportSRDaoI();
        externalTransportSRDaoI.dropTable();


        MedicalEquipmentSRDaoI medicalEquipmentSRDaoI = new MedicalEquipmentSRDaoI();
//        medicalEquipmentSRDaoI.dropTable();

        MedicineDeliverySRDaoI medicineDeliverySRDaoI = new MedicineDeliverySRDaoI();
//        medicineDeliverySRDaoI.dropTable();

        GiftFloralSRDaoI giftFloralSRDaoI = new GiftFloralSRDaoI();
//        giftFloralSRDaoI.dropTable();

        FoodDeliverySRDaoI foodDeliverySRDaoI = new FoodDeliverySRDaoI();
        foodDeliverySRDaoI.dropTable();

//        MedicalEquipmentDaoI medicalEquipmentDaoI = new MedicalEquipmentDaoI();
//        medicalEquipmentDaoI.dropTable();

//        MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
//        medicalEquipmentDBI.drop();


        mainSRDaoI.dropTable();



        LocationDaoI locationDaoI = new LocationDaoI();
        locationDaoI.dropTable();




        EmployeeDaoI employeeDaoI = new EmployeeDaoI();
        employeeDaoI.dropTable();

        //        medicalEquipmentSRDBI.drop();
//        medicineDeliverySRDBI.drop();
////        giftFloralSRDBI.drop();
//        foodDeliverySRDBI.drop();
//        externalTransportSRDBI.drop();
//        medicalEquipmentDBI.drop();
//        employeeDBI.drop();
//        locationDBI.drop();


        employeeDaoI.createTable();
        locationDaoI.createTable();
        mainSRDaoI.createTable();
        externalTransportSRDaoI.createTable();

//        FoodDeliverySRDaoI foodDeliverySRDaoI1 = new FoodDeliverySRDaoI();

        foodDeliverySRDaoI.createTable();


        Location location2 =
                new Location(
                        "C1244T0281",
                        180,
                        84,
                        "L",
                        "Towr",
                        "DPT",
                        "Dy Surgery Family Waiting Floor L1",
                        "Deartment C002L1");



        Employee employee1 = new Employee("14", "n", "n", "1", 1, "n", "n", "n", "n");
        Employee employee2 = new Employee("23", "a", "b", "c", 1, "1", "1", "d", "a");

        locationDaoI.addValue(location2);

        employeeDaoI.addValue(employee1);
        employeeDaoI.addValue(employee2);



        LocalDate date = LocalDate.parse("2022-02-12");

        DatabaseWrapper db = new DatabaseWrapper();
        AbstractSR ben = new ExternalTransportSR("12", "12", location2, employee1, employee2, date, "Ben", "Ben", "Ben", "Ben");


        AbstractSR Pushman = new FoodDeliverySR("121", "12", location2, employee1, employee2, date, "no", "Hushmand", "Ben");

        db.addSR(ben);
        db.addSR(Pushman);
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
