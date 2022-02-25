package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class LocationMongo implements IDatabase<Location> {

    private MongoDatabase conn;
    private MongoCollection LocationTable;

    public LocationMongo(){
        conn = MongoDB.getBDBMongo();
    }

    public static Document convertLocation(Location location){
        Document document = new Document();
        document.put("_id", location.getNodeID());
        document.put("xcoord", location.getXcoord());
        document.put("ycoord", location.getYcoord());
        document.put("floor", location.getFloor());
        document.put("building", location.getBuilding());
        document.put("nodeType", location.getNodeType());
        document.put("longName", location.getLongName());
        document.put("shortName", location.getShortName());

        return document;
    }

    @Override
    public void addValue(Location object) {

        conn.getCollection("Location").insertOne(convertLocation(object));

    }

    @Override
    public void deleteValue(String objectID) {

        LocationTable.deleteOne(convertLocation(getValue(objectID)));
    }

    @Override
    public void updateValue(Location object) {
        Document query = new Document("_id", object.getNodeID());
        LocationTable.findOneAndReplace(query, convertLocation(object));
    }

    @Override
    public Location getValue(String objectID) {
        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = LocationTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        Document locationObj = cursor.next();
        String nodeID = locationObj.getString("_id");
        int xcoord = locationObj.getInteger("xcoord");
        int ycoord = locationObj.getInteger("ycoord");
        String floor = locationObj.getString("floor");
        String building = locationObj.getString("building");
        String nodeType = locationObj.getString("nodeType");
        String longName = locationObj.getString("longName");
        String shortName = locationObj.getString("shortName");

        Location location = new Location(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName);

        return location;
//        return null;
    }

    @Override
    public List<Location> getAllValues() {
        List<Location> locationList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = LocationTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String nodeID = (String) object.get("_id");
            locationList.add(getValue(nodeID));
            }

        System.out.println(locationList);
        return locationList;
//        return null;
    }

    @Override
    public void createTable()  {
        LocationTable = conn.getCollection("Location");
    }

    @Override
    public void dropTable() {
        conn.getCollection("Location").drop();
    }

    @Override
    public void restoreTable(List<Location> list) {
        createTable();

        for(Location location : list) {
            addValue(location);
        }

    }
}
