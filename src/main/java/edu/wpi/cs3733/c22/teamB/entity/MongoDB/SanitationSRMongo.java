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
import edu.wpi.cs3733.c22.teamB.entity.objects.services.SanitationSR;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SanitationSRMongo implements IDatabase<SanitationSR> {

    private MongoDatabase conn;
    private MongoCollection SanitationSRTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public SanitationSRMongo(IDatabase<AbstractSR> MainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = MainSRMongo;
    }

    public static Document convertSanitationSR(SanitationSR sr) {
        Document document = new Document();
        document.put("_id", sr.getSrID());
        document.put("condition", sr.getCondition());

        return document;
    }

    @Override
    public void addValue(SanitationSR object) {
        conn.getCollection("SanitationSR").insertOne(convertSanitationSR(object));

    }

    @Override
    public void deleteValue(String objectID) {
        SanitationSRTable.deleteOne(convertSanitationSR(getValue(objectID)));
    }

    @Override
    public void updateValue(SanitationSR object) {
        Document query = new Document("_id", object.getSrID());
        SanitationSRTable.findOneAndReplace(query, convertSanitationSR(object));
    }

    @Override
    public SanitationSR getValue(String objectID) {
        SanitationSR sanitationSR;

        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = SanitationSRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();


        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        Document foodObj = cursor.next();
        String srID = foodObj.getString("_id");
        String condition = foodObj.getString("condition");

        sanitationSR = new SanitationSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, condition);

        return sanitationSR;
    }

    @Override
    public List<SanitationSR> getAllValues() {
        List<SanitationSR> sanitationSRList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = SanitationSRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String srID = (String) object.get("_id");
            sanitationSRList.add(getValue(srID));
        }

        return sanitationSRList;
    }

    @Override
    public void createTable() {
        SanitationSRTable = conn.getCollection("SanitationSR");
    }

    @Override
    public void dropTable() {
        conn.getCollection("SanitationSR").drop();
    }

    @Override
    public void restoreTable(List<SanitationSR> list) {
        createTable();
        List<Document> newList = new ArrayList<>();

        for (SanitationSR sanitationSR : list) {
            newList.add(convertSanitationSR(sanitationSR));
        }
        SanitationSRTable.insertMany(newList);
    }
}
