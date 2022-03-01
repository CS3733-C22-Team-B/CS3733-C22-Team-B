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
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ComputerServiceSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MainSR;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComputerServiceSRMongo implements IDatabase<ComputerServiceSR> {

    private MongoDatabase conn;
    private MongoCollection ComputerServiceSRTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public ComputerServiceSRMongo(IDatabase<AbstractSR> MainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = MainSRMongo;
    }

    public static Document convertComputerServiceSR(ComputerServiceSR sr){
        Document document = new Document();
        document.put("_id", sr.getSrID());
        document.put("helpType", sr.getHelpType());

        return document;
    }

    @Override
    public void addValue(ComputerServiceSR object) {
        conn.getCollection("ComputerServiceSR").insertOne(convertComputerServiceSR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        ComputerServiceSRTable.deleteOne(convertComputerServiceSR(getValue(objectID)));
    }

    @Override
    public void updateValue(ComputerServiceSR object) {
        Document query = new Document("_id", object.getSrID());
        ComputerServiceSRTable.findOneAndReplace(query, convertComputerServiceSR(object));
    }

    @Override
    public ComputerServiceSR getValue(String objectID) {
        ComputerServiceSR computerServiceSR;

        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = ComputerServiceSRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        Document computerObj = cursor.next();
        String srID = computerObj.getString("_id");
        String helpType = computerObj.getString("helpType");

        computerServiceSR = new ComputerServiceSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, helpType);

        return computerServiceSR;

    }

    @Override
    public List<ComputerServiceSR> getAllValues() {
        List<ComputerServiceSR> computerServiceSRList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = ComputerServiceSRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String srID = (String) object.get("_id");
            computerServiceSRList.add(getValue(srID));
        }

        return computerServiceSRList;
    }

    @Override
    public void createTable() {
        ComputerServiceSRTable = conn.getCollection("ComputerServiceSR");

    }

    @Override
    public void dropTable() {
        conn.getCollection("ComputerServiceSR").drop();
    }

    @Override
    public void restoreTable(List<ComputerServiceSR> list) {
        createTable();
        List<Document> newList = new ArrayList<>();

        for(ComputerServiceSR computerServiceSR : list) {
            newList.add(convertComputerServiceSR(computerServiceSR));
        }
        ComputerServiceSRTable.insertMany(newList);

    }
}
