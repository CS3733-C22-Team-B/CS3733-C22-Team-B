package edu.wpi.cs3733.c22.teamB.controllers.services;

import edu.wpi.cs3733.c22.teamB.controllers.IController;
import edu.wpi.cs3733.c22.teamB.entity.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.LaundrySR;
import javafx.fxml.FXML;

public class LaundrySRController implements IController {

    private LaundrySR sr = null;

    public LaundrySRController(){}

    public LaundrySRController(LaundrySR sr){
        this.sr = sr;
    }

    @FXML
    private void initialize() {
    }

    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = DatabaseWrapper.getInstance();
        if (this.sr == null)
            dw.addSR(new LaundrySR(sr));
        else
            dw.updateSR(new LaundrySR(sr));
    }

    @Override
    public void clear() {
    }
}
