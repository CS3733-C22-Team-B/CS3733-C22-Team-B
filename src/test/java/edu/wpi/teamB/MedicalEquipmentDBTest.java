package edu.wpi.teamB;

import static org.junit.jupiter.api.Assertions.*;

import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.LocationDBI;
import edu.wpi.cs3733.c22.teamB.entity.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.MedicalEquipmentDBI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MedicalEquipmentDBTest {

    MedicalEquipmentDBI medEquipDBI = new MedicalEquipmentDBI();
    LocationDBI locationDBI = new LocationDBI();

    Location location2 =
            new Location(
                    "goodbye", 1627, 1029, "1", "Tower", "HALL", "Tower Hallway 6", "Hallway F01401");
    Location location3 =
            new Location("yes", 1758, 1120, "1", "Tower", "HALL", "Tower Hallway 11", "Hallway F01901");
    MedicalEquipment equipment1 =
            new MedicalEquipment(
                    "E1", "bed", "type", "manufacturer", location2, "status", "color", "size", "description");
    MedicalEquipment equipment2 =
            new MedicalEquipment(
                    "E2", "wheelchair", "type", "manu", location3, "status", "color", "size", "desciption");

    @BeforeAll
    public void start() {
        locationDBI.insertNode(location2);
        locationDBI.insertNode(location3);
    }

    @Test
    public void testGetNode() {
        medEquipDBI.insertNode(equipment1);
        assertEquals(equipment1.toString(), medEquipDBI.getNode("E1").toString());
        medEquipDBI.deleteNode("E1");
    }

    @Test
    public void testInsertNode() {
        medEquipDBI.insertNode(equipment1);
        //        assertTrue(medEquipDBI.isInTable(equipment1.getEquipmentID()));
        assertTrue(medEquipDBI.getAllNodes().contains(equipment1));
        medEquipDBI.deleteNode("E1");
    }

    @Test
    public void testDeleteNode() {
        medEquipDBI.insertNode(equipment1);
        medEquipDBI.deleteNode("E1");
        assertFalse(medEquipDBI.getAllNodes().contains(equipment1));
    }

    @Test
    public void testUpdateNode() {
        medEquipDBI.insertNode(equipment2);

        MedicalEquipment equipTest =
                new MedicalEquipment(
                        "E2", "new chair", "test", "test", location3, "yes", "wooh", "size", "desciption");

        medEquipDBI.updateNode(equipTest);
        assertEquals(equipTest.toString(), medEquipDBI.getNode("E2").toString());

        medEquipDBI.deleteNode("E2");

        assertFalse(medEquipDBI.isInTable("E2"));
    }

    @AfterAll
    public void end() {
        locationDBI.deleteNode(location2.getNodeID());
        locationDBI.deleteNode(location3.getNodeID());
    }
}
