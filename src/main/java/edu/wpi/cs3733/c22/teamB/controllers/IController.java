package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.entity.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.Location;

public interface IController {

    void submit();
    void submit(AbstractSR sr);

    void clear();
}
