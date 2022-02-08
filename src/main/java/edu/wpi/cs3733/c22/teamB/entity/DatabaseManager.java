package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.Connection;
import java.util.List;

public class DatabaseManager {

    Connection conn = DBConnection.getConnection();

    public DatabaseManager() {}

    public void createTable() {
        LocationDBI locationDBI = new LocationDBI();
        locationDBI.createTable();

        EmployeeDBI employeeDBI = new EmployeeDBI();
        employeeDBI.createTable();

        MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
        medicalEquipmentDBI.createTable();

        MedicalEquipmentSRDBI medicalEquipmentSRDBI = new MedicalEquipmentSRDBI();
        medicalEquipmentSRDBI.createTable();

        GiftFloralSRDBI giftFloralSRDBI = new GiftFloralSRDBI();
        giftFloralSRDBI.createTable();

        FoodDeliverySRDBI foodDeliverySRDBI = new FoodDeliverySRDBI();
        foodDeliverySRDBI.createTable();

        MedicineDeliverySRDBI medicineDeliverySRDBI = new MedicineDeliverySRDBI();
        medicineDeliverySRDBI.createTable();

        ExternalTransportSRDBI externalTransportSRDBI = new ExternalTransportSRDBI();
        externalTransportSRDBI.createTable();

    }

    public void restoreTables(List<Location> locationList, List<Employee> employeeList, List<ExternalTransportSR> externalTransportSRList,
                              List<MedicineDeliverySR> medicineDeliverySRList, List<FoodDeliverySR> foodDeliverySRList,
                              List<GiftFloralSR> giftFloralSRList, List<MedicalEquipmentSR> medicalEquipmentSRList,
                              List<MedicalEquipment> medicalEquipmentList) {

        MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
        MedicalEquipmentSRDBI medicalEquipmentSRDBI = new MedicalEquipmentSRDBI();
        GiftFloralSRDBI giftFloralSRDBI = new GiftFloralSRDBI();
        FoodDeliverySRDBI foodDeliverySRDBI = new FoodDeliverySRDBI();
        MedicineDeliverySRDBI medicineDeliverySRDBI = new MedicineDeliverySRDBI();
        ExternalTransportSRDBI externalTransportSRDBI = new ExternalTransportSRDBI();
        EmployeeDBI employeeDBI = new EmployeeDBI();
        LocationDBI locationDBI = new LocationDBI();

        medicalEquipmentSRDBI.restore(medicalEquipmentSRList);
        medicineDeliverySRDBI.restore(medicineDeliverySRList);
        giftFloralSRDBI.restore(giftFloralSRList);
        foodDeliverySRDBI.restore(foodDeliverySRList);
        externalTransportSRDBI.restore(externalTransportSRList);
        medicalEquipmentDBI.restore(medicalEquipmentList);
        employeeDBI.restore(employeeList);
        locationDBI.restore(locationList);
    }

    /*

    LocationDBI ---> None
    EmployeeDBI ---> None
    MedicalEquipmentDBI ---> LocationDBI
    ExternalTransportDBI ---> EmployeeDBI
    FoodDeliveryDBI ---> LocationDBI && EmployeeDBI
    GiftFloralDBI ---> None
    LaundryDBI ---> LocationDBI
    MedicineDeliverySRDBI ---> EmployeeDBI && LocationDBI
    MedicalEquipmentSRDBI ---> LocationDBI && MedicalEquipmentDBI



     */
}
