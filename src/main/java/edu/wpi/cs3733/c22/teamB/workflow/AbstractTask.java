package edu.wpi.cs3733.c22.teamB.workflow;

public abstract class AbstractTask implements Runnable {
    Thread t;

    public AbstractTask(Workflow workflow) {
        workflow.addTask(this);
    }

    public abstract void start();
}
