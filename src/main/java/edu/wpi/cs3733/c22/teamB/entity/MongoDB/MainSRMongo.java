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
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MainSR;
import org.bson.Document;

import javax.print.Doc;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainSRMongo implements IDatabase<AbstractSR> {

    private MongoDatabase conn;
    private MongoCollection MainSRTable;
    private IDatabase<Location> LocationTable;
    private IDatabase<Employee> EmployeeTable;

    public MainSRMongo(IDatabase<Location> LocationTable, IDatabase<Employee> EmployeeTable) {
        this.LocationTable = LocationTable;
        this.EmployeeTable = EmployeeTable;
        conn = MongoDB.getBDBMongo();
    }

    public static Document convertSR(AbstractSR mainSR){
        Document document = new Document();
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
        conn.getCollection("MainSR").insertOne(convertSR(object));

    }

    @Override
    public void deleteValue(String objectID) {
        MainSRTable.deleteOne(convertSR(getValue(objectID)));


    }

    @Override
    public void updateValue(AbstractSR object) {
        Document query = new Document("_id", object.getSrID());
        MainSRTable.findOneAndReplace(query, convertSR(object));
    }

    @Override
    public AbstractSR getValue(String objectID){

        MainSR mainSR;
        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = MainSRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        Location location = new Location();
        Employee requestor = new Employee();
        Employee assignedEmployee = new Employee();

        Document mainSRObj = cursor.next();
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

        Document query = new Document();
        FindIterable<Document> iterable = MainSRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

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
