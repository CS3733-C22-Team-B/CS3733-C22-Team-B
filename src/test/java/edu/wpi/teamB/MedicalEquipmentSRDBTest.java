/*package edu.wpi.cs3733.c22.teamB;

import edu.wpi.cs3733.c22.teamB.entity.*;
import edu.wpi.cs3733.c22.teamB.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MedicalEquipmentSRDBTest {

    String statusTest = new String("BLANK");

    Location locationTest1 =
            new Location(
                    "hello", 1950, 910, "1", "Tower", "HALL", "Shattuck Street Lobby 3", "Hallway F00501");
    Location locationTest2 =
            new Location("goodbye", 1627, 825, "1", "Tower", "HALL", "Tower Hallway 5", "Hallway F01301");

    MedicalEquipment medEquipTest =
            new MedicalEquipment(
                    "E1",
                    "bed",
                    "type",
                    "manufacturer",
                    locationTest2,
                    "status",
                    "color",
                    "size",
                    "description");

    Employee employee1 = new Employee("4", "Pushman", "we", "we", "we", "we");
    Employee employee2 = new Employee("5", "sits", "we", "ha", "hehe", "asd");

    MedicalEquipmentSR medEquipSR =
            new MedicalEquipmentSR("id hehe", statusTest, locationTest1, medEquipTest, employee1);

    MedicalEquipmentSRDBI medEquipSRDBI = new MedicalEquipmentSRDBI();

    List<MedicalEquipmentSR> list = new ArrayList<>();

    @Test
    public void testPopulationTime() {
        LocationDBI locationDBI = new LocationDBI();
        locationDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        locationDBI.insertNode(locationTest1);
        locationDBI.insertNode(locationTest2);

        MedicalEquipmentDBI equipmentDBI = new MedicalEquipmentDBI();
        equipmentDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        equipmentDBI.insertNode(medEquipTest);

        EmployeeDBI employeeDBI = new EmployeeDBI();
        employeeDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        employeeDBI.insertNode(employee1);
        employeeDBI.insertNode(employee2);

        medEquipSRDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        list.add(medEquipSR);
        medEquipSRDBI.restore(list);

        locationDBI.closeConnection();
        equipmentDBI.closeConnection();
        employeeDBI.closeConnection();
        medEquipSRDBI.closeConnection();
    }

    //    @Test
    //    public void testGetNode(){
    //        medEquipSRDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
    //
    //        assertEquals(medEquipSR.toString()),
    //
    //
    //    }

}
*/