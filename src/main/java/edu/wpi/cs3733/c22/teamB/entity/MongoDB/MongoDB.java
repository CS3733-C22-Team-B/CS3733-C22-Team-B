package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;

import java.net.UnknownHostException;

public class MongoDB {

    public static MongoClient mongoClient;
    public static DB BDBMongo;

    public static void getConnection() throws UnknownHostException {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        BDBMongo = mongoClient.getDB("BDBMongo");
    }
}
