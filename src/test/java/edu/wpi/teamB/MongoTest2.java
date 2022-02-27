package edu.wpi.teamB;

import edu.wpi.cs3733.c22.teamB.entity.MongoDB.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.UnknownHostException;
import java.time.LocalDate;

public class MongoTest2 {

    public MongoTest2() {}

    @Test public void testMongo() throws UnknownHostException {

        //Location

        MongoDB.getConnection();
        LocationMongo locationMongo = new LocationMongo();
        locationMongo.dropTable();
        locationMongo.createTable();

        Location location1 = new Location("12", 12, 12, "12", "123", "123", "21e", "q2e");
        Location location2 = new Location("123", 13, 13, "12", "123", "123", "123", "123");

        locationMongo.addValue(location1);
        locationMongo.addValue(location2);
        assertEquals(locationMongo.getValue(location1.getNodeID()), location1);
        locationMongo.getAllValues();

//        //Employee

        EmployeeMongo employeeMongo = new EmployeeMongo();
        employeeMongo.dropTable();
        employeeMongo.createTable();

        Employee employee1 = new Employee("123", "123", "123", "123", 12, "123", "123", "213", "123");
        Employee employee2 = new Employee("223", "123", "123", "123", 12, "123", "123", "213", "123");
        Employee employee3 = new Employee("323", "123", "Ben", "123", 12, "123", "123", "213", "123");

        employeeMongo.addValue(employee1);
        employeeMongo.addValue(employee2);
        employeeMongo.addValue(employee3);
        employeeMongo.getValue(employee1.getEmployeeID());

        IDatabase<AbstractSR> mainSRMongo = new MainSRMongo(locationMongo, employeeMongo);
        mainSRMongo.dropTable();
        mainSRMongo.createTable();
        LocalDate date = LocalDate.parse("2022-12-12");


        //Computer Service
        ComputerServiceSRMongo computerServiceSRMongo = new ComputerServiceSRMongo(mainSRMongo);
        computerServiceSRMongo.dropTable();
        computerServiceSRMongo.createTable();

        ComputerServiceSR computerServiceSR = new ComputerServiceSR("Comp1", "123", location1, employee1, employee2, date, "123" ,"123");
        ComputerServiceSR computerServiceSR2 = new ComputerServiceSR("Comp2", "123", location1, employee1, employee2, date, "123" ,"123");
        ComputerServiceSR computerServiceSR3 = new ComputerServiceSR("Comp3", "123", location1, employee1, employee2, date, "123" ,"123");
        ComputerServiceSR computerServiceSR32 = new ComputerServiceSR("Comp3", "123", location1, employee3, employee2, date, "123" ,"123");

        mainSRMongo.addValue(computerServiceSR);
        mainSRMongo.addValue(computerServiceSR2);
        mainSRMongo.addValue(computerServiceSR3);

        computerServiceSRMongo.addValue(computerServiceSR);
        computerServiceSRMongo.addValue(computerServiceSR2);
        computerServiceSRMongo.addValue(computerServiceSR3);
        computerServiceSRMongo.updateValue(computerServiceSR32);
        computerServiceSRMongo.deleteValue(computerServiceSR32.getSrID());
        computerServiceSRMongo.getValue(computerServiceSR.getSrID());
        computerServiceSRMongo.getAllValues();


        //External Transport

        ExternalTransportSRMongo externalTransportSRMongo = new ExternalTransportSRMongo(mainSRMongo);
        externalTransportSRMongo.dropTable();
        externalTransportSRMongo.createTable();

        ExternalTransportSR externalTransportSR = new ExternalTransportSR("External1", "123", location1, employee1, employee2, date, "123", "123", "123", "23");
        ExternalTransportSR externalTransportSR2 = new ExternalTransportSR("External2", "123", location1, employee1, employee2, date, "123", "123", "123", "23");
        ExternalTransportSR externalTransportSR3 = new ExternalTransportSR("External3", "123", location1, employee1, employee2, date, "123", "123", "123", "23");
        ExternalTransportSR externalTransportSR32 = new ExternalTransportSR("External3", "123", location1, employee3, employee2, date, "123", "123", "123", "23");

        mainSRMongo.addValue(externalTransportSR);
        mainSRMongo.addValue(externalTransportSR2);
        mainSRMongo.addValue(externalTransportSR3);

        externalTransportSRMongo.addValue(externalTransportSR);
        externalTransportSRMongo.addValue(externalTransportSR2);
        externalTransportSRMongo.addValue(externalTransportSR3);
        externalTransportSRMongo.updateValue(externalTransportSR32);
        externalTransportSRMongo.deleteValue(externalTransportSR32.getSrID());
        externalTransportSRMongo.getValue(externalTransportSR.getSrID());
        externalTransportSRMongo.getAllValues();

        //Food

        FoodDeliverySRMongo foodDeliverySRMongo = new FoodDeliverySRMongo(mainSRMongo);
        foodDeliverySRMongo.dropTable();
        foodDeliverySRMongo.createTable();

        FoodDeliverySR foodDeliverySR = new FoodDeliverySR("Food1", "123", location1, employee1, employee2, date, "132", "123", "123");
        FoodDeliverySR foodDeliverySR2 = new FoodDeliverySR("Food2", "122", location1, employee1, employee2, date, "132", "123", "123" );
        FoodDeliverySR foodDeliverySR3 = new FoodDeliverySR("Food3", "123", location1, employee1, employee2, date, "132", "123", "123" );
        FoodDeliverySR foodDeliverySR32 = new FoodDeliverySR("Food3", "123", location1, employee1, employee2, date, "132", "123", "123" );

        mainSRMongo.addValue(foodDeliverySR);
        mainSRMongo.addValue(foodDeliverySR2);
        mainSRMongo.addValue(foodDeliverySR3);

        foodDeliverySRMongo.addValue(foodDeliverySR);
        foodDeliverySRMongo.addValue(foodDeliverySR2);
        foodDeliverySRMongo.addValue(foodDeliverySR3);
        foodDeliverySRMongo.updateValue(foodDeliverySR32);
        foodDeliverySRMongo.deleteValue(foodDeliverySR32.getSrID());
        foodDeliverySRMongo.getValue(foodDeliverySR.getSrID());
        foodDeliverySRMongo.getAllValues();

        //Gift

        GiftFloralSRMongo giftFloralSRMongo = new GiftFloralSRMongo(mainSRMongo);
        giftFloralSRMongo.dropTable();
        giftFloralSRMongo.createTable();

        GiftFloralSR giftFloralSR = new GiftFloralSR("Gift1", "123", location1, employee1, employee2, date, "13", "123");
        GiftFloralSR giftFloralSR2 = new GiftFloralSR("Gift2", "123", location1, employee1, employee2, date, "13", "123");
        GiftFloralSR giftFloralSR3 = new GiftFloralSR("Gift3", "1232", location1, employee1, employee2, date, "13", "123");
        GiftFloralSR giftFloralSR32 = new GiftFloralSR("Gift3", "3", location1, employee1, employee2, date, "14333334343434343434343433", "123");

        mainSRMongo.addValue(giftFloralSR);
        mainSRMongo.addValue(giftFloralSR2);
        mainSRMongo.addValue(giftFloralSR3);

        giftFloralSRMongo.addValue(giftFloralSR);
        giftFloralSRMongo.addValue(giftFloralSR2);
        giftFloralSRMongo.addValue(giftFloralSR3);
        giftFloralSRMongo.updateValue(giftFloralSR32);
        giftFloralSRMongo.deleteValue(giftFloralSR3.getSrID());
        giftFloralSRMongo.getValue(giftFloralSR.getSrID());
        giftFloralSRMongo.getAllValues();

        //MedicalEquipment

        EquipmentMongo equipmentMongo = new EquipmentMongo(locationMongo);
        equipmentMongo.dropTable();
        equipmentMongo.createTable();

        MedicalEquipment equipment1 = new MedicalEquipment("1", "12", "13", "12", location2, "123", "123", "123", "123", 1);
        MedicalEquipment equipment2 = new MedicalEquipment("2", "12", "13", "12", location2, "123", "123", "123", "123", 1);
        MedicalEquipment equipment3 = new MedicalEquipment("3", "12", "13", "12", location2, "123", "123", "123", "123", 1);
        MedicalEquipment equipment32 = new MedicalEquipment("3", "1122132", "13", "12", location2, "123", "123", "123", "123", 1);

        equipmentMongo.addValue(equipment1);
        equipmentMongo.addValue(equipment2);
        equipmentMongo.addValue(equipment3);

        //MedicalEquipmentSR

        MedicalEquipmentSRMongo medicalEquipmentSRMongo = new MedicalEquipmentSRMongo(mainSRMongo, equipmentMongo);
        medicalEquipmentSRMongo.dropTable();
        medicalEquipmentSRMongo.createTable();

        MedicalEquipmentSR medicalEquipmentSR = new MedicalEquipmentSR("medSR1", "123", location1, employee1, employee2, date, "123", equipment1);
        MedicalEquipmentSR medicalEquipmentSR2 = new MedicalEquipmentSR("medSR2", "123", location1, employee1, employee2, date, "123", equipment1);
        MedicalEquipmentSR medicalEquipmentSR3 = new MedicalEquipmentSR("medSR3", "123", location1, employee1, employee2, date, "123", equipment2);
        MedicalEquipmentSR medicalEquipmentSR32 = new MedicalEquipmentSR("medSR3", "123", location1, employee1, employee2, date, "123", equipment1);

        mainSRMongo.addValue(medicalEquipmentSR);
        mainSRMongo.addValue(medicalEquipmentSR2);
        mainSRMongo.addValue(medicalEquipmentSR3);

        medicalEquipmentSRMongo.addValue(medicalEquipmentSR);
        medicalEquipmentSRMongo.addValue(medicalEquipmentSR2);
        medicalEquipmentSRMongo.addValue(medicalEquipmentSR3);
        medicalEquipmentSRMongo.updateValue(medicalEquipmentSR32);
        medicalEquipmentSRMongo.deleteValue(medicalEquipmentSR32.getSrID());
        medicalEquipmentSRMongo.getValue(medicalEquipmentSR.getSrID());
        medicalEquipmentSRMongo.getAllValues();

        //MedicineDelivery

        MedicineDeliverySRMongo medicineDeliverySRMongo = new MedicineDeliverySRMongo(mainSRMongo);
        medicineDeliverySRMongo.dropTable();
        medicineDeliverySRMongo.createTable();

        MedicineDeliverySR medicineSR = new MedicineDeliverySR("Medicine1", "123",location1, employee1, employee2, date, "123", "123" ,"123" );
        MedicineDeliverySR medicineSR2 = new MedicineDeliverySR("Medicine2", "123",location1, employee1, employee2, date, "123", "123" ,"123" );
        MedicineDeliverySR medicineSR3 = new MedicineDeliverySR("Medicine3", "123",location1, employee1, employee2, date, "123", "123" ,"123" );
        MedicineDeliverySR medicineSR32 = new MedicineDeliverySR("Medicine3", "123",location1, employee1, employee2, date, "123", "123" ,"uiasjdbakjsfsb" );

        mainSRMongo.addValue(medicineSR);
        mainSRMongo.addValue(medicineSR2);
        mainSRMongo.addValue(medicineSR3);

        medicineDeliverySRMongo.addValue(medicineSR);
        medicineDeliverySRMongo.addValue(medicineSR2);
        medicineDeliverySRMongo.addValue(medicineSR3);
        medicineDeliverySRMongo.updateValue(medicineSR32);
        medicineDeliverySRMongo.deleteValue(medicineSR32.getSrID());
        medicineDeliverySRMongo.getValue(medicineSR.getSrID());
        medicineDeliverySRMongo.getAllValues();


        // Sanitation

        SanitationSRMongo sanitationSRMongo = new SanitationSRMongo(mainSRMongo);
        sanitationSRMongo.dropTable();
        sanitationSRMongo.createTable();

        SanitationSR sanitationSR = new SanitationSR("Sanitation1", "13", location1, employee1, employee2, date, "132", "123");
        SanitationSR sanitationSR2 = new SanitationSR("Sanitation2", "13", location1, employee1, employee2, date, "132", "123");
        SanitationSR sanitationSR3 = new SanitationSR("Sanitation3", "13", location1, employee1, employee2, date, "132", "123");
        SanitationSR sanitationSR32 = new SanitationSR("Sanitation3", "13", location1, employee1, employee2, date, "132", "123");

        mainSRMongo.addValue(sanitationSR);
        mainSRMongo.addValue(sanitationSR2);
        mainSRMongo.addValue(sanitationSR3);

        sanitationSRMongo.addValue(sanitationSR);
        sanitationSRMongo.addValue(sanitationSR2);
        sanitationSRMongo.addValue(sanitationSR3);
        sanitationSRMongo.updateValue(sanitationSR32);
        sanitationSRMongo.deleteValue(sanitationSR32.getSrID());
        sanitationSRMongo.getValue(sanitationSR.getSrID());
        sanitationSRMongo.getAllValues();

        //Laundry

        LaundrySRMongo laundrySRMongo = new LaundrySRMongo(mainSRMongo);
        laundrySRMongo.dropTable();
        laundrySRMongo.createTable();

        AbstractSR laundrySR = new LaundrySR("Laundry1", "123", location1, employee1, employee2, date, "123");
        AbstractSR laundrySR2 = new LaundrySR("Laundry2", "123", location1, employee1, employee2, date, "123");
        AbstractSR laundrySR3 = new LaundrySR("Laundry3", "123", location1, employee1, employee2, date, "123");
        AbstractSR laundrySR32 = new LaundrySR("Laundry3", "123", location1, employee1, employee2, date, "123awdeqe");

        mainSRMongo.addValue(laundrySR);
        mainSRMongo.addValue(laundrySR2);
        mainSRMongo.addValue(laundrySR3);

        laundrySRMongo.addValue((LaundrySR) laundrySR);
        laundrySRMongo.addValue((LaundrySR) laundrySR2);
        laundrySRMongo.addValue((LaundrySR) laundrySR3);
        laundrySRMongo.updateValue((LaundrySR) laundrySR32);
        laundrySRMongo.deleteValue(laundrySR32.getSrID());
        laundrySRMongo.getValue(laundrySR.getSrID());
        laundrySRMongo.getAllValues();
    }
}
