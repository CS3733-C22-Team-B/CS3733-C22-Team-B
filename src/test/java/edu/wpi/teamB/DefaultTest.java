///*-------------------------*/
///* DO NOT DELETE THIS TEST */
///*-------------------------*/
//
//package edu.wpi.teamB;
//
//import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
//import edu.wpi.cs3733.c22.teamB.entity.MongoDB.*;
//import edu.wpi.cs3733.c22.teamB.entity.PasswordHashing;
//import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
//import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
//import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
//import org.junit.jupiter.api.Test;
//
//import java.net.UnknownHostException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DefaultTest {
//
//    @Test
//    public void test() {
////        String password = "Aqwe12";
////        System.out.println(PasswordHashing.hashPassword(password));
////
////        DatabaseWrapper db = new DatabaseWrapper();
////        Employee employee = new Employee("ASD", "asd", "ad", "asd", 12, "asd", "asd", "Asd", "asd");
//////        db.addEmployee(employee);
////        assertEquals(db.getEmployee("ASD").getPassword(), PasswordHashing.hashPassword("asd"));
//    }
//
////    @Test
////    public void TestID() {
////        Location location1 = new Location(12, 12, "03", "wqe", "HALL", "asd", "asd");
////        System.out.println(location1.getNodeID());
////        assertEquals("bHALL01603",location1.getNodeID());
////        MedicalEquipment medicalEquipment = new MedicalEquipment( "chair", "chair", "manu", location1, "asd", "asd","d","des",2);
////        System.out.println(medicalEquipment.getEquipmentID());
////        assertEquals("bCHAIR00202",medicalEquipment.getEquipmentID());
////        Employee employee = new Employee(  "E", "manuel", "bigBoy", 2, "asd","d","des","8677carsforkids");
////        System.out.println(employee.getEmployeeID());
////        assertEquals("bE00202",employee.getEmployeeID());
////    }
//
//    @Test public void testDrop() throws UnknownHostException {
//        MongoDB.getConnection();
//
//        LocationMongo locationMongo = new LocationMongo();
//        EmployeeMongo employeeMongo = new EmployeeMongo();
//        MainSRMongo mainSRMongo = new MainSRMongo(locationMongo, employeeMongo);
//        ExternalTransportSRMongo externalTransportSRMongo = new ExternalTransportSRMongo(mainSRMongo);
//        externalTransportSRMongo.dropTable();
//
//
//    }
//}
