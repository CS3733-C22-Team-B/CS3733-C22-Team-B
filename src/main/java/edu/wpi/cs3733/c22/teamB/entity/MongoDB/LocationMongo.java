package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class LocationMongo implements IDatabase<Location> {

    private DB conn;
    private DBCollection LocationTable;

    public LocationMongo(){
        conn = MongoDB.getBDBMongo();
    }

    public static DBObject convertLocation(Location location){
        BasicDBObject document = new BasicDBObject();
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

        conn.getCollection("Location").insert(convertLocation(object));

    }

    @Override
    public void deleteValue(String objectID) {

        LocationTable.remove(convertLocation(getValue(objectID)));
    }

    @Override
    public void updateValue(Location object) {
        DBObject query = new BasicDBObject("_id", object.getNodeID());
        LocationTable.findAndModify(query, convertLocation(object));
    }

    @Override
    public Location getValue(String objectID) {
        DBObject query = new BasicDBObject("_id", objectID);
        DBCursor cursor = LocationTable.find(query);

        BasicDBObject locationObj = (BasicDBObject) cursor.one();
        String nodeID = locationObj.getString("_id");
        int xcoord = Integer.parseInt(locationObj.getString("xcoord"));
        int ycoord = Integer.parseInt(locationObj.getString("ycoord"));
        String floor = locationObj.getString("floor");
        String building = locationObj.getString("building");
        String nodeType = locationObj.getString("nodeType");
        String longName = locationObj.getString("longName");
        String shortName = locationObj.getString("shortName");

        Location location = new Location(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName);

        return location;
    }

    @Override
    public List<Location> getAllValues() {
        List<Location> locationList = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = LocationTable.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();

            String nodeID = (String) object.get("_id");
            locationList.add(getValue(nodeID));
            }

        return locationList;
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
