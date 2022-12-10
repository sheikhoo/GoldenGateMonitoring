package ir.sheikhoo.goldengatemonitoring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class GgsLog {
    @Id
    private Long id;
    @Column
    private LocalDate time;
    @Column
    private Boolean program;
    @Column
    private Boolean status;
    @Column
    private String group;
    @Column
    private LocalTime lagAtChkpt;
    @Column
    private LocalTime lagSinceChkpt;

    public GgsLog() {
    }

    public GgsLog(Long id, LocalDate time, Boolean program, Boolean status, String group, LocalTime lagAtChkpt, LocalTime lagSinceChkpt) {
        this.id = id;
        this.time = time;
        this.program = program;
        this.status = status;
        this.group = group;
        this.lagAtChkpt = lagAtChkpt;
        this.lagSinceChkpt = lagSinceChkpt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Boolean getProgram() {
        return program;
    }

    public void setProgram(Boolean program) {
        this.program = program;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
}