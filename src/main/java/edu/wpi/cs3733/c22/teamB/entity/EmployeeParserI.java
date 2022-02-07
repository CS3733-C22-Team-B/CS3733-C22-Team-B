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
        employee.setName(data[1]);
        employee.setPosition(data[2]);
        employee.setAddress(data[3]);
        employee.setEmail(data[4]);
        employee.setPhoneNumber(data[5]);

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
    public List<String> fromObjectsToStrings(List<Employee> listEmployees) {
        List<String> listString = new ArrayList<>();
        for (Employee employee : listEmployees) {
            listString.add(employee.toStringFields());
        }

        return listString;
    }
}
