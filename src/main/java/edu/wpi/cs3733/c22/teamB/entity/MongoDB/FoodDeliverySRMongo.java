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
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
import org.bson.Document;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodDeliverySRMongo implements IDatabase<FoodDeliverySR> {

    private MongoDatabase conn;
    private MongoCollection FoodDeliveryTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public FoodDeliverySRMongo(IDatabase<AbstractSR> mainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = mainSRMongo;
    }

    public static Document convertFoodDeliverySR(FoodDeliverySR sr) {
        Document document = new Document();
        document.put("_id", sr.getSrID());
        document.put("foodName", sr.getFoodName());
        document.put("drinkName", sr.getDrinkName());

        return document;
    }


    @Override
    public void addValue(FoodDeliverySR object) {
        conn.getCollection("FoodDeliverySR").insertOne(convertFoodDeliverySR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        FoodDeliveryTable.deleteOne(convertFoodDeliverySR(getValue(objectID)));
    }

    @Override
    public void updateValue(FoodDeliverySR object) {
        Document query = new Document("_id", object.getSrID());
        FoodDeliveryTable.findOneAndReplace(query, convertFoodDeliverySR(object));
    }

    @Override
    public FoodDeliverySR getValue(String objectID) {
        FoodDeliverySR foodDeliverySR;

        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = FoodDeliveryTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        Document foodObj = cursor.next();
        String srID = foodObj.getString("_id");
        String foodName = foodObj.getString("foodName");
        String drinkName = foodObj.getString("drinkName");

        foodDeliverySR = new FoodDeliverySR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, foodName, drinkName);

        return foodDeliverySR;
    }

    @Override
    public List<FoodDeliverySR> getAllValues() {
        List<FoodDeliverySR> foodDeliverySRList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = FoodDeliveryTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String srID = (String) object.get("_id");
            foodDeliverySRList.add(getValue(srID));
        }

        return foodDeliverySRList;
    }

    @Override
    public void createTable() {
        FoodDeliveryTable = conn.getCollection("FoodDeliverySR");
    }

    @Override
    public void dropTable() {
        conn.getCollection("FoodDeliverySR").drop();
    }

    @Override
    public void restoreTable(List<FoodDeliverySR> list) {
        createTable();
        List<Document> newList = new ArrayList<>();

        for (FoodDeliverySR foodDeliverySR : list) {
            newList.add(convertFoodDeliverySR(foodDeliverySR));
        }
        FoodDeliveryTable.insertMany(newList);

    }
}
