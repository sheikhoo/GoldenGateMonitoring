package ir.sheikhoo.goldengatemonitoring.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class GgsLog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime time;
    @Column
    private String program;
    @Column
    private Boolean status;
    @Column
    private String statusTitle;
    @Column
    private String groupName;
    @Column
    private LocalTime lagAtChkpt;
    @Column
    private LocalTime lagSinceChkpt;

    public GgsLog() {
    }

    public GgsLog(Long id, LocalDateTime time, String program, Boolean status, String statusTitle, String groupName, LocalTime lagAtChkpt, LocalTime lagSinceChkpt) {
        this.id = id;
        this.time = time;
        this.program = program;
        this.status = status;
        this.statusTitle = statusTitle;
        this.groupName = groupName;
        this.lagAtChkpt = lagAtChkpt;
        this.lagSinceChkpt = lagSinceChkpt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String group) {
        this.groupName = group;
    }

    public LocalTime getLagAtChkpt() {
        return lagAtChkpt;
    }

    public void setLagAtChkpt(LocalTime lagAtChkpt) {
        this.lagAtChkpt = lagAtChkpt;
    }

    public LocalTime getLagSinceChkpt() {
        return lagSinceChkpt;
    }

    public void setLagSinceChkpt(LocalTime lagSinceChkpt) {
        this.lagSinceChkpt = lagSinceChkpt;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }
}