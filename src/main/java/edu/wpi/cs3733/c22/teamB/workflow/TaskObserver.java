package edu.wpi.cs3733.c22.teamB.workflow;

public class TaskObserver extends AbstractObserver<Boolean> {
    private boolean prevTaskDone = false;

    public TaskObserver(TaskSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        if (this.subject.getState()) {
            prevTaskDone = true;
        }
    }

    public boolean isPrevTaskDone() {
        return prevTaskDone;
    }
}
