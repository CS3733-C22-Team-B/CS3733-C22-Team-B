package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.List;

public class EmployeeDaoI implements IDatabase<Employee> {

    Connection conn;

    public EmployeeDaoI() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void addValue(Employee object) {

    }

    @Override
    public void deleteValue(String objectID) {

    }

    @Override
    public void updateValue(Employee object) {

    }

    @Override
    public Employee getValue(String objectID) {
        return null;
    }

    @Override
    public List<Employee> getAllValues() {
        return null;
    }

    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "EMPLOYEE", null);

            if (rset.next() && rset.getString(3).equals("EMPLOYEE")){
                // table exists
            } else {
                Statement stmt = conn.createStatement();
                stmt.execute(
                        "create table Employee( "
                                + "employeeID VARCHAR(50), "
                                + "name VARCHAR(50), "
                                + "position VARCHAR(50), "
                                + "address VARCHAR(50), "
                                + "email VARCHAR(50), "
                                + "phoneNumber VARCHAR(50)," +
                                " PRIMARY KEY (employeeID))");
            }
        } catch (SQLException e) {
            System.out.println("Create Employee Table: Failed!");
            e.printStackTrace();
        }
    }



    @Override
    public void dropTable() {

    }

    @Override
    public void restoreTable() {

    }
}
