package edu.wpi.teamB;

import edu.wpi.cs3733.c22.teamB.entity.MongoDB.LocationMongo;
import edu.wpi.cs3733.c22.teamB.entity.MongoDB.MongoDB;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

public class MongoTest {

    @Test
    public void testCreateLocation() throws UnknownHostException {
        MongoDB.getConnection();
        LocationMongo locationMongo = new LocationMongo();
        locationMongo.dropTable();
        locationMongo.createTable();

        Location location1 = new Location("12", 12, 12, "12", "123", "123", "21e", "q2e");
        Location location2 = new Location("123", 13, 13, "12", "123", "123", "123", "123");
        Location location3 = new Location("123", 1000, 13, "12", "123", "123", "123", "123");


        locationMongo.addValue(location1);
        locationMongo.addValue(location2);
        locationMongo.updateValue(location3);
        locationMongo.deleteValue(location1.getNodeID());
        locationMongo.getValue(location3.getNodeID());
        locationMongo.getAllValues();
    }
}
