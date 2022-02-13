package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;

public class MainSR extends AbstractSR {
    public MainSR() {
        super(null, null, null, null, null, null, null, null);
    }

    public MainSR(String srID, String srType, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes) {
        super(srID, srType, status, location, requestor, assignedEmployee, dateRequested, notes);
    }
}
