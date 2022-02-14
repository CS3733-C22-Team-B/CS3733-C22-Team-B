package edu.wpi.cs3733.c22.teamB.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestoreBackupWrapper {

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

    private CSVReader reader;
    private CSVWriter writer;

    private File backDir;
    private final String locationFileName = "/TowerLocationsB.csv";
    private final String employeeFileName = "/EmployeeB.csv";
    private final String medicalEquipmentFileName = "/MedicalEquipmentB.csv";
    private final String mainSRFileName = "/MainSRB.csv";
    private final String externalTransportFileName = "/ExternalTransportSRB.csv";
    private final String foodDeliveryFileName = "/FoodDeliverySRB.csv";
    private final String giftFloralFileName = "/GiftFloralSRB.csv";
    private final String laundryFileName = "/LaundrySRB.csv";
    private final String medicalEquipmentSRFileName = "/MedicalEquipmentSRB.csv";
    private final String medicineDeliveryFileName = "/MedicineDeliverySRB.csv";

    private final String locationFileNameW = "TowerLocationsB";
    private final String employeeFileNameW = "EmployeeB";
    private final String medicalEquipmentFileNameW = "MedicalEquipmentB";
    private final String mainSRFileNameW = "MainSRB";
    private final String externalTransportFileNameW = "ExternalTransportSRB";
    private final String foodDeliveryFileNameW = "FoodDeliverySRB";
    private final String giftFloralFileNameW = "GiftFloralSRB";
    private final String laundryFileNameW = "LaundrySRB";
    private final String medicalEquipmentSRFileNameW = "MedicalEquipmentSRB";
    private final String medicineDeliveryFileNameW = "MedicineDeliverySRB";

    RestoreBackupWrapper() {
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
         reader = new CSVReader();
         writer = new CSVWriter();

         String pathString = new File("").getAbsolutePath();
         File f = new File(pathString);

         backDir = new File(f.getAbsolutePath() + "/backup");
    }

    void restoreAll() throws IOException {
        restoreLocation();
        restoreEmployee();
        restoreMedicalEquipment();
        restoreMainSR();
        restoreExternalTransportSR();
        restoreFoodDeliverySR();
        restoreGiftFloralSR();
        restoreLaundrySR();
        restoreMedicalEquipmentSR();
        restoreMedicineDeliverySR();
    }

    void backupAll() throws FileNotFoundException {
        backupLocation();
        backupEmployee();
        backupMedicalEquipment();
        backupMainSR();
        backupExternalTransportSR();
        backupFoodDeliverySR();
        backupGiftFloralSR();
        backupLaundrySR();
        backupMedicalEquipmentSR();
        backupMedicineDeliverySR();
    }

    void restoreLocation() throws IOException {

        LocationParserI parser = new LocationParserI();

        File filePath = new File(backDir.getAbsolutePath() + locationFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<Location> locationList = parser.fromStringsToObjects(stringList);

        LocationDao.restoreTable(locationList);
    }

    void backupLocation() throws FileNotFoundException {
        LocationParserI parser = new LocationParserI();

        writer.backupDir(locationFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(LocationDao.getAllValues()));
    }

    void restoreEmployee() throws IOException {

        EmployeeParserI parser = new EmployeeParserI();

        File filePath = new File(backDir.getAbsolutePath() + employeeFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<Employee> employeeList = new ArrayList<>();

        EmployeeDao.restoreTable(employeeList);
    }

    void backupEmployee() throws FileNotFoundException {
        EmployeeParserI parser = new EmployeeParserI();

        writer.backupDir(employeeFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(EmployeeDao.getAllValues()));
    }

    void restoreMedicalEquipment() throws IOException {

        MedicalEquipmentParserI parser = new MedicalEquipmentParserI();

        File filePath = new File(backDir.getAbsolutePath() + medicalEquipmentFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<MedicalEquipment> medicalEquipmentList = parser.fromStringsToObjects(stringList);

        MedicalEquipmentDao.restoreTable(medicalEquipmentList);
    }

    void backupMedicalEquipment() throws FileNotFoundException {
        MedicalEquipmentParserI parser = new MedicalEquipmentParserI();

        writer.backupDir(medicalEquipmentFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(MedicalEquipmentDao.getAllValues()));
    }

    void restoreMainSR() throws IOException {

        MainSRParserI parser = new MainSRParserI();

        File filePath = new File(backDir.getAbsolutePath() + mainSRFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<AbstractSR> mainSRList = parser.fromStringsToObjects(stringList);

        MainSRDao.restoreTable(mainSRList);
    }

    void backupMainSR() throws FileNotFoundException {
        MainSRParserI parser = new MainSRParserI();

        writer.backupDir(mainSRFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(MainSRDao.getAllValues()));
    }

    void restoreExternalTransportSR() throws IOException {

        ExternalTransportSRParserI parser = new ExternalTransportSRParserI();

        File filePath = new File(backDir.getAbsolutePath() + externalTransportFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<ExternalTransportSR> externalTransportSRList = parser.fromStringsToObjects(stringList);

        ExternalTransportDao.restoreTable(externalTransportSRList);
    }

    void backupExternalTransportSR() throws FileNotFoundException {
        ExternalTransportSRParserI parser = new ExternalTransportSRParserI();

        writer.backupDir(externalTransportFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(ExternalTransportDao.getAllValues()));
    }

    void restoreFoodDeliverySR() throws IOException {

        FoodDeliverySRParserI parser = new FoodDeliverySRParserI();

        File filePath = new File(backDir.getAbsolutePath() + foodDeliveryFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<FoodDeliverySR> foodDeliverySRList = parser.fromStringsToObjects(stringList);

        FoodDeliveryDao.restoreTable(foodDeliverySRList);
    }

    void backupFoodDeliverySR() throws FileNotFoundException {
        FoodDeliverySRParserI parser = new FoodDeliverySRParserI();

        writer.backupDir(foodDeliveryFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(FoodDeliveryDao.getAllValues()));
    }

    void restoreGiftFloralSR() throws IOException {

        GiftFloralSRParserI parser = new GiftFloralSRParserI();

        File filePath = new File(backDir.getAbsolutePath() + giftFloralFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<GiftFloralSR> giftFloralSRList = parser.fromStringsToObjects(stringList);

        GiftFloralSRDao.restoreTable(giftFloralSRList);
    }

    void backupGiftFloralSR() throws FileNotFoundException {
        GiftFloralSRParserI parser = new GiftFloralSRParserI();

        writer.backupDir(giftFloralFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(GiftFloralSRDao.getAllValues()));
    }

    void restoreLaundrySR() throws IOException {

        LaundrySRParserI parser = new LaundrySRParserI();

        File filePath = new File(backDir.getAbsolutePath() + laundryFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<LaundrySR> laundrySRList = parser.fromStringsToObjects(stringList);

        LaundrySRDao.restoreTable(laundrySRList);
    }

    void backupLaundrySR() throws FileNotFoundException {
        LaundrySRParserI parser = new LaundrySRParserI();

        writer.backupDir(laundryFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(LaundrySRDao.getAllValues()));
    }

    void restoreMedicalEquipmentSR() throws IOException {

        MedicalEquipmentSRParserI parser = new MedicalEquipmentSRParserI();

        File filePath = new File(backDir.getAbsolutePath() + medicalEquipmentSRFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<MedicalEquipmentSR> medicalEquipmentSRList = parser.fromStringsToObjects(stringList);

        MedicalEquipmentSRDao.restoreTable(medicalEquipmentSRList);
    }

    void backupMedicalEquipmentSR() throws FileNotFoundException {
        MedicalEquipmentSRParserI parser = new MedicalEquipmentSRParserI();

        writer.backupDir(medicalEquipmentSRFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(MedicalEquipmentSRDao.getAllValues()));
    }

    void restoreMedicineDeliverySR() throws IOException {

        MedicineDeliverySRParserI parser = new MedicineDeliverySRParserI();

        File filePath = new File(backDir.getAbsolutePath() + medicineDeliveryFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<MedicineDeliverySR> medicineDeliverySRList = parser.fromStringsToObjects(stringList);

        MedicineDeliverySRDao.restoreTable(medicineDeliverySRList);
    }

    void backupMedicineDeliverySR() throws FileNotFoundException {
        MedicineDeliverySRParserI parser = new MedicineDeliverySRParserI();

        writer.backupDir(medicineDeliveryFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(MedicineDeliverySRDao.getAllValues()));
    }

}
