package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoI implements IDatabase<Employee> {

    public EmployeeDaoI() {
    }

    @Override
    public void addValue(Employee employee) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO Employee(employeeID, lastname, firstname, position, accessLevel, username, password, email, phoneNumber) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, employee.getEmployeeID());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getFirstName());
            pstmt.setString(4, employee.getPosition());
            pstmt.setInt(5, employee.getAccessLevel());
            pstmt.setString(6, employee.getUsername());
            pstmt.setString(7, employee.getLastName());
            pstmt.setString(8, employee.getEmail());
            pstmt.setString(9, employee.getPhoneNumber());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into Employee Table Using Employee ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteValue(String employeeID) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM Employee WHERE employeeID = ?");
            pstmt.setString(1, employeeID);

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From Employee Table Using Equipment ID: Failed!");
            e.printStackTrace();
        }
    }


    @Override
    public void updateValue(Employee employee) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE Employee SET lastname = ?, firstname = ?, position = ?, accessLevel = ?, username = ?, password = ?, email = ?, phoneNumber = ? WHERE employeeID = ?");

            pstmt.setString(1, employee.getLastName());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setString(3, employee.getPosition());
            pstmt.setInt(4, employee.getAccessLevel());
            pstmt.setString(5, employee.getUsername());
            pstmt.setString(6, employee.getPassword());
            pstmt.setString(7, employee.getEmail());
            pstmt.setString(8, employee.getPhoneNumber());
            pstmt.setString(9, employee.getEmployeeID());


            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update Employee ID: Failed!");
            e.printStackTrace();
            return;
        }
    }


    public Employee getValue(String employeeID){
        Connection conn = ConnectionManager.getInstance().getConnection();

        Employee employee = new Employee();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Employee WHERE employeeID = ?");
            pstmt.setString(1, employeeID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            String lastName = rset.getString("lastName");
            String firstName = rset.getString("firstName");
            String position = rset.getString("position");
            int accessLevel = rset.getInt("accessLevel");
            String username = rset.getString("username");
            String password = rset.getString("password");
            String email = rset.getString("email");
            String phoneNumber = rset.getString("phoneNumber");

            employee = new Employee(employeeID, lastName, firstName, position, accessLevel, username, password, email, phoneNumber);

        } catch (SQLException e) {
            System.out.println("Get Employee Node: Failed!");
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllValues() {
        List<Employee> EmployeeList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Employee");
            ResultSet rset = pstmt.executeQuery();

            String employeeID = "";
            String lastName = "";
            String firstName = "";
            String position = "";
            int accessLevel = 0;
            String username = "";
            String password = "";
            String email = "";
            String phoneNumber = "";

            while (rset.next()) {
                employeeID = rset.getString("employeeID");
                lastName = rset.getString("lastName");
                firstName = rset.getString("firstName");
                position = rset.getString("position");
                accessLevel = rset.getInt("accessLevel");
                username = rset.getString("username");
                password = rset.getString("password");
                email = rset.getString("email");
                phoneNumber = rset.getString("phoneNumber");

                EmployeeList.add(new Employee(employeeID, lastName, firstName, position, accessLevel, username, password, email, phoneNumber));
            }
        } catch (SQLException e) {
            System.out.println("Get All Employee Nodes: SQL Failed!");
            e.printStackTrace();
        }
        return EmployeeList;
    }

    public void createTable() {
        Connection conn = ConnectionManager.getInstance().getConnection();

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
                                + "lastName VARCHAR(50), "
                                + "firstName VARCHAR(50), "
                                + "position VARCHAR(50), "
                                + "accessLevel int not null , "
                                + "username VARCHAR(50), "
                                + "password VARCHAR(50), "
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
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE Employee");
        } catch (SQLException e) {
            System.out.println("Drop Employee Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void restoreTable(List<Employee> list) {

        createTable();

        // For each iteration of location in the list of location
        for (Employee employee : list) {
            addValue(employee);
        }

    }
}
