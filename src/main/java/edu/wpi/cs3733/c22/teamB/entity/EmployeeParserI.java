package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeParserI implements IParser<Employee> {

    @Override
    public Employee fromStringToObject(String string) {
        Employee employee = new Employee();

        String[] data = string.split(",");

        employee.setEmployeeID(data[0]);
        employee.setLastName(data[1]);
        employee.setFistName(data[2]);
        employee.setPosition(data[3]);
        employee.setAccessLevel(Integer.parseInt(data[4]));
        employee.setUsername(data[5]);
        employee.setPassword(data[6]);
        employee.setEmail(data[7]);
        employee.setPhoneNumber(data[8]);

        return employee;
    }

    @Override
    public List<Employee> fromStringsToObjects(List<String> listString) {
        List<Employee> employeeList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return employeeList;
    }

    @Override
    public String fromObjectToString(Employee employee) {
        String str = employee.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<Employee> listT) {
        List<String> listString = new ArrayList<>();
        listString.add(Employee.toStringHeader());
        for (Employee employee : listT) {
            listString.add(employee.toStringFields());
        }

        return listString;
    }
}
