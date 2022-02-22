package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;

import java.net.UnknownHostException;
import java.util.List;

public class MongoDB {

    public static MongoClient mongoClient;
    public static DB BDBMongo;
    public static DBCollection LocationMongo;

    public static void getConnection()  {

        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
//        BDBMongo = mongoClient.getDB("BDBMongo");

    }

    public static DBObject convert(Location location){
        return new BasicDBObject("nodeID", location.getNodeID()).append("xcoord", location.getXcoord()).append("ycoord", location.getYcoord()).append("floor", location.getFloor()).append("building", location.getBuilding()).append("nodeType", location.getNodeType()).append("longName", location.getLongName()).append("shortName", location.getShortName());
    }
    public static DB getBDBMongo() {
        BDBMongo = mongoClient.getDB("BDBMongo");
        return BDBMongo;
    }
}
