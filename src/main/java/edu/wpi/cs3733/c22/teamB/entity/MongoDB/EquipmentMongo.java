package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentMongo implements IDatabase<MedicalEquipment> {

    private DB conn;
    private DBCollection MedicalEquipmentTable;
    private IDatabase<Location> LocationTable;

    public EquipmentMongo(IDatabase<Location> LocationTable) {
        this.LocationTable = LocationTable;
        conn = MongoDB.getBDBMongo();
    }

    public static DBObject convertEquipment(MedicalEquipment equipment){

        BasicDBObject document = new BasicDBObject();
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
        conn.getCollection("MedicalEquipment").insert(convertEquipment(object));
    }

    @Override
    public void deleteValue(String objectID) {
        MedicalEquipmentTable.remove(convertEquipment(getValue(objectID)));
    }

    @Override
    public void updateValue(MedicalEquipment object) {
        DBObject query = new BasicDBObject("_id", object.getEquipmentID());
        MedicalEquipmentTable.findAndModify(query, convertEquipment(object));
    }

    @Override
    public MedicalEquipment getValue(String objectID) {
        DBObject query = new BasicDBObject("_id", objectID);
        DBCursor cursor = MedicalEquipmentTable.find(query);
        Location location = new Location();


        BasicDBObject employeeObj = (BasicDBObject) cursor.one();
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
        int amount = Integer.parseInt(employeeObj.getString("amount"));



        MedicalEquipment equipment = new MedicalEquipment(equipmentID, equipmentName, equipmentType, manufacturer, location, status, color, size,  description, amount);
//        System.out.print(employee);
        return equipment;
    }

    @Override
    public List<MedicalEquipment> getAllValues() {
        List<MedicalEquipment> equipmentList = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = MedicalEquipmentTable.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();

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

        for(MedicalEquipment equipment : list) {
            addValue(equipment);
        }
    }
}
