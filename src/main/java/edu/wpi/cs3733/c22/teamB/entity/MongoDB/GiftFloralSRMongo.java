//package edu.wpi.cs3733.c22.teamB.entity.MongoDB;
//
//import com.mongodb.*;
//import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
//import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
//import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
//import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
//import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
//import edu.wpi.cs3733.c22.teamB.entity.objects.services.GiftFloralSR;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GiftFloralSRMongo implements IDatabase<GiftFloralSR> {
//
//    private DB conn;
//    private DBCollection GiftFloralTable;
//    private IDatabase<AbstractSR> MainSRMongo;
//
//
//    public GiftFloralSRMongo(IDatabase<AbstractSR> mainSRMongo) {
//        conn = MongoDB.getBDBMongo();
//        this.MainSRMongo = mainSRMongo;
//    }
//
//    public static DBObject convertGiftFloralSR(GiftFloralSR sr) {
//        BasicDBObject document = new BasicDBObject();
//        document.put("_id", sr.getSrID());
//        document.put("giftName", sr.getGiftName());
//
//        return document;
//    }
//
//    @Override
//    public void addValue(GiftFloralSR object) {
//        conn.getCollection("GiftFloralSR").insert(convertGiftFloralSR(object));
//    }
//
//    @Override
//    public void deleteValue(String objectID) {
//        GiftFloralTable.remove(convertGiftFloralSR(getValue(objectID)));
//    }
//
//    @Override
//    public void updateValue(GiftFloralSR object) {
//        DBObject query = new BasicDBObject("_id", object.getSrID());
//        GiftFloralTable.findAndModify(query, convertGiftFloralSR(object));
//    }
//
//    @Override
//    public GiftFloralSR getValue(String objectID) {
//        GiftFloralSR giftFloralSR;
//
//        DBObject query = new BasicDBObject("_id", objectID);
//        DBCursor cursor = GiftFloralTable.find(query);
//
//        AbstractSR mainSR = MainSRMongo.getValue(objectID);
//
//        String status = mainSR.getStatus();
////        String srType
//        Location location = mainSR.getLocation();
//        Employee requestor = mainSR.getRequestor();
//        Employee assignedEmployee = mainSR.getAssignedEmployee();
//        LocalDate dateRequested = mainSR.getDateRequested();
//        String notes = mainSR.getNotes();
//
//        BasicDBObject foodObj = (BasicDBObject) cursor.one();
//        String srID = foodObj.getString("_id");
//        String giftName = foodObj.getString("giftName");
//
//        giftFloralSR = new GiftFloralSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, giftName);
//
//        return giftFloralSR;    }
//
//    @Override
//    public List<GiftFloralSR> getAllValues() {
//        List<GiftFloralSR> giftFloralSRList = new ArrayList<>();
//
//        BasicDBObject query = new BasicDBObject();
//        DBCursor cursor = GiftFloralTable.find(query);
//
//        while (cursor.hasNext()) {
//            DBObject object = cursor.next();
//
//            String srID = (String) object.get("_id");
//            giftFloralSRList.add(getValue(srID));
//        }
//
//        return giftFloralSRList;
//    }
//
//    @Override
//    public void createTable() {
//        GiftFloralTable = conn.getCollection("GiftFloralSR");
//    }
//
//    @Override
//    public void dropTable() {
//        conn.getCollection("GiftFloralSR").drop();
//    }
//
//    @Override
//    public void restoreTable(List<GiftFloralSR> list) {
//        createTable();
//        for(GiftFloralSR giftFloralSR : list) {
//            addValue(giftFloralSR);
//        }
//
//    }
//}
