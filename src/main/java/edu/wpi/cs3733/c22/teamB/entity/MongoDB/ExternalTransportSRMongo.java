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
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ExternalTransportSR;
import org.bson.Document;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExternalTransportSRMongo implements IDatabase<ExternalTransportSR> {

    private MongoDatabase conn;
    private MongoCollection ExternalTransportTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public ExternalTransportSRMongo(IDatabase<AbstractSR> MainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = MainSRMongo;
    }

    public static Document convertExternalTransportSR(ExternalTransportSR sr){
        Document document = new Document();
        document.put("_id", sr.getSrID());
        document.put("patientID", sr.getPatientID());
        document.put("dropOffLocation", sr.getDropOffLocation());
        document.put("formOfTransport", sr.getFormOfTransport());

        return document;
    }

    @Override
    public void addValue(ExternalTransportSR object) {
        conn.getCollection("ExternalTransportSR").insertOne(convertExternalTransportSR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        ExternalTransportTable.deleteOne(convertExternalTransportSR(getValue(objectID)));
    }

    @Override
    public void updateValue(ExternalTransportSR object) {
        Document query = new Document("_id", object.getSrID());
        ExternalTransportTable.findOneAndReplace(query, convertExternalTransportSR(object));
    }

    @Override
    public ExternalTransportSR getValue(String objectID) {
        ExternalTransportSR externalTransportSR;

        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = ExternalTransportTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();



        Document externalObj = cursor.next();
        String srID = externalObj.getString("_id");
        String patientID = externalObj.getString("patientID");
        String dropOffLocation = externalObj.getString("dropOffLocation");
        String formOfTransport = externalObj.getString("formOfTransport");

        externalTransportSR = new ExternalTransportSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, patientID, dropOffLocation, formOfTransport);

        return externalTransportSR;    }

    @Override
    public List<ExternalTransportSR> getAllValues() {
        List<ExternalTransportSR> externalTransportSRList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = ExternalTransportTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String srID = (String) object.get("_id");
            externalTransportSRList.add(getValue(srID));
        }

        return externalTransportSRList;
    }

    @Override
    public void createTable() {
        ExternalTransportTable = conn.getCollection("ExternalTransportSR");

    }

    @Override
    public void dropTable() {
        conn.getCollection("ExternalTransportSR").drop();
    }

    @Override
    public void restoreTable(List<ExternalTransportSR> list) {
        createTable();

        for(ExternalTransportSR externalTransportSR : list) {
            addValue(externalTransportSR);
        }
    }
}
