package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ComputerServiceSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MainSR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComputerServiceSRMongo implements IDatabase<ComputerServiceSR> {

    private DB conn;
    private DBCollection ComputerServiceSRTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public ComputerServiceSRMongo(IDatabase<AbstractSR> MainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = MainSRMongo;
    }

    public static DBObject convertComputerServiceSR(ComputerServiceSR sr){
        BasicDBObject document = new BasicDBObject();
        document.put("_id", sr.getSrID());
        document.put("helpType", sr.getHelpType());

        return document;
    }

    @Override
    public void addValue(ComputerServiceSR object) {
        conn.getCollection("ComputerServiceSR").insert(convertComputerServiceSR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        ComputerServiceSRTable.remove(convertComputerServiceSR(getValue(objectID)));
    }

    @Override
    public void updateValue(ComputerServiceSR object) {
        DBObject query = new BasicDBObject("_id", object.getSrID());
        ComputerServiceSRTable.findAndModify(query, convertComputerServiceSR(object));
    }

    @Override
    public ComputerServiceSR getValue(String objectID) {
        ComputerServiceSR computerServiceSR;

        DBObject query = new BasicDBObject("_id", objectID);
        DBCursor cursor = ComputerServiceSRTable.find(query);

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();



        BasicDBObject computerObj = (BasicDBObject) cursor.one();
        String srID = computerObj.getString("_id");
        String helpType = computerObj.getString("helpType");

        computerServiceSR = new ComputerServiceSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, helpType);

        return computerServiceSR;

    }

    @Override
    public List<ComputerServiceSR> getAllValues() {
        List<ComputerServiceSR> computerServiceSRList = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = ComputerServiceSRTable.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();

            String srID = (String) object.get("_id");
            computerServiceSRList.add(getValue(srID));
        }

        return null;
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

        for(ComputerServiceSR computerServiceSR : list) {
            addValue(computerServiceSR);
        }

    }
}
