package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodDeliverySRMongo implements IDatabase<FoodDeliverySR> {

    private DB conn;
    private DBCollection FoodDeliveryTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public FoodDeliverySRMongo(IDatabase<AbstractSR> mainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = mainSRMongo;
    }

    public static DBObject convertFoodDeliverySR(FoodDeliverySR sr) {
        BasicDBObject document = new BasicDBObject();
        document.put("_id", sr.getSrID());
        document.put("foodName", sr.getFoodName());
        document.put("drinkName", sr.getDrinkName());

        return document;
    }


    @Override
    public void addValue(FoodDeliverySR object) {
        conn.getCollection("FoodDeliverySR").insert(convertFoodDeliverySR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        FoodDeliveryTable.remove(convertFoodDeliverySR(getValue(objectID)));
    }

    @Override
    public void updateValue(FoodDeliverySR object) {
        DBObject query = new BasicDBObject("_id", object.getSrID());
        FoodDeliveryTable.findAndModify(query, convertFoodDeliverySR(object));
    }

    @Override
    public FoodDeliverySR getValue(String objectID) {
        FoodDeliverySR foodDeliverySR;

        DBObject query = new BasicDBObject("_id", objectID);
        DBCursor cursor = FoodDeliveryTable.find(query);

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        BasicDBObject foodObj = (BasicDBObject) cursor.one();
        String srID = foodObj.getString("_id");
        String foodName = foodObj.getString("foodName");
        String drinkName = foodObj.getString("drinkName");

        foodDeliverySR = new FoodDeliverySR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, foodName, drinkName);

        return foodDeliverySR;
    }

    @Override
    public List<FoodDeliverySR> getAllValues() {
        List<FoodDeliverySR> foodDeliverySRList = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = FoodDeliveryTable.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();

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
        for (FoodDeliverySR foodDeliverySR : list) {
            addValue(foodDeliverySR);
        }

    }
}
