package edu.wpi.cs3733.c22.teamB.entity;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.*;
import edu.wpi.cs3733.c22.teamB.entity.parsers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private IDatabase<ComputerServiceSR> ComputerServiceSRDao;
    private IDatabase<SanitationSR> SanitationSRDao;
    private IDatabase<AbstractSR> MainSRDao;

    private CSVReader reader;
    private CSVWriter writer;

    private File backDir;

    // Read paths
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
    private final String computerServiceFileName = "/ComputerServiceSRB.csv";
    private final String sanitationFileName = "/SanitationSRB.csv";

    // Write file names
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
    private final String computerServiceFileNameW = "ComputerServiceSRB";
    private final String sanitationFileNameW = "SanitationSRB";

    // First restore paths
    private final String locationFileNameF = "TowerLocationsB.csv";
    private final String employeeFileNameF = "EmployeeB.csv";
    private final String medicalEquipmentFileNameF = "MedicalEquipmentB.csv";
    private final String computerSRFileNameF = "ComputerServiceSRB.csv";
    private final String externalTransportSRFileNameF = "ExternalTransportSRB.csv";
    private final String foodDeliverySRFileNameF = "FoodDeliverySRB.csv";
    private final String giftFloralSRFileNameF = "GiftFloralSRB.csv";
    private final String laundrySRFileNameF = "LaundrySRB.csv";
    private final String mainSRFileNameF = "MainSR.csv";
    private final String medicalEquipmentSRFileNameF = "MedicalEquipmentSRB.csv";
    private final String medicineDeliveryFileNameF = "MedicineDeliverySRB.csv";
    private final String sanitationFileNameF = "SanitationSRB.csv";


    RestoreBackupWrapper(IDatabase<Location> LocationDao, IDatabase<Employee> EmployeeDao, IDatabase<MedicalEquipment> MedicalEquipmentDao,
                         IDatabase<AbstractSR> MainSRDao, IDatabase<ExternalTransportSR> ExternalTransportDao, IDatabase<FoodDeliverySR> FoodDeliveryDao,
                         IDatabase<GiftFloralSR> GiftFloralSRDao, IDatabase<LaundrySR> LaundrySRDao, IDatabase<MedicalEquipmentSR> MedicalEquipmentSRDao,
                         IDatabase<MedicineDeliverySR> MedicineDeliverySRDao, IDatabase<ComputerServiceSR> ComputerServiceSRDao, IDatabase<SanitationSR> SanitationSRDao) {

        setDao(LocationDao, EmployeeDao, MedicalEquipmentDao, MainSRDao, ExternalTransportDao, FoodDeliveryDao, GiftFloralSRDao, LaundrySRDao, MedicalEquipmentSRDao, MedicineDeliverySRDao, ComputerServiceSRDao, SanitationSRDao);

        reader = new CSVReader();
        writer = new CSVWriter();

        String pathString = new File("").getAbsolutePath();
        File f = new File(pathString);

        backDir = new File(f.getAbsolutePath() + "/backup");
    }

    public void setDao(IDatabase<Location> LocationDao, IDatabase<Employee> EmployeeDao, IDatabase<MedicalEquipment> MedicalEquipmentDao,
                       IDatabase<AbstractSR> MainSRDao, IDatabase<ExternalTransportSR> ExternalTransportDao, IDatabase<FoodDeliverySR> FoodDeliveryDao,
                       IDatabase<GiftFloralSR> GiftFloralSRDao, IDatabase<LaundrySR> LaundrySRDao, IDatabase<MedicalEquipmentSR> MedicalEquipmentSRDao,
                       IDatabase<MedicineDeliverySR> MedicineDeliverySRDao, IDatabase<ComputerServiceSR> ComputerServiceSRDao, IDatabase<SanitationSR> SanitationSRDao) {

        this.LocationDao = LocationDao;
        this.EmployeeDao = EmployeeDao;
        this.MedicalEquipmentDao = MedicalEquipmentDao;
        this.MainSRDao = MainSRDao;
        this.ExternalTransportDao = ExternalTransportDao;
        this.FoodDeliveryDao = FoodDeliveryDao;
        this.GiftFloralSRDao = GiftFloralSRDao;
        this.LaundrySRDao = LaundrySRDao;
        this.MedicalEquipmentSRDao = MedicalEquipmentSRDao;
        this.MedicineDeliverySRDao = MedicineDeliverySRDao;
        this.ComputerServiceSRDao = ComputerServiceSRDao;
        this.SanitationSRDao = SanitationSRDao;
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
        restoreComputerServiceSR();
        restoreSanitationSR();
    }

    void backupAll() throws FileNotFoundException {
        try {
            backupLocation();
        } catch (NullPointerException e) {
            System.out.println("Location Table/Collection is empty");
        }

        try {
            backupEmployee();
        } catch (NullPointerException e) {
            System.out.println("Employee Table/Collection is empty");
        }

        try {
            backupMedicalEquipment();;
        } catch (NullPointerException e) {
            System.out.println("MedicalEquipment Table/Collection is empty");
        }

        try {
            backupMainSR();
        } catch (NullPointerException e) {
            System.out.println("MainSR Table/Collection is empty");
        }

        try {
            backupExternalTransportSR();
        } catch (NullPointerException e) {
            System.out.println("ExternalTransport Table/Collection is empty");
        }

        try {
            backupFoodDeliverySR();
        } catch (NullPointerException e) {
            System.out.println("FoodDeliverySR Table/Collection is empty");
        }

        try {
            backupGiftFloralSR();
        } catch (NullPointerException e) {
            System.out.println("GiftFloralSR Table/Collection is empty");
        }

        try {
            backupLaundrySR();
        } catch (NullPointerException e) {
            System.out.println("LaundrySR Table/Collection is empty");
        }

        try {
            backupMedicalEquipmentSR();
        } catch (NullPointerException e) {
            System.out.println("MedicalEquipmentSR Table/Collection is empty");
        }

        try {
            backupMedicineDeliverySR();
        } catch (NullPointerException e) {
            System.out.println("MedicineDeliverySR Table/Collection is empty");
        }

        try {
            backupComputerServiceSR();
        } catch (NullPointerException e) {
            System.out.println("ComputerServiceSR Table/Collection is empty");
        }

        try {
            backupSanitationSR();
        } catch (NullPointerException e) {
            System.out.println("SanitationSR Table/Collection is empty");
        }
    }

    void firstRestore() throws IOException{
        CSVReader reader = new CSVReader();

        LocationParserI parserL = new LocationParserI();
        EmployeeParserI parserE = new EmployeeParserI();
        MedicalEquipmentParserI parserM = new MedicalEquipmentParserI();
        ComputerServiceSRParserI parserC = new ComputerServiceSRParserI();
        ExternalTransportSRParserI parserExternal = new ExternalTransportSRParserI();
        FoodDeliverySRParserI parserF = new FoodDeliverySRParserI();
        GiftFloralSRParserI parserG = new GiftFloralSRParserI();
        LaundrySRParserI parserLaundry = new LaundrySRParserI();
        MainSRParserI parserMain = new MainSRParserI();
        MedicalEquipmentSRParserI parserMedSR = new MedicalEquipmentSRParserI();
        MedicineDeliverySRParserI parserMedD = new MedicineDeliverySRParserI();
        SanitationSRParserI parserS = new SanitationSRParserI();

        List<String> locationStringList = reader.firstRestore(locationFileNameF);
        List<String> employeeStringList = reader.firstRestore(employeeFileNameF);
        List<String> medicalEquipmentStringList = reader.firstRestore(medicalEquipmentFileNameF);
        List<String> computerSRStringList = reader.firstRestore(computerSRFileNameF);
        List<String> externalSRStringList = reader.firstRestore(externalTransportSRFileNameF);
        List<String> foodSRStringList = reader.firstRestore(foodDeliverySRFileNameF);
        List<String> giftSRStringList = reader.firstRestore(giftFloralSRFileNameF);
        List<String> laundrySRStringList = reader.firstRestore(laundrySRFileNameF);
        List<String> mainSRStringList = reader.firstRestore(mainSRFileNameF);
        List<String> medicalSRStringList = reader.firstRestore(medicalEquipmentSRFileNameF);
        List<String> medicineSRStringList = reader.firstRestore(medicineDeliveryFileNameF);
        List<String> sanitationSRStringList = reader.firstRestore(sanitationFileNameF);

        List<Location> locationList = parserL.fromStringsToObjects(locationStringList);
        List<Employee> employeeList = parserE.fromStringsToObjects(employeeStringList);
        List<MedicalEquipment> medicalEquipmentList = parserM.fromStringsToObjects(medicalEquipmentStringList);
        List<ComputerServiceSR> computerSRList = parserC.fromStringsToObjects(computerSRStringList);
        List<ExternalTransportSR> externalTransportSRList = parserExternal.fromStringsToObjects(externalSRStringList);
        List<FoodDeliverySR> foodDeliverySRList = parserF.fromStringsToObjects(foodSRStringList);
        List<GiftFloralSR> giftFloralSRList = parserG.fromStringsToObjects(giftSRStringList);
        List<LaundrySR> laundrySRList = parserLaundry.fromStringsToObjects(laundrySRStringList);
        List<AbstractSR> mainSRList = parserMain.fromStringsToObjects(mainSRStringList);
        List<MedicalEquipmentSR> medicalEquipmentSRList = parserMedSR.fromStringsToObjects(medicalSRStringList);
        List<MedicineDeliverySR> medicineDeliverySRList = parserMedD.fromStringsToObjects(medicineSRStringList);
        List<SanitationSR> sanitationSRList = parserS.fromStringsToObjects(sanitationSRStringList);


        LocationDao.restoreTable(locationList);
        EmployeeDao.restoreTable(employeeList);
        MedicalEquipmentDao.restoreTable(medicalEquipmentList);
        ComputerServiceSRDao.restoreTable(computerSRList);
        ExternalTransportDao.restoreTable(externalTransportSRList);
        FoodDeliveryDao.restoreTable(foodDeliverySRList);
        GiftFloralSRDao.restoreTable(giftFloralSRList);
        LaundrySRDao.restoreTable(laundrySRList);
        MainSRDao.restoreTable(mainSRList);
        MedicalEquipmentSRDao.restoreTable(medicalEquipmentSRList);
        MedicineDeliverySRDao.restoreTable(medicineDeliverySRList);
        SanitationSRDao.restoreTable(sanitationSRList);
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
        List<Employee> employeeList = parser.fromStringsToObjects(stringList);

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

    void restoreComputerServiceSR() throws IOException {
        ComputerServiceSRParserI parser = new ComputerServiceSRParserI();

        File filePath = new File(backDir.getAbsolutePath() + computerServiceFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<ComputerServiceSR> computerServiceSRList = parser.fromStringsToObjects(stringList);

        ComputerServiceSRDao.restoreTable(computerServiceSRList);
    }

    void backupComputerServiceSR() throws FileNotFoundException {
        ComputerServiceSRParserI parser = new ComputerServiceSRParserI();

        writer.backupDir(computerServiceFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(ComputerServiceSRDao.getAllValues()));
    }

    void restoreSanitationSR() throws IOException {
        SanitationSRParserI parser = new SanitationSRParserI();

        File filePath = new File(backDir.getAbsolutePath() + sanitationFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<SanitationSR> sanitationSRList = parser.fromStringsToObjects(stringList);

        SanitationSRDao.restoreTable(sanitationSRList);
    }

    void backupSanitationSR() throws FileNotFoundException {
        SanitationSRParserI parser = new SanitationSRParserI();

        writer.backupDir(sanitationFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(SanitationSRDao.getAllValues()));
    }

}
