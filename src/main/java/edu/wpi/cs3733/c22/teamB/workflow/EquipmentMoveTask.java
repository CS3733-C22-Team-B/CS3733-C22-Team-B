package edu.wpi.cs3733.c22.teamB.workflow;

import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicalEquipmentSR;
import org.apache.derby.iapi.db.Database;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class EquipmentMoveTask extends AbstractTask implements Runnable {
    private MedicalEquipmentSR sr;
    private List<Location> path;

    public EquipmentMoveTask(Workflow workflow, MedicalEquipmentSR sr, List<String> path) {
        super(workflow);
        this.sr = sr;
        DatabaseWrapper dw = new DatabaseWrapper();
        this.path = path.stream().map(s -> dw.getLocation(s)).collect(Collectors.toList());
    }

    @Override
    public void run() {
        try {
            DatabaseWrapper dw = new DatabaseWrapper();

            // first complete the SR
            System.out.println("Service " + sr.getSrID() + " starting");
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10001));

            sr.setStatus("IN PROGRESS");
            dw.updateSR(sr);

            System.out.println(
                            "Service " +
                            sr.getSrID() + " completed: \n" +
                            sr.getMedicalEquipment().getStatus() + " " +
                            sr.getMedicalEquipment().getEquipmentName() + " moved from " +
                            sr.getMedicalEquipment().getLocation().getLongName() + " to " +
                            sr.getLocation().getLongName());

            sr.getMedicalEquipment().setLocation(sr.getLocation());
            sr.setStatus("DONE");

            dw.updateMedicalEquipment(sr.getMedicalEquipment());
            dw.updateSR(sr);

            // patient using equipment
            System.out.println("Patient is using equipment...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10001));
            System.out.println("Patient finished using equipment.");

            sr.getMedicalEquipment().setStatus("DIRTY");
            dw.updateMedicalEquipment(sr.getMedicalEquipment());

            // then move by specified path
            System.out.println("Equipment " +
                    sr.getMedicalEquipment().getEquipmentName() +
                    " on the way to storage.");
            for (Location loc : path) {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10001));
                System.out.println(
                                sr.getMedicalEquipment().getStatus() + " " +
                                sr.getMedicalEquipment().getEquipmentName() + " moved from " +
                                sr.getMedicalEquipment().getLocation().getLongName() + " to " +
                                loc.getLongName());
                sr.getMedicalEquipment().setLocation(loc);
                dw.updateMedicalEquipment(sr.getMedicalEquipment());
            }
        } catch (InterruptedException e) {

        }
        System.out.println("Task completed.");
    }

    @Override
    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}
