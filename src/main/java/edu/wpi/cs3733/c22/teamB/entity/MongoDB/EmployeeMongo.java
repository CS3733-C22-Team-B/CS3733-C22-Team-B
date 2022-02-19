package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.PasswordHashing;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMongo implements IMongo<Employee> {

    private DB conn;
    private DBCollection EmployeeTable;

    public EmployeeMongo() throws UnknownHostException {
        conn = MongoDB.getBDBMongo();
    }

    public static DBObject convertEmployee(Employee employee){
        BasicDBObject document = new BasicDBObject();
        document.put("_id", employee.getEmployeeID());
        document.put("lastName", employee.getLastName());
        document.put("firstName", employee.getFirstName());
        document.put("position", employee.getPosition());
        document.put("accessLevel", employee.getAccessLevel());
        document.put("username", employee.getUsername());
        document.put("password", /*PasswordHashing.hashPassword*/(employee.getPassword()));
        document.put("email", employee.getEmail());
        document.put("phoneNumber", employee.getPhoneNumber());

        return document;
    }

    @Override
    public void addValue(Employee object) {
        conn.getCollection("Employee").insert(convertEmployee(object));
    }

    @Override
    public void deleteValue(String objectID) {
        EmployeeTable.remove(convertEmployee(getValue(objectID)));
    }

    @Override
    public void updateValue(Employee object) {
        DBObject query = new BasicDBObject("_id", object.getEmployeeID());
        EmployeeTable.findAndModify(query, convertEmployee(object));
    }

    @Override
    public Employee getValue(String objectID) {
        DBObject query = new BasicDBObject("_id", objectID);
        DBCursor cursor = EmployeeTable.find(query);

        BasicDBObject employeeObj = (BasicDBObject) cursor.one();
        String employeeID = employeeObj.getString("_id");
        String lastName = employeeObj.getString("lastName");
        String firstName = employeeObj.getString("firstName");
        String position = employeeObj.getString("position");
        int accessLevel = Integer.parseInt(employeeObj.getString("accessLevel"));
        String username = employeeObj.getString("username");
        String password = employeeObj.getString("password");
        String email = employeeObj.getString("email");
        String phoneNumber = employeeObj.getString("phoneNumber");

        Employee employee = new Employee(employeeID, lastName, firstName, position, accessLevel, username, password, email, phoneNumber);
//        System.out.print(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllValues() {
        List<Employee> employeeList = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = EmployeeTable.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();

            String nodeID = (String) object.get("_id");
            employeeList.add(getValue(nodeID));
        }

        return employeeList;
    }

    @Override
    public void createTable() {

        EmployeeTable = conn.getCollection("Employee");

    }

    @Override
    public void dropTable() {
        conn.getCollection("Employee").drop();
    }

    @Override
    public void restoreTable(List<Employee> list) {
        createTable();

        for(Employee employee : list) {
            addValue(employee);
        }
    }
}
