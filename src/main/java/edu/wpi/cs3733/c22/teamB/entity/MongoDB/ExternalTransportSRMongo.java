package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ComputerServiceSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ExternalTransportSR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExternalTransportSRMongo implements IDatabase<ExternalTransportSR> {

    private DB conn;
    private DBCollection ExternalTransportTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public ExternalTransportSRMongo(IDatabase<AbstractSR> MainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = MainSRMongo;
    }

    public static DBObject convertExternalTransportSR(ExternalTransportSR sr){
        BasicDBObject document = new BasicDBObject();
        document.put("_id", sr.getSrID());
        document.put("patientID", sr.getPatientID());
        document.put("dropOffLocation", sr.getDropOffLocation());
        document.put("formOfTransport", sr.getFormOfTransport());

        return document;
    }

    @Override
    public void addValue(ExternalTransportSR object) {
        conn.getCollection("ExternalServiceSR").insert(convertExternalTransportSR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        ExternalTransportTable.remove(convertExternalTransportSR(getValue(objectID)));
    }

    @Override
    public void updateValue(ExternalTransportSR object) {
        DBObject query = new BasicDBObject("_id", object.getSrID());
        ExternalTransportTable.findAndModify(query, convertExternalTransportSR(object));
    }

    @Override
    public ExternalTransportSR getValue(String objectID) {
        ExternalTransportSR externalTransportSR;

        DBObject query = new BasicDBObject("_id", objectID);
        DBCursor cursor = ExternalTransportTable.find(query);

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();



        BasicDBObject externalObj = (BasicDBObject) cursor.one();
        String srID = externalObj.getString("_id");
        String patientID = externalObj.getString("patientID");
        String dropOffLocation = externalObj.getString("dropOffLocation");
        String formOfTransport = externalObj.getString("formOfTransport");

        externalTransportSR = new ExternalTransportSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, patientID, dropOffLocation, formOfTransport);

        return externalTransportSR;    }

    @Override
    public List<ExternalTransportSR> getAllValues() {
        List<ExternalTransportSR> externalTransportSRList = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = ExternalTransportTable.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();

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
