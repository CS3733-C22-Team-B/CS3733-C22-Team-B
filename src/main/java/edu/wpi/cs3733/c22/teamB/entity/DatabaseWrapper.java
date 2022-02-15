package edu.wpi.cs3733.c22.teamB.entity;

import javax.swing.table.AbstractTableModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    private IDatabase<ComputerServiceSR> ComputerServiceSRDao;
    private IDatabase<AbstractSR> MainSRDao;

    private RestoreBackupWrapper restoreBackupWrapper;

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
        ComputerServiceSRDao = new ComputerServiceSRDaoI();
        MainSRDao = new MainSRDaoI();

        restoreBackupWrapper = new RestoreBackupWrapper();
    }

    // AbstractSR a = new ExternalTransportSR();
    // a.getPatientID()
//    public void addSR(ExternalTransportSR abstractSR){
//        MainSRDao.addValue(abstractSR); //TODO do you need this or comment out?ExternalTransportDao.addValue(abstractSR);
//    }
    
    public void addSR(AbstractSR abstractSR){
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
            case "ComputerServiceSR":
                ComputerServiceSRDao.addValue((ComputerServiceSR) abstractSR);
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

    public void deleteSR(String srID) {

        AbstractSR abstractSR = getSR(srID);
        System.out.println(abstractSR.getSrType());
        switch(abstractSR.getSrType()) {
            case "ExternalTransportSR":
                ExternalTransportDao.deleteValue(srID);
                break;
            case "FoodDeliverySR":
                FoodDeliveryDao.deleteValue(srID);
                break;
            case "GiftFloralSR":
                GiftFloralSRDao.deleteValue(srID);
                break;
            case "LaundrySR":
                LaundrySRDao.deleteValue(srID);
                break;
            case "MedicalEquipmentSR":
                MedicalEquipmentSRDao.deleteValue(srID);
                break;
            case "MedicineDeliverySR":
                MedicineDeliverySRDao.deleteValue(srID);
                break;
            case "ComputerServiceSR":
                ComputerServiceSRDao.deleteValue(srID);
                break;
            default:
                System.out.println("Invalid SRID Input: " + abstractSR.getSrID());
        }
        MainSRDao.deleteValue(srID);

    }

    public void deleteLocation(String locationID) {
        LocationDao.deleteValue(locationID);
    }

    public void deleteEmployee(String employeeID) {
        EmployeeDao.deleteValue(employeeID);
    }

    public void deleteMedicalEquipment(String medicalEquipmentID) {
        MedicalEquipmentDao.deleteValue(medicalEquipmentID);
    }

    public void updateSR(AbstractSR abstractSR) {
        MainSRDao.updateValue(abstractSR); //TODO do you need this or comment out?ExternalTransportDao.addValue(abstractSR);
        System.out.println(abstractSR.getSrType());
        switch(abstractSR.getSrType()) {
            case "ExternalTransportSR":
                ExternalTransportDao.updateValue((ExternalTransportSR) abstractSR);
                break;
            case "FoodDeliverySR":
                FoodDeliveryDao.updateValue((FoodDeliverySR) abstractSR);
                break;
            case "GiftFloralSR":
                GiftFloralSRDao.updateValue((GiftFloralSR) abstractSR);
                break;
            case "LaundrySR":
                LaundrySRDao.updateValue((LaundrySR) abstractSR);
                break;
            case "MedicalEquipmentSR":
                MedicalEquipmentSRDao.updateValue((MedicalEquipmentSR) abstractSR);
                break;
            case "MedicineDeliverySR":
                MedicineDeliverySRDao.updateValue((MedicineDeliverySR) abstractSR);
                break;
            case "ComputerServiceSR":
                ComputerServiceSRDao.updateValue((ComputerServiceSR) abstractSR);
                break;
            default:
                System.out.println("Invalid SR Input: " + abstractSR.getSrType());
        }
    }

    public void updateLocation(Location location) {
        LocationDao.updateValue(location);
    }

    public void updateEmployee(Employee employee) {
        EmployeeDao.updateValue(employee);
    }

    public void updateMedicalEquipment(MedicalEquipment medicalEquipment) {
        MedicalEquipmentDao.updateValue(medicalEquipment);
    }

    public AbstractSR getSR(String srID) {

        AbstractSR abstractSR = MainSRDao.getValue(srID);
        if (abstractSR != null) {
            switch(abstractSR.getSrType()) {
                case "ExternalTransportSR":
                    System.out.println(ExternalTransportDao.getValue(srID));
                    return ExternalTransportDao.getValue(srID);
                case "FoodDeliverySR":
                    System.out.println(FoodDeliveryDao.getValue(srID));
                    return FoodDeliveryDao.getValue(srID);
                case "GiftFloralSR":
                    System.out.println(GiftFloralSRDao.getValue(srID));
                    return GiftFloralSRDao.getValue(srID);
                case "LaundrySR":
                    System.out.println(LaundrySRDao.getValue(srID));
                    return LaundrySRDao.getValue(srID);
                case "MedicalEquipmentSR":
                    System.out.println(MedicalEquipmentSRDao.getValue(srID));
                    return MedicalEquipmentSRDao.getValue(srID);
                case "MedicineDeliverySR":
                    System.out.println(MedicineDeliverySRDao.getValue(srID));
                    return MedicineDeliverySRDao.getValue(srID);
                case "ComputerServiceSR":
                    System.out.println(ComputerServiceSRDao.getValue(srID));
                    return ComputerServiceSRDao.getValue(srID);
                default:
                    System.out.println("Invalid SR Input: " + abstractSR.getSrType());
            }
        }
        return null;
    }

    public Location getLocation(String locationID) {
        return LocationDao.getValue(locationID);
    }

    public Employee getEmployee(String employeeID) {
        return EmployeeDao.getValue(employeeID);
    }

    public MedicalEquipment getMedicalEquipment(String medicalEquipmentID) {
        return MedicalEquipmentDao.getValue(medicalEquipmentID);
    }

    public List<AbstractSR> getAllSR() {
        List<AbstractSR> list = MainSRDao.getAllValues();

        for (AbstractSR abstractSR : list) {
            abstractSR = getSR(abstractSR.getSrID());
        }

        System.out.println(list);
        return list;
    }

    public List<Location> getAllLocation() {
        return LocationDao.getAllValues();
    }

    public List<Employee> getAllEmployee() {
        return EmployeeDao.getAllValues();
    }

    public List<MedicalEquipment> getAllMedicalEquipment() {
        return MedicalEquipmentDao.getAllValues();
    }

    public void createTableSR() {
        MainSRDao.createTable();
        LaundrySRDao.createTable();
        ExternalTransportDao.createTable();
        FoodDeliveryDao.createTable();
        GiftFloralSRDao.createTable();
        ComputerServiceSRDao.createTable();
        MedicineDeliverySRDao.createTable();
        MedicalEquipmentSRDao.createTable();
    }

    public void createTableLocation() {
        LocationDao.createTable();
    }

    public void createTableEmployee() {
        EmployeeDao.createTable();
    }

    public void createTableMedicalEquipment() {
        MedicalEquipmentDao.createTable();
    }

    public void createAll() {

        createTableLocation();
        createTableEmployee();
        createTableMedicalEquipment();
        createTableSR();
    }

    public void dropTableSR() {
        MedicalEquipmentSRDao.dropTable();
        MedicineDeliverySRDao.dropTable();
        GiftFloralSRDao.dropTable();
        FoodDeliveryDao.dropTable();
        ExternalTransportDao.dropTable();
        ComputerServiceSRDao.dropTable();

        LaundrySRDao.dropTable();
        MainSRDao.dropTable();
    }

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

        dropTableSR();
        dropTableMedicalEquipment();
        dropTableEmployee();
        dropTableLocation();
    }

    void restoreTableSR() throws IOException {
        restoreBackupWrapper.restoreMainSR();
        restoreBackupWrapper.restoreExternalTransportSR();
        restoreBackupWrapper.restoreFoodDeliverySR();
        restoreBackupWrapper.restoreGiftFloralSR();
        restoreBackupWrapper.restoreLaundrySR();
        restoreBackupWrapper.restoreMedicalEquipmentSR();
        restoreBackupWrapper.restoreMedicineDeliverySR();
    }

    void restoreTableLocation() throws IOException {
        restoreBackupWrapper.restoreLocation();
    }

    void restoreTableEmployee() throws IOException {
        restoreBackupWrapper.restoreEmployee();
    }

    void restoreTableMedicalEquipment() throws IOException {
        restoreBackupWrapper.restoreMedicalEquipment();
    }

    public void restoreAll() throws IOException {
        dropAll();
        createAll();
        restoreBackupWrapper.restoreAll();
    }

    void backupTableLocation() throws IOException {
        restoreBackupWrapper.backupLocation();
    }

    void backupTableEmployee() throws IOException {
        restoreBackupWrapper.backupEmployee();
    }

    void backupTableMedicalEquipment() throws IOException{
        restoreBackupWrapper.backupMedicalEquipment();
    }

    void backupTableSR() throws FileNotFoundException {
        restoreBackupWrapper.backupMainSR();
        restoreBackupWrapper.backupExternalTransportSR();
        restoreBackupWrapper.backupFoodDeliverySR();
        restoreBackupWrapper.backupGiftFloralSR();
        restoreBackupWrapper.backupLaundrySR();
        restoreBackupWrapper.backupMedicalEquipmentSR();
        restoreBackupWrapper.backupMedicineDeliverySR();
    }

    public void backupAll() throws IOException{
        restoreBackupWrapper.backupAll();
    }
    public boolean isInTableLocation(String nodeID){
        LocationDaoI test = new LocationDaoI();
        return test.isInTable(nodeID);
    }

    public int nodeTypeCountLocation(String nodeType, String floor){
        LocationDaoI locationDaoI = new LocationDaoI();
        System.out.println(locationDaoI.nodeTypeCount(nodeType, floor));
        return locationDaoI.nodeTypeCount(nodeType, floor);
    }
}
