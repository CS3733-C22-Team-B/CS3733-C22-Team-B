package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MainSR;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainSRMongo implements IDatabase<AbstractSR> {

    private DB conn;
    private DBCollection MainSRTable;
    private IDatabase<Location> LocationTable;
    private IDatabase<Employee> EmployeeTable;

    public MainSRMongo(IDatabase<Location> LocationTable, IDatabase<Employee> EmployeeTable) throws UnknownHostException {
        this.LocationTable = LocationTable;
        this.EmployeeTable = EmployeeTable;
        conn = MongoDB.getBDBMongo();
    }

    public static DBObject convertSR(AbstractSR mainSR){
        BasicDBObject document = new BasicDBObject();
        document.put("_id", mainSR.getSrID());
        document.put("srType", mainSR.getSrType());
        document.put("status", mainSR.getStatus());
        document.put("locationID", mainSR.getLocation().getNodeID());
        document.put("requestorID", mainSR.getRequestor().getEmployeeID());
        document.put("assignedEmployeeID", mainSR.getAssignedEmployee().getEmployeeID());
        document.put("dateRequested", mainSR.getDateRequested().toString());
        document.put("notes", mainSR.getNotes());

        return document;
    }

    @Override
    public void addValue(AbstractSR object) {
        conn.getCollection("MainSR").insert(convertSR(object));

    }

    @Override
    public void deleteValue(String objectID) {
        MainSRTable.remove(convertSR(getValue(objectID)));


    }

    @Override
    public void updateValue(AbstractSR object) {
        DBObject query = new BasicDBObject("_id", object.getSrID());
        MainSRTable.findAndModify(query, convertSR(object));
    }

    @Override
    public AbstractSR getValue(String objectID){

        MainSR mainSR;
        DBObject query = new BasicDBObject("_id", objectID);
        DBCursor cursor = MainSRTable.find(query);
        Location location = new Location();
        Employee requestor = new Employee();
        Employee assignedEmployee = new Employee();

        BasicDBObject mainSRObj = (BasicDBObject) cursor.one();
        String srID = mainSRObj.getString("_id");
        String srType = mainSRObj.getString("srType");
        String status = mainSRObj.getString("status");
        String locationID = mainSRObj.getString("locationID");
        location = LocationTable.getValue(locationID);
        String requestorID = mainSRObj.getString("requestorID");
        String assignedEmployeeID = mainSRObj.getString("assignedEmployeeID");
        requestor = EmployeeTable.getValue(requestorID);
        assignedEmployee = EmployeeTable.getValue(assignedEmployeeID);
        String date = mainSRObj.getString("dateRequested");
        LocalDate dateRequested = LocalDate.parse(date);
        String notes = mainSRObj.getString("notes");
        System.out.println(location);

        mainSR = new MainSR(objectID, srType, status, location, requestor, assignedEmployee, dateRequested, notes);

        return mainSR;
    }

    @Override
    public List<AbstractSR> getAllValues() {
        List<AbstractSR> MainSRList = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = MainSRTable.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();

            String nodeID = (String) object.get("_id");
            MainSRList.add(getValue(nodeID));
        }

        return MainSRList;

    }

    @Override
    public void createTable() {
        MainSRTable = conn.getCollection("MainSR");

    }

    @Override
    public void dropTable() {
        conn.getCollection("MainSR").drop();
    }

    @Override
    public void restoreTable(List<AbstractSR> list) {

        createTable();;
        for(AbstractSR abstractSR : list) {
            addValue(abstractSR);
        }
    }
}
