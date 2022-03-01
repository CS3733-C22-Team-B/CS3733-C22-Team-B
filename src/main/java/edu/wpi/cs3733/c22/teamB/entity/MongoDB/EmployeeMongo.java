package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import edu.wpi.cs3733.c22.teamB.entity.PasswordHashing;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import org.bson.Document;

import javax.print.Doc;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMongo implements IDatabase<Employee> {

    private MongoDatabase conn;
    private MongoCollection EmployeeTable;

    public EmployeeMongo(){
        conn = MongoDB.getBDBMongo();
    }

    public static Document convertEmployee(Employee employee){
        String pass = employee.getPassword();

        // if pass is already hashed, do nothing; else hash it
        if (pass.length() > 20) {
        } else {
            pass = PasswordHashing.hashPassword(employee.getPassword());
        }
        Document document = new Document();
        document.put("_id", employee.getEmployeeID());
        document.put("lastName", employee.getLastName());
        document.put("firstName", employee.getFirstName());
        document.put("position", employee.getPosition());
        document.put("accessLevel", employee.getAccessLevel());
        document.put("username", employee.getUsername());
        document.put("password", pass);
        document.put("email", employee.getEmail());
        document.put("phoneNumber", employee.getPhoneNumber());

        return document;
    }

    @Override
    public void addValue(Employee object) {
        conn.getCollection("Employee").insertOne(convertEmployee(object));
    }

    @Override
    public void deleteValue(String objectID) {
        EmployeeTable.deleteOne(convertEmployee(getValue(objectID)));
    }

    @Override
    public void updateValue(Employee object) {
        Document query = new Document("_id", object.getEmployeeID());
        EmployeeTable.findOneAndReplace(query, convertEmployee(object));
    }

    @Override
    public Employee getValue(String objectID) {
        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = EmployeeTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

//        String employeeObj = cursor.next().toJson();

        Document employeeObj = cursor.next();


        String employeeID = employeeObj.getString("_id");
        String lastName = employeeObj.getString("lastName");
        String firstName = employeeObj.getString("firstName");
        String position = employeeObj.getString("position");
        int accessLevel = employeeObj.getInteger("accessLevel");
        String username = employeeObj.getString("username");
        String password = employeeObj.getString("password");
        String email = employeeObj.getString("email");
        String phoneNumber = employeeObj.getString("phoneNumber");

        Employee employee = new Employee(employeeID, lastName, firstName, position, accessLevel, username, password, email, phoneNumber);
        System.out.print(employeeObj);
        return employee;
//        System.out.println(employeeObj);
//        return null;
    }

    @Override
    public List<Employee> getAllValues() {
        List<Employee> employeeList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = EmployeeTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();


        while (cursor.hasNext()) {
            Document object = cursor.next();

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
        List<Document> newList = new ArrayList<>();

        for(Employee employee : list) {
            newList.add(convertEmployee(employee));
        }
        EmployeeTable.insertMany(newList);
    }
}
