package edu.wpi.teamB;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs3733.c22.teamB.entity.*;
import org.junit.jupiter.api.Test;

public class MainSRParserITest {

    @Test
    public void testStringToObject() {
        String str = "nodeID1,MedicalEquipmentSR,WAITING,location1,requestor1,aEmployee1,2022-05-05,shortN1";

        Location location1 = new Location();
        location1.setNodeID("location1");

        Employee requestor1 = new Employee();
        requestor1.setEmployeeID("requestor1");

        Employee aEmployee1 = new Employee();
        aEmployee1.setEmployeeID("aEmployee1");

        LocalDate dateR1 = LocalDate.parse("2022-05-05");

        AbstractSR mainSRExpected =
                new MainSR("nodeID1", "MedicalEquipmentSR", "WAITING", location1, requestor1, aEmployee1, dateR1, "shortN1");

        MainSRParserI mainParser = new MainSRParserI();
        AbstractSR mainSRResult = mainParser.fromStringToObject(str);

        assertEquals(mainSRExpected.toString(), mainSRResult.toString());
    }

    @Test
    public void testStringsToObjects() {
        List<String> stringList = new ArrayList<>();
        String str1 = "nodeID1,MedicalEquipmentSR,WAITING,location1,requestor1,aEmployee1,2022-05-05,shortN1";
        String str2 = "nodeID2,MedicalEquipmentSR,WAITING,location2,requestor2,aEmployee2,2022-05-05,shortN2";
        stringList.add(str1);
        stringList.add(str2);

        Location location1 = new Location();
        location1.setNodeID("location1");

        Employee requestor1 = new Employee();
        requestor1.setEmployeeID("requestor1");

        Employee aEmployee1 = new Employee();
        aEmployee1.setEmployeeID("aEmployee1");

        LocalDate dateR1 = LocalDate.parse("2022-05-05");

        Location location2 = new Location();
        location2.setNodeID("location2");

        Employee requestor2 = new Employee();
        requestor2.setEmployeeID("requestor2");

        Employee aEmployee2 = new Employee();
        aEmployee2.setEmployeeID("aEmployee2");

        LocalDate dateR2 = LocalDate.parse("2022-05-05");

        AbstractSR mainSR1 =
                new MainSR("nodeID1", "MedicalEquipmentSR", "WAITING", location1, requestor1, aEmployee1, dateR1, "shortN1");
        AbstractSR mainSR2 =
                new MainSR("nodeID2", "MedicalEquipmentSR", "WAITING", location2, requestor2, aEmployee2, dateR2, "shortN2");
        List<AbstractSR> mainSRListExpected = new ArrayList<>();
        mainSRListExpected.add(mainSR1);
        mainSRListExpected.add(mainSR2);

        MainSRParserI mainParser = new MainSRParserI();
        List<MainSR> mainSRListResult = mainParser.fromStringsToObjects(stringList);

        assertEquals(mainSRListExpected.toString(), mainSRListResult.toString());
    }

    @Test
    public void testObjectToString() {
        String stringExpected = "nodeID1,MedicalEquipmentSR,WAITING,location1,requestor1,aEmployee1,2022-05-05,shortN1";

        Location location1 = new Location();
        location1.setNodeID("location1");

        Employee requestor1 = new Employee();
        requestor1.setEmployeeID("requestor1");

        Employee aEmployee1 = new Employee();
        aEmployee1.setEmployeeID("aEmployee1");

        LocalDate dateR1 = LocalDate.parse("2022-05-05");

        AbstractSR mainSRExpected =
                new MainSR("nodeID1", "MedicalEquipmentSR", "WAITING", location1, requestor1, aEmployee1, dateR1, "shortN1");

        MainSRParserI mainParser = new MainSRParserI();
        String stringResult = mainParser.fromObjectToString((MainSR) mainSRExpected);

        assertEquals(stringExpected, stringResult);
    }

    @Test
    public void testObjectsToStrings() {

        List<String> stringListExpected = new ArrayList<>();
        String header = MainSR.toStringHeader();
        String str1 = "nodeID1,MedicalEquipmentSR,WAITING,location1,requestor1,aEmployee1,2022-05-05,shortN1";
        String str2 = "nodeID2,MedicalEquipmentSR,WAITING,location2,requestor2,aEmployee2,2022-05-05,shortN2";
        stringListExpected.add(header);
        stringListExpected.add(str1);
        stringListExpected.add(str2);

        Location location1 = new Location();
        location1.setNodeID("location1");

        Employee requestor1 = new Employee();
        requestor1.setEmployeeID("requestor1");

        Employee aEmployee1 = new Employee();
        aEmployee1.setEmployeeID("aEmployee1");

        LocalDate dateR1 = LocalDate.parse("2022-05-05");

        Location location2 = new Location();
        location2.setNodeID("location2");

        Employee requestor2 = new Employee();
        requestor2.setEmployeeID("requestor2");

        Employee aEmployee2 = new Employee();
        aEmployee2.setEmployeeID("aEmployee2");

        LocalDate dateR2 = LocalDate.parse("2022-05-05");

        AbstractSR mainSR1 =
                new MainSR("nodeID1", "MedicalEquipmentSR", "WAITING", location1, requestor1, aEmployee1, dateR1, "shortN1");
        AbstractSR mainSR2 =
                new MainSR("nodeID2", "MedicalEquipmentSR", "WAITING", location2, requestor2, aEmployee2, dateR2, "shortN2");
        List<MainSR> mainSRListExpected = new ArrayList<>();
        mainSRListExpected.add((MainSR) mainSR1);
        mainSRListExpected.add((MainSR) mainSR2);

        MainSRParserI mainParser = new MainSRParserI();
        List<String> stringListResult = mainParser.fromObjectsToStrings(mainSRListExpected);

        assertEquals(stringListExpected.toString(), stringListResult.toString());
    }
}
