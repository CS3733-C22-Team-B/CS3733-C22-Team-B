package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDBI extends AbstractDatabaseI<Employee> {

    Connection conn;

    public EmployeeDBI() {
        this.conn = DBConnection.getConnection();
    }
    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "Employee", null);

            if (rset.next() && rset.getString(3).equals("Employee")){
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
    public void restore(List<Employee> list) {
//
//        try {
//            Statement stmt = conn.createStatement();
//            stmt.execute("drop table Employee");
//        } catch (SQLException e) {
//            System.out.println("Drop Employee Table: Failed!");
//        }

        try {
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

            // For each iteration of location in the list of location
            for (Employee employee : list) {

                // Get all the parameter information
                String employeeID = employee.getEmployeeID();
                String name = employee.getName();
                String position = employee.getPosition();
                String address = employee.getAddress();
                String email = employee.getEmail();
                String phoneNumber = employee.getPhoneNumber();

                PreparedStatement pstmt =
                        conn.prepareStatement(
                                "INSERT INTO Employee (employeeID, name, position, address, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, employeeID);
                pstmt.setString(2, name);
                pstmt.setString(3, position);
                pstmt.setString(4, address);
                pstmt.setString(5, email);
                pstmt.setString(6, phoneNumber);

                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException e) {
            System.out.println("Restore Employee Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllNodes() {

        List<Employee> EmployeeList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Employee");
            ResultSet rset = pstmt.executeQuery();

            String employeeID = "";
            String name = "";
            String position = "";
            String address = "";
            String email = "";
            String phoneNumber = "";

            while (rset.next()) {
                employeeID = rset.getString("employeeID");
                name = rset.getString("name");
                position = rset.getString("position");
                address = rset.getString("address");
                email = rset.getString("email");
                phoneNumber = rset.getString("phoneNumber");

                EmployeeList.add(new Employee(employeeID, name, position, address, email, phoneNumber));
            }
        } catch (SQLException e) {
            System.out.println("Get all nodes: SQL Failed!");
            e.printStackTrace();
        }
        return EmployeeList;
    }

    @Override
    public Employee getNode(String employeeID) {
        Employee employee = new Employee();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Employee WHERE employeeID = ?");
            pstmt.setString(1, employeeID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            String name = rset.getString("name");
            String position = rset.getString("position");
            String address = rset.getString("address");
            String email = rset.getString("email");
            String phoneNumber = rset.getString("phoneNumber");

            employee = new Employee(employeeID, name, position, address, email, phoneNumber);

        } catch (SQLException e) {
            System.out.println("Get Employee Node: Failed!");
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void deleteNode(String nodeID) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM Employee WHERE employeeID = ?");
            pstmt.setString(1, nodeID);

                pstmt.executeUpdate();


            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From Medical Equipment Table Using Equipment ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateNode(Employee node) {

        // employeeID, name, position, address, email, phoneNumber

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE Employee SET name = ?, position = ?, address = ?, email = ?, phoneNumber = ? WHERE employeeID = ?");

            pstmt.setString(1, node.getName());
            pstmt.setString(2, node.getPosition());
            pstmt.setString(3, node.getAddress());
            pstmt.setString(4, node.getEmail());
            pstmt.setString(5, node.getPhoneNumber());
            pstmt.setString(6, node.getEmployeeID());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update Node ID: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void insertNode(Employee node) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO Employee(employeeID, name, position, address, email, phoneNumber) VALUES( ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, node.getEmployeeID());
            pstmt.setString(2, node.getName());
            pstmt.setString(3, node.getPosition());
            pstmt.setString(4, node.getAddress());
            pstmt.setString(5, node.getEmail());
            pstmt.setString(6, node.getPhoneNumber());

            if (!isInTable(node.getEmployeeID())) {
                pstmt.executeUpdate();
            }
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into Table Using Node ID: Failed!");
            e.printStackTrace();
        }
    }

    public boolean isInTable(String nodeID) {    //check if there is a node with given ID in table
        boolean ans = false;
        try {
            //search for NodeID
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Location WHERE nodeID = ?");
            pstmt.setString(1, nodeID);
            ResultSet rs = pstmt.executeQuery();
            ans = rs.next();    //if any ids are found
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Search for NodeID Failed!");
            e.printStackTrace();
        }
        return ans;
    }
}
