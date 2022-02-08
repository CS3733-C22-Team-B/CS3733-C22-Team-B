package edu.wpi.teamB;

import static org.junit.jupiter.api.Assertions.*;

import edu.wpi.cs3733.c22.teamB.entity.*;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MedicalEquipmentSRDBTest {

    String statusTest = "BLANK";

    LocationDBI locationDBI = new LocationDBI();
    MedicalEquipmentDBI equipmentDBI = new MedicalEquipmentDBI();
    EmployeeDBI employeeDBI = new EmployeeDBI();
    MedicalEquipmentSRDBI medEquipSRDBI = new MedicalEquipmentSRDBI();

    Location location1 =
            new Location(
                    "goodbye", 1627, 1029, "1", "Tower", "HALL", "Tower Hallway 6", "Hallway F01401");
    Location location2 =
            new Location("yes", 1758, 1120, "1", "Tower", "HALL", "Tower Hallway 11", "Hallway F01901");

    MedicalEquipment equipment1 =
            new MedicalEquipment(
                    "E1", "bed", "type", "manufacturer", location1, "status", "color", "size", "description");
    MedicalEquipment equipment2 =
            new MedicalEquipment(
                    "E2", "wheelchair", "type", "manu", location2, "status", "color", "size", "desciption");

    Employee employee1 = new Employee("4", "Pushman", "we", "we", "we", "we");
    Employee employee2 = new Employee("5", "sits", "we", "ha", "hehe", "asd");

    MedicalEquipmentSR medEquipSR =
            new MedicalEquipmentSR("idhehe", statusTest, location1, equipment1, employee1);

    @BeforeAll
    public void start() {
        locationDBI.insertNode(location1);
        locationDBI.insertNode(location2);
        equipmentDBI.insertNode(equipment1);
        equipmentDBI.insertNode(equipment2);
        employeeDBI.insertNode(employee1);
        employeeDBI.insertNode(employee2);
    }

    @Test
    public void testInsertNode() {
        medEquipSRDBI.insertNode(medEquipSR);
        List<MedicalEquipmentSR> l = medEquipSRDBI.getAllNodes();
        System.out.println(l);
        System.out.println(medEquipSR);
        assertTrue(l.contains(medEquipSR));
        //        assertTrue(medEquipSRDBI.isInTable(medEquipSR.getSrID()));
        medEquipSRDBI.deleteNode(medEquipSR.getSrID());
    }

    @Test
    public void testGetNode() {
        medEquipSRDBI.insertNode(medEquipSR);
        assertEquals(medEquipSR.toString(), medEquipSRDBI.getNode("idhehe").toString());
        medEquipSRDBI.deleteNode("idhehe");
    }

    @Test
    public void testDeleteNode() {
        medEquipSRDBI.insertNode(medEquipSR);
        assertTrue(medEquipSRDBI.getAllNodes().contains(medEquipSR));
        medEquipSRDBI.deleteNode("idhehe");
        assertFalse(medEquipSRDBI.getAllNodes().contains(medEquipSR));
    }

    @Test
    public void testUpdateNode() {
        medEquipSRDBI.insertNode(medEquipSR);

        MedicalEquipmentSR medEquipSRTest =
                new MedicalEquipmentSR("idhehe", statusTest, location1, equipment1, employee2);

        medEquipSRDBI.updateNode(medEquipSRTest);
        assertEquals(medEquipSRTest.toString(), medEquipSRDBI.getNode("idhehe").toString());
        medEquipSRDBI.deleteNode("idhehe");

        assertFalse(medEquipSRDBI.getAllNodes().contains(medEquipSRTest));
    }

    @AfterAll
    public void end() {
        employeeDBI.deleteNode(employee2.getEmployeeID());
        employeeDBI.deleteNode(employee1.getEmployeeID());
        equipmentDBI.deleteNode(equipment2.getEquipmentID());
        equipmentDBI.deleteNode(equipment1.getEquipmentID());
        locationDBI.deleteNode(location2.getNodeID());
        locationDBI.deleteNode(location1.getNodeID());
    }
}
