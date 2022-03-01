package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentMongo implements IDatabase<MedicalEquipment> {

    private MongoDatabase conn;
    private MongoCollection MedicalEquipmentTable;
    private IDatabase<Location> LocationTable;

    public EquipmentMongo(IDatabase<Location> LocationTable) {
        this.LocationTable = LocationTable;
        conn = MongoDB.getBDBMongo();
    }

    public static Document convertEquipment(MedicalEquipment equipment){

        Document document = new Document();
        document.put("_id", equipment.getEquipmentID());
        document.put("equipmentName", equipment.getEquipmentName());
        document.put("equipmentType", equipment.getEquipmentType());
        document.put("manufacturer", equipment.getManufacturer());
        document.put("locationID", equipment.getLocation().getNodeID());
        document.put("status", equipment.getStatus());
        document.put("color", (equipment.getColor()));
        document.put("size", equipment.getSize());
        document.put("description", equipment.getDescription());
        document.put("amount", equipment.getAmount());

        return document;
    }

    @Override
    public void addValue(MedicalEquipment object) {
        conn.getCollection("MedicalEquipment").insertOne(convertEquipment(object));
    }

    @Override
    public void deleteValue(String objectID) {
        MedicalEquipmentTable.deleteOne(convertEquipment(getValue(objectID)));
    }

    @Override
    public void updateValue(MedicalEquipment object) {
        Document query = new Document("_id", object.getEquipmentID());
        MedicalEquipmentTable.findOneAndReplace(query, convertEquipment(object));
    }

    @Override
    public MedicalEquipment getValue(String objectID) {
        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = MedicalEquipmentTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();
        Location location = new Location();


        Document employeeObj = cursor.next();
        String equipmentID = employeeObj.getString("_id");
        String equipmentName = employeeObj.getString("equipmentName");
        String equipmentType = employeeObj.getString("equipmentType");
        String manufacturer = employeeObj.getString("manufacturer");
        String locationID = employeeObj.getString("locationID");
//        try {
//            LocationMongo locationMongo = new LocationMongo();
//            location = locationMongo.getValue(locationID);
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
        location = LocationTable.getValue(locationID);
        String status = employeeObj.getString("status");
        String color = employeeObj.getString("color");
        String size = employeeObj.getString("size");
        String description = employeeObj.getString("description");
        int amount = employeeObj.getInteger("amount");



        MedicalEquipment equipment = new MedicalEquipment(equipmentID, equipmentName, equipmentType, manufacturer, location, status, color, size,  description, amount);
//        System.out.print(employee);
        return equipment;
    }

    @Override
    public List<MedicalEquipment> getAllValues() {
        List<MedicalEquipment> equipmentList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = MedicalEquipmentTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String nodeID = (String) object.get("_id");
            equipmentList.add(getValue(nodeID));
        }

        return equipmentList;
    }

    @Override
    public void createTable() {

        MedicalEquipmentTable = conn.getCollection("MedicalEquipment");

    }

    @Override
    public void dropTable() {
        conn.getCollection("MedicalEquipment").drop();
    }

    @Override
    public void restoreTable(List<MedicalEquipment> list) {
        createTable();
        List<Document> newList = new ArrayList<>();
        for(MedicalEquipment equipment : list) {
            newList.add(convertEquipment(equipment));
        }
        MedicalEquipmentTable.insertMany(newList);
    }
}
