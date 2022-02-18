package edu.wpi.cs3733.c22.teamB.entity.objects.services;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;

import java.time.LocalDate;

public class LaundrySR extends AbstractSR {

    public LaundrySR() {
        super(null, "LaundrySR", null, null, null, null, null, null);
    }

    public LaundrySR(String srID, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes) {
        super(srID, "LaundrySR", status, location, requestor, assignedEmployee, dateRequested, notes);
    }

    public LaundrySR(AbstractSR csr){
        super(csr);
        this.setSrType("LaundrySR");
    }

    @Override
    public String toString() {
        return "LaundrySR {" +
                "srID= " + getSrID() + '\'' +
                "srType= " + getSrType() + '\'' +
                "status= " + getStatus() + '\'' +
                "location= " + getLocation() + '\'' +
                "requestor= " + getRequestor() + '\'' +
                "assignedEmployee= " + getAssignedEmployee() + '\'' +
                "dateRequested= " + getDateRequested() + '\'' +
                "notes= " + getNotes() + '\'' + '}';
    }

    public String toStringFields() {
        return getSrID();
    }

    public static String toStringHeader() {
        return "srID";
    }
}
