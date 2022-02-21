package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import edu.wpi.cs3733.c22.teamB.entity.*;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;

import java.net.UnknownHostException;
import java.util.List;

public class MongoWrapper {

    IMongo<Location> locationMongo;
    IMongo<Employee> employeeMongo;
    IMongo<MedicalEquipment> equipmentMongo;

    public MongoWrapper() throws UnknownHostException {
        locationMongo= new LocationMongo();
        employeeMongo = new EmployeeMongo();
        equipmentMongo = new EquipmentMongo(locationMongo);



    }

    public void addLocation(Location location) {
        locationMongo.addValue(location);
    }

    public void addEmployee(Employee employee) {
        employeeMongo.addValue(employee);
    }

    public void addMedicalEquipment(MedicalEquipment medicalEquipment) {
        equipmentMongo.addValue(medicalEquipment);
    }

    public void deleteLocation(String locationID) {
        locationMongo.deleteValue(locationID);
    }

    public void deleteEmployee(String employeeID) {
        employeeMongo.deleteValue(employeeID);
    }

    public void deleteMedicalEquipment(String medicalEquipmentID) {
        equipmentMongo.deleteValue(medicalEquipmentID);
    }

    public void updateLocation(Location location) {
        locationMongo.updateValue(location);
    }

    public void updateEmployee(Employee employee) {
        employeeMongo.updateValue(employee);
    }

    public void updateMedicalEquipment(MedicalEquipment medicalEquipment) {
        equipmentMongo.updateValue(medicalEquipment);
    }

    public Location getLocation(String locationID) {
        return locationMongo.getValue(locationID);
    }

    public Employee getEmployee(String employeeID) {
        return employeeMongo.getValue(employeeID);
    }

    public MedicalEquipment getMedicalEquipment(String medicalEquipmentID) {
        return equipmentMongo.getValue(medicalEquipmentID);
    }
    public List<Location> getAllLocation() {
        return locationMongo.getAllValues();
    }

    public List<Employee> getAllEmployee() {
        return employeeMongo.getAllValues();
    }

    public List<MedicalEquipment> getAllMedicalEquipment() {
        return equipmentMongo.getAllValues();
    }

    public void createTableLocation() {
        locationMongo.createTable();
    }

    public void createTableEmployee() {
        employeeMongo.createTable();
    }

    public void createTableMedicalEquipment() {
        equipmentMongo.createTable();
    }

    public void createAll() {
        createTableLocation();
        createTableEmployee();
        createTableMedicalEquipment();
    }

    public void dropTableLocation() {
        locationMongo.dropTable();
    }

    public void dropTableEmployee() {
        employeeMongo.dropTable();
    }

    public void dropTableMedicalEquipment() {
        equipmentMongo.dropTable();
    }

    public void dropAll() {
        dropTableMedicalEquipment();
        dropTableEmployee();
        dropTableLocation();
    }

}
