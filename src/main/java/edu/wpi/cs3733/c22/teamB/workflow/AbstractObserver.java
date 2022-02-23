package edu.wpi.cs3733.c22.teamB.workflow;

public abstract class AbstractObserver<T> {
    AbstractSubject<T> subject;
    public abstract void update();
}
