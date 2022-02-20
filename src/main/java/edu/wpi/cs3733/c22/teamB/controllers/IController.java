package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;

public interface IController {

    void submit();
    void submit(AbstractSR sr);

    void clear();
}
