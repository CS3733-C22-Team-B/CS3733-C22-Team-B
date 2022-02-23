package edu.wpi.cs3733.c22.teamB.workflow;

import edu.wpi.cs3733.c22.teamB.entity.objects.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Workflow {
    public static final List<String> infusionPumpPath = List.of("HBATH00203", "bSTOR00101", "bSTOR00103");
    private ExecutorService exec = Executors.newSingleThreadExecutor();

    public void addTask(AbstractTask task) {
        exec.submit(task);
    }

    public void addAllTasks(Collection<AbstractTask> taskCollection) {
        for (AbstractTask task : taskCollection) {
            exec.submit(task);
        }
    }
}
