package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ComputerServiceSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.LaundrySR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LaundrySRMongo implements IDatabase<LaundrySR> {

    private DB conn;
    private DBCollection LaundrySRTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public LaundrySRMongo(IDatabase<AbstractSR> MainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = MainSRMongo;
    }

    public static DBObject convertLaundrySR(LaundrySR sr) {
        BasicDBObject document = new BasicDBObject();
        document.put("_id", sr.getSrID());

        return document;
    }

    @Override
    public void addValue(LaundrySR object) {
        conn.getCollection("LaundrySR").insert(convertLaundrySR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        LaundrySRTable.remove(convertLaundrySR(getValue(objectID)));
    }

    @Override
    public void updateValue(LaundrySR object) {
        DBObject query = new BasicDBObject("_id", object.getSrID());
        LaundrySRTable.findAndModify(query, convertLaundrySR(object));
    }

    @Override
    public LaundrySR getValue(String objectID) {
        LaundrySR laundrySR;

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        laundrySR = new LaundrySR(objectID, status, location, requestor, assignedEmployee, dateRequested, notes);

        return laundrySR;
    }

    @Override
    public List<LaundrySR> getAllValues() {
        List<LaundrySR> laundrySRList = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = LaundrySRTable.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();

            String srID = (String) object.get("_id");
            laundrySRList.add(getValue(srID));
        }

        return laundrySRList;
    }

    @Override
    public void createTable() {
        LaundrySRTable = conn.getCollection("LaundrySR");
    }

    @Override
    public void dropTable() {
        conn.getCollection("LaundrySR").drop();
    }

    @Override
    public void restoreTable(List<LaundrySR> list) {
        createTable();
        for(LaundrySR laundrySR : list) {
            addValue(laundrySR);
        }
    }
}
