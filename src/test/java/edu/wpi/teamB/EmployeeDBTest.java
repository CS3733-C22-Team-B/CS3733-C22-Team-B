package edu.wpi.teamB;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.cs3733.c22.teamB.entity.Employee;
import edu.wpi.cs3733.c22.teamB.entity.EmployeeDBI;
import org.junit.jupiter.api.Test;

public class EmployeeDBTest {

    EmployeeDBI employeeDBI = new EmployeeDBI();
    Employee employee1 = new Employee("test1", "n", "n", "n", "n", "n");
    Employee employee2 = new Employee("test2", "a", "b", "c", "d", "a");
    Employee employee3 = new Employee("test2", "d", "d", "e", "q", "q");
    Employee employee4 = new Employee("test4", "ha", "b", "c", "d", "a");

    @Test
    public void testGetNode() {
        employeeDBI.insertNode(employee1);
        assertEquals(employee1.toString(), employeeDBI.getNode("test1").toString());
        employeeDBI.deleteNode(employee1.getEmployeeID());
    }

    @Test
    public void testInsertNode() {
        employeeDBI.insertNode(employee4);
        assertEquals("test4", employee4.getEmployeeID());
        employeeDBI.deleteNode("test4");
    }

    @Test
    public void testDeleteNode() {
        employeeDBI.insertNode(employee4);
        employeeDBI.deleteNode("test4");
        assertFalse(employeeDBI.getAllNodes().contains(employee4));
    }

    @Test
    public void testUpdateNode() {
        employeeDBI.insertNode(employee2);
        employeeDBI.updateNode(employee3);
        assertEquals(employee3.toString(), employeeDBI.getNode("test2").toString());
        employeeDBI.deleteNode("test2");
    }
}
