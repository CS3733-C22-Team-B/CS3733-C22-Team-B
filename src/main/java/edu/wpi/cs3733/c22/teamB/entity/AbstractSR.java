package edu.wpi.cs3733.c22.teamB.entity;

import java.util.Map;

public abstract class AbstractSR {
    public enum SRStatus {
        BLANK,
        WAITING,
        CANCELED,
        DONE
    };

    public static Map<String, SRStatus> StringToSRStatus =
            Map.ofEntries(
                    Map.entry("BLANK", SRStatus.BLANK),
                    Map.entry("WAITING", SRStatus.WAITING),
                    Map.entry("CANCELED", SRStatus.CANCELED),
                    Map.entry("DONE", SRStatus.DONE));

    public static Map<SRStatus, String> SRStatusToString =
            Map.ofEntries(
                    Map.entry(SRStatus.BLANK, "BLANK"),
                    Map.entry(SRStatus.WAITING, "WAITING"),
                    Map.entry(SRStatus.CANCELED, "CANCELED"),
                    Map.entry(SRStatus.DONE, "DONE"));

    protected String srID;
    protected SRStatus status;

    public AbstractSR(String srID, String statusStr) {
        this.srID = srID;
        if (statusStr == null) statusStr = "BLANK";
        this.status = stringToSRStatus(statusStr);
    }

    static SRStatus stringToSRStatus(String s) {
        if (StringToSRStatus.containsKey(s)) return StringToSRStatus.get(s);
        else throw new IllegalArgumentException("No SRStatus " + s);
    }

    public String getSrID() {
        return this.srID;
    }

    public SRStatus getStatus() {
        return status;
    }

    public String getStatusString() {
        return SRStatusToString.get(this.status);
    }

    public void setSrID(String srID) {
        this.srID = srID;
    }

    public void setStatus(String statusStr) {
        this.status = stringToSRStatus(statusStr);
    }
}
