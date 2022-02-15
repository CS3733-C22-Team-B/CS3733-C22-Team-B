package edu.wpi.cs3733.c22.teamB.entity;

import edu.wpi.cs3733.c22.teamB.entity.*;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodDeliverySRDaoI implements IDatabase<FoodDeliverySR> {

    public FoodDeliverySRDaoI() {
    }

    @Override
    public void addValue(FoodDeliverySR object) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO FoodDeliverySR (srID, foodName, drinkName) VALUES(?, ?, ?)");
            pstmt.setString(1, object.getSrID());
            pstmt.setString(2, object.getFoodName());
            pstmt.setString(3, object.getDrinkName());

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into FoodDeliverySR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteValue(String objectID) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM FoodDeliverySR WHERE srID = ?");
            pstmt.setString(1, objectID);
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Delete From FoodDeliverySR Table Using SR ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateValue(FoodDeliverySR object) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE FoodDeliverySR SET foodName = ?, drinkName = ? WHERE srID = ?");
            pstmt.setString(1, object.getFoodName());
            pstmt.setString(2, object.getDrinkName());
            pstmt.setString(3, object.getSrID());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Update FoodDeliverySR Node: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public FoodDeliverySR getValue(String objectID) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        FoodDeliverySR foodDeliverySR = new FoodDeliverySR();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM FoodDeliverySR WHERE srID = ?");
            pstmt.setString(1, objectID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            String foodName = rset.getString("foodName");
            String drinkName = rset.getString("drinkName");

            MainSRDaoI mainSRDaoI = new MainSRDaoI();
            AbstractSR mainSR = mainSRDaoI.getValue(objectID);

            String status = mainSR.getStatus();
            Location location = mainSR.getLocation();
            Employee requestor = mainSR.getRequestor();
            Employee assignedEmployee = mainSR.getAssignedEmployee();
            LocalDate dateRequested = mainSR.getDateRequested();
            String notes = mainSR.getNotes();
            foodDeliverySR = new FoodDeliverySR(objectID, status, location, requestor, assignedEmployee, dateRequested, notes, foodName, drinkName);
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Get FoodDeliverySR Node Failed");
            e.printStackTrace();
        }
        return foodDeliverySR;
    }

    @Override
    public List<FoodDeliverySR> getAllValues() {
        List<FoodDeliverySR> foodDeliverySRList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();

        try{
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT SRID FROM FoodDeliverySR ");
            ResultSet rset = pstmt.executeQuery();


            while(rset.next()){
                foodDeliverySRList.add(getValue(rset.getString("SRID")));
            }
        } catch (SQLException e) {
            System.out.println("Get foodDeliverySR Node Failed");
            e.printStackTrace();
        }
        return foodDeliverySRList;
    }


    @Override
    public void createTable() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "FOODDELIVERYSR", null);
            if (rset.next() && rset.getString(3).equals("FOODDELIVERYSR")){
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE FOODDELIVERYSR ( "
                        + "srID VARCHAR(50), "
                        + "foodName VARCHAR(50), "
                        + "drinkName VARCHAR(50), "
                        + "PRIMARY KEY (srID),"
                        + "CONSTRAINT FK_FOODDELIVERYSR_MainSR FOREIGN KEY (srID) REFERENCES MainSR (srID) )"); //ON DELETE SET NULL
            }
        } catch (SQLException e) {
            System.out.println("Create Location Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE FOODDELIVERYSR");
        } catch (SQLException e) {
            System.out.println("Drop FOODDELIVERYSR Table: Failed!");
        }
    }

    @Override
    public void restoreTable(List<FoodDeliverySR> list) {
        createTable();
        for (FoodDeliverySR foodDeliverySR : list) {
            addValue(foodDeliverySR);
        }
    }
}
