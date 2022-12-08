package ir.sheikhoo.goldengatemonitoring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(schema = "GGS_LOG")
public class GgsLog {
    @Id
    private LocalDate time;
    @Column
    private Boolean managerSts;

    // Pump
    @Column
    private Boolean pumpSts;
    @Column
    private String pumpTitle;
    @Column
    private LocalTime pumpLagAtChkpt;
    @Column
    private LocalTime pumpLagSinceChkpt;

    // Extract
    @Column
    private Boolean extractSts;
    @Column
    private String extractTitle;
    @Column
    private LocalTime extractLagAtChkpt;
    @Column
    private LocalTime extractLagSinceChkpt;

    // Replicat
    @Column
    private Boolean replicatSts;
    @Column
    private String replicatTitle;
    @Column
    private LocalTime replicatLagAtChkpt;
    @Column
    private LocalTime replicatLagSinceChkpt;

    public GgsLog() {
    }

    public GgsLog(LocalDate time, Boolean managerSts, Boolean pumpSts, String pumpTitle, LocalTime pumpLagAtChkpt, LocalTime pumpLagSinceChkpt, Boolean extractSts, String extractTitle, LocalTime extractLagAtChkpt, LocalTime extractLagSinceChkpt, Boolean replicatSts, String replicatTitle, LocalTime replicatLagAtChkpt, LocalTime replicatLagSinceChkpt) {
        this.time = time;
        this.managerSts = managerSts;
        this.pumpSts = pumpSts;
        this.pumpTitle = pumpTitle;
        this.pumpLagAtChkpt = pumpLagAtChkpt;
        this.pumpLagSinceChkpt = pumpLagSinceChkpt;
        this.extractSts = extractSts;
        this.extractTitle = extractTitle;
        this.extractLagAtChkpt = extractLagAtChkpt;
        this.extractLagSinceChkpt = extractLagSinceChkpt;
        this.replicatSts = replicatSts;
        this.replicatTitle = replicatTitle;
        this.replicatLagAtChkpt = replicatLagAtChkpt;
        this.replicatLagSinceChkpt = replicatLagSinceChkpt;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Boolean getManagerSts() {
        return managerSts;
    }

    public void setManagerSts(Boolean managerSts) {
        this.managerSts = managerSts;
    }

    public Boolean getPumpSts() {
        return pumpSts;
    }

    public void setPumpSts(Boolean pumpSts) {
        this.pumpSts = pumpSts;
    }

    public String getPumpTitle() {
        return pumpTitle;
    }

    public void setPumpTitle(String pumpTitle) {
        this.pumpTitle = pumpTitle;
    }

    public LocalTime getPumpLagAtChkpt() {
        return pumpLagAtChkpt;
    }

    public void setPumpLagAtChkpt(LocalTime pumpLagAtChkpt) {
        this.pumpLagAtChkpt = pumpLagAtChkpt;
    }

    public LocalTime getPumpLagSinceChkpt() {
        return pumpLagSinceChkpt;
    }

    public void setPumpLagSinceChkpt(LocalTime pumpLagSinceChkpt) {
        this.pumpLagSinceChkpt = pumpLagSinceChkpt;
    }

    public Boolean getExtractSts() {
        return extractSts;
    }

    public void setExtractSts(Boolean extractSts) {
        this.extractSts = extractSts;
    }

    public String getExtractTitle() {
        return extractTitle;
    }

    public void setExtractTitle(String extractTitle) {
        this.extractTitle = extractTitle;
    }

    public LocalTime getExtractLagAtChkpt() {
        return extractLagAtChkpt;
    }

    public void setExtractLagAtChkpt(LocalTime extractLagAtChkpt) {
        this.extractLagAtChkpt = extractLagAtChkpt;
    }

    public LocalTime getExtractLagSinceChkpt() {
        return extractLagSinceChkpt;
    }

    public void setExtractLagSinceChkpt(LocalTime extractLagSinceChkpt) {
        this.extractLagSinceChkpt = extractLagSinceChkpt;
    }

    public Boolean getReplicatSts() {
        return replicatSts;
    }

    public void setReplicatSts(Boolean replicatSts) {
        this.replicatSts = replicatSts;
    }

    public String getReplicatTitle() {
        return replicatTitle;
    }

    public void setReplicatTitle(String replicatTitle) {
        this.replicatTitle = replicatTitle;
    }

    public LocalTime getReplicatLagAtChkpt() {
        return replicatLagAtChkpt;
    }

    public void setReplicatLagAtChkpt(LocalTime replicatLagAtChkpt) {
        this.replicatLagAtChkpt = replicatLagAtChkpt;
    }

    public LocalTime getReplicatLagSinceChkpt() {
        return replicatLagSinceChkpt;
    }

    public void setReplicatLagSinceChkpt(LocalTime replicatLagSinceChkpt) {
        this.replicatLagSinceChkpt = replicatLagSinceChkpt;
    }

    @Override
    public String toString() {
        return  "time=" + time +
                ", manager is " + (managerSts?"Online":"Offline") +
                ", pump is " + (pumpSts?"Online":"Offline") +
                ", pumpTitle='" + pumpTitle + '\'' +
                ", pumpLagAtChkpt=" + pumpLagAtChkpt +
                ", pumpLagSinceChkpt=" + pumpLagSinceChkpt +
                ", extract is " + (extractSts?"Online":"Offline") +
                ", extractTitle='" + extractTitle + '\'' +
                ", extractLagAtChkpt=" + extractLagAtChkpt +
                ", extractLagSinceChkpt=" + extractLagSinceChkpt +
                ", replicat is =" + (replicatSts?"Online":"Offline") +
                ", replicatTitle='" + replicatTitle + '\'' +
                ", replicatLagAtChkpt=" + replicatLagAtChkpt +
                ", replicatLagSinceChkpt=" + replicatLagSinceChkpt +
                '}';
    }
}