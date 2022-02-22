package edu.wpi.teamB;

import edu.wpi.cs3733.c22.teamB.entity.MongoDB.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.GiftFloralSR;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;
import java.time.LocalDate;

public class MongoTest2 {

    public MongoTest2() {}

    @Test public void testMongo() throws UnknownHostException {

        //Location

        MongoDB.getConnection();
        LocationMongo locationMongo = new LocationMongo();
        locationMongo.dropTable();
        locationMongo.createTable();

        Location location1 = new Location("12", 12, 12, "12", "123", "123", "21e", "q2e");
        Location location2 = new Location("123", 13, 13, "12", "123", "123", "123", "123");

        locationMongo.addValue(location1);
        locationMongo.addValue(location2);

        //Employee

        EmployeeMongo employeeMongo = new EmployeeMongo();
        employeeMongo.dropTable();
        employeeMongo.createTable();

        Employee employee1 = new Employee("123", "123", "123", "123", 12, "123", "123", "213", "123");
        Employee employee2 = new Employee("223", "123", "123", "123", 12, "123", "123", "213", "123");
        Employee employee3 = new Employee("323", "123", "Ben", "123", 12, "123", "123", "213", "123");

        employeeMongo.addValue(employee1);
        employeeMongo.addValue(employee2);
        employeeMongo.addValue(employee3);

        IDatabase<AbstractSR> mainSRMongo = new MainSRMongo(locationMongo, employeeMongo);
        mainSRMongo.dropTable();
        mainSRMongo.createTable();
        LocalDate date = LocalDate.parse("2022-12-12");

        //Food

        FoodDeliverySRMongo foodDeliverySRMongo = new FoodDeliverySRMongo(mainSRMongo);
        foodDeliverySRMongo.dropTable();
        foodDeliverySRMongo.createTable();

        FoodDeliverySR foodDeliverySR = new FoodDeliverySR("Food1", "123", location1, employee1, employee2, date, "132", "123", "123");
        FoodDeliverySR foodDeliverySR2 = new FoodDeliverySR("Food2", "122", location1, employee1, employee2, date, "132", "123", "123" );
        FoodDeliverySR foodDeliverySR3 = new FoodDeliverySR("Food3", "123", location1, employee1, employee2, date, "132", "123", "123" );
        FoodDeliverySR foodDeliverySR32 = new FoodDeliverySR("Food3", "123", location1, employee1, employee2, date, "132", "123", "123" );

        mainSRMongo.addValue(foodDeliverySR);
        mainSRMongo.addValue(foodDeliverySR2);
        mainSRMongo.addValue(foodDeliverySR3);

        foodDeliverySRMongo.addValue(foodDeliverySR);
        foodDeliverySRMongo.addValue(foodDeliverySR2);
        foodDeliverySRMongo.addValue(foodDeliverySR3);
        foodDeliverySRMongo.updateValue(foodDeliverySR32);
        foodDeliverySRMongo.deleteValue(foodDeliverySR32.getSrID());
        foodDeliverySRMongo.getValue(foodDeliverySR.getSrID());
        foodDeliverySRMongo.getAllValues();

        //Gift

        GiftFloralSRMongo giftFloralSRMongo = new GiftFloralSRMongo(mainSRMongo);
        giftFloralSRMongo.dropTable();
        giftFloralSRMongo.createTable();

        GiftFloralSR giftFloralSR = new GiftFloralSR("Gift1", "123", location1, employee1, employee2, date, "13", "123");
        GiftFloralSR giftFloralSR2 = new GiftFloralSR("Gift2", "123", location1, employee1, employee2, date, "13", "123");
        GiftFloralSR giftFloralSR3 = new GiftFloralSR("Gift3", "1232", location1, employee1, employee2, date, "13", "123");
        GiftFloralSR giftFloralSR32 = new GiftFloralSR("Gift3", "3", location1, employee1, employee2, date, "14333334343434343434343433", "123");

        mainSRMongo.addValue(giftFloralSR);
        mainSRMongo.addValue(giftFloralSR2);
        mainSRMongo.addValue(giftFloralSR3);

        giftFloralSRMongo.addValue(giftFloralSR);
        giftFloralSRMongo.addValue(giftFloralSR2);
        giftFloralSRMongo.addValue(giftFloralSR3);
        giftFloralSRMongo.updateValue(giftFloralSR32);
        giftFloralSRMongo.deleteValue(giftFloralSR3.getSrID());
        giftFloralSRMongo.getValue(giftFloralSR.getSrID());
        giftFloralSRMongo.getAllValues();
    }
}
