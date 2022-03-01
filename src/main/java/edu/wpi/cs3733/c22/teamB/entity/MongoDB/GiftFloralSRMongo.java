package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.GiftFloralSR;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GiftFloralSRMongo implements IDatabase<GiftFloralSR> {

    private MongoDatabase conn;
    private MongoCollection GiftFloralTable;
    private IDatabase<AbstractSR> MainSRMongo;


    public GiftFloralSRMongo(IDatabase<AbstractSR> mainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = mainSRMongo;
    }

    public static Document convertGiftFloralSR(GiftFloralSR sr) {
        Document document = new Document();
        document.put("_id", sr.getSrID());
        document.put("giftName", sr.getGiftName());

        return document;
    }

    @Override
    public void addValue(GiftFloralSR object) {
        conn.getCollection("GiftFloralSR").insertOne(convertGiftFloralSR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        GiftFloralTable.deleteOne(convertGiftFloralSR(getValue(objectID)));
    }

    @Override
    public void updateValue(GiftFloralSR object) {
        Document query = new Document("_id", object.getSrID());
        GiftFloralTable.findOneAndReplace(query, convertGiftFloralSR(object));
    }

    @Override
    public GiftFloralSR getValue(String objectID) {
        GiftFloralSR giftFloralSR;

        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = GiftFloralTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        Document foodObj = cursor.next();
        String srID = foodObj.getString("_id");
        String giftName = foodObj.getString("giftName");

        giftFloralSR = new GiftFloralSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, giftName);

        return giftFloralSR;    }

    @Override
    public List<GiftFloralSR> getAllValues() {
        List<GiftFloralSR> giftFloralSRList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = GiftFloralTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String srID = (String) object.get("_id");
            giftFloralSRList.add(getValue(srID));
        }

        return giftFloralSRList;
    }

    @Override
    public void createTable() {
        GiftFloralTable = conn.getCollection("GiftFloralSR");
    }

    @Override
    public void dropTable() {
        conn.getCollection("GiftFloralSR").drop();
    }

    @Override
    public void restoreTable(List<GiftFloralSR> list) {
        createTable();
        List<Document> newList = new ArrayList<>();

        for(GiftFloralSR giftFloralSR : list) {
            newList.add(convertGiftFloralSR(giftFloralSR));
        }
        GiftFloralTable.insertMany(newList);
    }
}
