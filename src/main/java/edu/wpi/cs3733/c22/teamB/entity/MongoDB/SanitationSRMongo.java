//package edu.wpi.cs3733.c22.teamB.entity.MongoDB;
//
//import com.mongodb.*;
//import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
//import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
//import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
//import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
//import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
//import edu.wpi.cs3733.c22.teamB.entity.objects.services.SanitationSR;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SanitationSRMongo implements IDatabase<SanitationSR> {
//
//    private DB conn;
//    private DBCollection SanitationSRTable;
//    private IDatabase<AbstractSR> MainSRMongo;
//
//    public SanitationSRMongo(IDatabase<AbstractSR> MainSRMongo) {
//        conn = MongoDB.getBDBMongo();
//        this.MainSRMongo = MainSRMongo;
//    }
//
//    public static DBObject convertSanitationSR(SanitationSR sr) {
//        BasicDBObject document = new BasicDBObject();
//        document.put("_id", sr.getSrID());
//        document.put("condition", sr.getCondition());
//
//        return document;
//    }
//
//    @Override
//    public void addValue(SanitationSR object) {
//        conn.getCollection("SanitationSR").insert(convertSanitationSR(object));
//
//    }
//
//    @Override
//    public void deleteValue(String objectID) {
//        SanitationSRTable.remove(convertSanitationSR(getValue(objectID)));
//    }
//
//    @Override
//    public void updateValue(SanitationSR object) {
//        DBObject query = new BasicDBObject("_id", object.getSrID());
//        SanitationSRTable.findAndModify(query, convertSanitationSR(object));
//    }
//
//    @Override
//    public SanitationSR getValue(String objectID) {
//        SanitationSR sanitationSR;
//
//        DBObject query = new BasicDBObject("_id", objectID);
//        DBCursor cursor = SanitationSRTable.find(query);
//
//        AbstractSR mainSR = MainSRMongo.getValue(objectID);
//
//        String status = mainSR.getStatus();
//        Location location = mainSR.getLocation();
//        Employee requestor = mainSR.getRequestor();
//        Employee assignedEmployee = mainSR.getAssignedEmployee();
//        LocalDate dateRequested = mainSR.getDateRequested();
//        String notes = mainSR.getNotes();
//
//        BasicDBObject foodObj = (BasicDBObject) cursor.one();
//        String srID = foodObj.getString("_id");
//        String condition = foodObj.getString("condition");
//
//        sanitationSR = new SanitationSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, condition);
//
//        return sanitationSR;
//    }
//
//    @Override
//    public List<SanitationSR> getAllValues() {
//        List<SanitationSR> sanitationSRList = new ArrayList<>();
//
//        BasicDBObject query = new BasicDBObject();
//        DBCursor cursor = SanitationSRTable.find(query);
//
//        while (cursor.hasNext()) {
//            DBObject object = cursor.next();
//
//            String srID = (String) object.get("_id");
//            sanitationSRList.add(getValue(srID));
//        }
//
//        return sanitationSRList;
//    }
//
//    @Override
//    public void createTable() {
//        SanitationSRTable = conn.getCollection("SanitationSR");
//    }
//
//    @Override
//    public void dropTable() {
//        conn.getCollection("SanitationSR").drop();
//    }
//
//    @Override
//    public void restoreTable(List<SanitationSR> list) {
//        createTable();
//        for (SanitationSR sanitationSR : list) {
//            addValue(sanitationSR);
//        }
//    }
//}
