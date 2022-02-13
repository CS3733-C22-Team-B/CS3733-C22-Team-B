package edu.wpi.cs3733.c22.teamB.entity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DatabaseWrapper {

    private IDatabase<Location> LocationDao;
    private IDatabase<Employee> EmployeeDao;
    private IDatabase<MedicalEquipment> MedicalEquipmentDao;
    private IDatabase<ExternalTransportSR> ExternalTransportDao;
    private IDatabase<FoodDeliverySR> FoodDeliveryDao;
    private IDatabase<GiftFloralSR> GiftFloralSRDao;
    private IDatabase<LaundrySR> LaundrySRDao;
    private IDatabase<MedicalEquipmentSR> MedicalEquipmentSRDao;
    private IDatabase<MedicineDeliverySR> MedicineDeliverySRDao;
    private IDatabase<AbstractSR> MainSRDao;

    public DatabaseWrapper() {
        LocationDao = new LocationDaoI();
        EmployeeDao = new EmployeeDaoI();
        MedicalEquipmentDao = new MedicalEquipmentDaoI();
        ExternalTransportDao = new ExternalTransportSRDaoI();
        FoodDeliveryDao = new FoodDeliverySRDaoI();
        GiftFloralSRDao = new GiftFloralSRDaoI();
        LaundrySRDao = new LaundrySRDaoI();
        MedicalEquipmentSRDao = new MedicalEquipmentSRDaoI();
        MedicineDeliverySRDao = new MedicineDeliverySRDaoI();
        MainSRDao = new MainSRDaoI();

    }

    // AbstractSR a = new ExternalTransportSR();
    // a.getPatientID()
//    public void addSR(ExternalTransportSR abstractSR){
//        MainSRDao.addValue(abstractSR); //TODO do you need this or comment out?ExternalTransportDao.addValue(abstractSR);
//    }
    
    public void addSR(AbstractSR abstractSR){
        //AbstractSR a = new ExternalTransportSR();
        MainSRDao.addValue(abstractSR); //TODO do you need this or comment out?ExternalTransportDao.addValue(abstractSR);
        System.out.println(abstractSR.getSrType());
        switch(abstractSR.getSrType()) {
            case "ExternalTransportSR":
                ExternalTransportDao.addValue((ExternalTransportSR) abstractSR);
                break;
            case "FoodDeliverySR":
                FoodDeliveryDao.addValue((FoodDeliverySR) abstractSR);
                break;
            case "GiftFloralSR":
                GiftFloralSRDao.addValue((GiftFloralSR) abstractSR);
                break;
            case "LaundrySR":
                LaundrySRDao.addValue((LaundrySR) abstractSR);
                break;
            case "MedicalEquipmentSR":
                MedicalEquipmentSRDao.addValue((MedicalEquipmentSR) abstractSR);
                break;
            case "MedicineDeliverySR":
            MedicineDeliverySRDao.addValue((MedicineDeliverySR) abstractSR);
                break;
            default:
                System.out.println("Invalid SR Input: " + abstractSR.getSrType());
        }

    }

    public void addLocation(Location location) {
        LocationDao.addValue(location);
    }

    public void addEmployee(Employee employee) {
        EmployeeDao.addValue(employee);
    }

    public void addMedicalEquipment(MedicalEquipment medicalEquipment) {
        MedicalEquipmentDao.addValue(medicalEquipment);
    }

    public void deleteSR(String srID) {}

    public void deleteLocation(String locationID) {
        LocationDao.deleteValue(locationID);
    }

    public void deleteEmployee(String employeeID) {
        EmployeeDao.deleteValue(employeeID);
    }

    public void deleteMedicalEquipment(String medicalEquipmentID) {
        MedicalEquipmentDao.deleteValue(medicalEquipmentID);
    }

    public void updateSR(AbstractSR abstractSR) {}

    public void updateLocation(Location location) {
        LocationDao.updateValue(location);
    }

    public void updateEmployee(Employee employee) {
        EmployeeDao.updateValue(employee);
    }

    public void updateMedicalEquipment(MedicalEquipment medicalEquipment) {
        MedicalEquipmentDao.updateValue(medicalEquipment);
    }

    public AbstractSR getSR(String srID) {return MainSRDao.getValue(srID);}

    public Location getLocation(String locationID) {
        return LocationDao.getValue(locationID);
    }

    public Employee getEmployee(String employeeID) {
        return EmployeeDao.getValue(employeeID);
    }

    public MedicalEquipment getMedicalEquipment(String medicalEquipmentID) {
        return MedicalEquipmentDao.getValue(medicalEquipmentID);
    }

    //public List<AbstractSR> getAllSR() {}

    public List<Location> getAllLocation() {
        return LocationDao.getAllValues();
    }

    public List<Employee> getAllEmployee() {
        return EmployeeDao.getAllValues();
    }

    public List<MedicalEquipment> getAllMedicalEquipment(String medicalEquipmentID) {
        return MedicalEquipmentDao.getAllValues();
    }

    public void createTableSR() {}

    public void createTableLocation() {
        LocationDao.createTable();
    }

    public void createTableEmployee() {
        EmployeeDao.createTable();
    }

    public void createTableMedicalEquipment() {
        MedicalEquipmentDao.createTable();
    }

    public void dropTableSR() {}

    public void dropTableLocation() {
        LocationDao.dropTable();
    }

    public void dropTableEmployee() {
        EmployeeDao.dropTable();
    }

    public void dropTableMedicalEquipment() {
        MedicalEquipmentDao.dropTable();
    }

    public void dropAll() {
    }

    public void restoreTableSR() {}

    public void restoreTableLocation() {}

    public void restoreTableEmployee() {}

    public void restoreTableMedicalEquipment() {}

    public void restoreAll() {}
}
