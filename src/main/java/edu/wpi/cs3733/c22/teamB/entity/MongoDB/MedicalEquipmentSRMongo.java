//package edu.wpi.cs3733.c22.teamB.entity.MongoDB;
//
//import com.mongodb.*;
//import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
//import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
//import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
//import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
//import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
//import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicalEquipmentSR;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MedicalEquipmentSRMongo implements IDatabase<MedicalEquipmentSR> {
//    private DB conn;
//    private DBCollection MedicalEquipmentSRTable;
//    private IDatabase<AbstractSR> MainSRMongo;
//    private IDatabase<MedicalEquipment> MedicalEquipmentMongo;
//
//    public MedicalEquipmentSRMongo(IDatabase<AbstractSR> mainSRMongo, IDatabase<MedicalEquipment> MedicalEquipmentMongo) {
//        conn = MongoDB.getBDBMongo();
//        this.MainSRMongo = mainSRMongo;
//        this.MedicalEquipmentMongo = MedicalEquipmentMongo;
//    }
//
//    public static DBObject convertMedicalEquipmentSR(MedicalEquipmentSR sr) {
//        BasicDBObject document = new BasicDBObject();
//        document.put("_id", sr.getSrID());
//        document.put("medicalEquipment", sr.getMedicalEquipment().getEquipmentID());
//
//        return document;
//    }
//
//    @Override
//    public void addValue(MedicalEquipmentSR object) {
//        conn.getCollection("MedicalEquipmentSR").insert(convertMedicalEquipmentSR(object));
//    }
//
//    @Override
//    public void deleteValue(String objectID) {
//        MedicalEquipmentSRTable.remove(convertMedicalEquipmentSR(getValue(objectID)));
//    }
//
//    @Override
//    public void updateValue(MedicalEquipmentSR object) {
//        DBObject query = new BasicDBObject("_id", object.getSrID());
//        MedicalEquipmentSRTable.findAndModify(query, convertMedicalEquipmentSR(object));
//    }
//
//    @Override
//    public MedicalEquipmentSR getValue(String objectID) {
//        MedicalEquipmentSR medicalEquipmentSR;
//
//        DBObject query = new BasicDBObject("_id", objectID);
//        DBCursor cursor = MedicalEquipmentSRTable.find(query);
//
//        AbstractSR mainSR = MainSRMongo.getValue(objectID);
//
//        String status = mainSR.getStatus();
//
//        Location location = mainSR.getLocation();
//        Employee requestor = mainSR.getRequestor();
//        Employee assignedEmployee = mainSR.getAssignedEmployee();
//        LocalDate dateRequested = mainSR.getDateRequested();
//        String notes = mainSR.getNotes();
//
//        BasicDBObject medEqSR = (BasicDBObject) cursor.one();
//        String srID = medEqSR.getString("_id");
//        String medicalEquipmentID = medEqSR.getString("medicalEquipment");
//        MedicalEquipment medicalEquipment = MedicalEquipmentMongo.getValue(medicalEquipmentID);
//
//        medicalEquipmentSR = new MedicalEquipmentSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, medicalEquipment);
//
//        return medicalEquipmentSR;
//    }
//
//    @Override
//    public List<MedicalEquipmentSR> getAllValues() {
//        List<MedicalEquipmentSR> equipmentSRList = new ArrayList<>();
//
//        BasicDBObject query = new BasicDBObject();
//        DBCursor cursor = MedicalEquipmentSRTable.find(query);
//
//        while (cursor.hasNext()) {
//            DBObject object = cursor.next();
//
//            String nodeID = (String) object.get("_id");
//            equipmentSRList.add(getValue(nodeID));
//        }
//
//        return equipmentSRList;
//    }
//
//    @Override
//    public void createTable() {
//        MedicalEquipmentSRTable = conn.getCollection("MedicalEquipmentSR");
//    }
//
//    @Override
//    public void dropTable() {
//        conn.getCollection("MedicalEquipmentSR").drop();
//    }
//
//    @Override
//    public void restoreTable(List<MedicalEquipmentSR> list) {
//        createTable();
//        for(MedicalEquipmentSR medicalEquipmentSR : list) {
//            addValue(medicalEquipmentSR);
//        }
//
//    }
//}
