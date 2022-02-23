package edu.wpi.cs3733.c22.teamB.workflow;

import edu.wpi.cs3733.c22.teamB.entity.objects.Location;

import java.util.*;

public class Workflow {
    public static final List<String> infusionPumpPath = List.of("HBATH00203", "bSTOR00101", "bSTOR00103");

    public void addTask(AbstractTask task) {
        task.start();
    }

    public void addAllTasks(Collection<AbstractTask> taskCollection) {
        for (AbstractTask task : taskCollection) {
            task.start();
        }
    }
}
