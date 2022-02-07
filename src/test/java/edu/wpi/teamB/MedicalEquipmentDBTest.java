package edu.wpi.cs3733.c22.teamB;

import static org.junit.jupiter.api.Assertions.*;

import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.MedicalEquipmentDBI;
import org.junit.jupiter.api.Test;

public class MedicalEquipmentDBTest {

    MedicalEquipmentDBI medEquipDBI = new MedicalEquipmentDBI();

    Location location2 =
            new Location(
                    "FHALL01401", 1627, 1029, "1", "Tower", "HALL", "Tower Hallway 6", "Hallway F01401");
    Location location3 =
            new Location(
                    "FHALL01901", 1758, 1120, "1", "Tower", "HALL", "Tower Hallway 11", "Hallway F01901");
    MedicalEquipment equipment1 =
            new MedicalEquipment(
                    "E1", "bed", "type", "manufacturer", location2, "status", "color", "size", "description");
    MedicalEquipment equipment2 =
            new MedicalEquipment(
                    "E2", "wheelchair", "type", "manu", location3, "status", "color", "size", "desciption");
    MedicalEquipment equipment3 =
            new MedicalEquipment(
                    "InsertEquipment", "pencil", "type", "manu", location3, "stat", "color", "size", "des");
    MedicalEquipment emptyEquip = new MedicalEquipment();

    @Test
    public void testGetNode() {
        medEquipDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        assertEquals(equipment1.toString(), medEquipDBI.getNode("E1").toString());

        medEquipDBI.closeConnection();
    }

    @Test
    public void testInsertNode() {
        medEquipDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        medEquipDBI.insertNode(equipment3);
        assertEquals("InsertEquipment", equipment3.getEquipmentID());

        medEquipDBI.closeConnection();
    }

    @Test
    public void testInsertEmptyNode() {
        medEquipDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        assertNull(emptyEquip.getEquipmentID());

        medEquipDBI.closeConnection();
    }

    @Test
    public void testDeleteNode() {
        medEquipDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        medEquipDBI.deleteNode("InsertEquipment");
        assertFalse(medEquipDBI.getAllNodes().contains(equipment3));

        medEquipDBI.closeConnection();
    }

    @Test
    public void testUpdateNode() {
        medEquipDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        medEquipDBI.insertNode(equipment2);
        MedicalEquipment equipTest =
                new MedicalEquipment(
                        "E2", "new chair", "test", "test", location3, "yes", "wooh", "size", "desciption");

        medEquipDBI.updateNode(equipTest);
        assertEquals(equipTest.toString(), medEquipDBI.getNode("E2").toString());
        medEquipDBI.deleteNode("E2");

        assertFalse(medEquipDBI.getAllNodes().contains(equipment2));

        medEquipDBI.closeConnection();
    }
}
