package ir.sheikhoo.goldengatemonitoring.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class GgsLogDto {
    private Long id;
    private LocalDate time;
    private Boolean program;
    private Boolean status;
    private String group;
    private LocalTime lagAtChkpt;
    private LocalTime lagSinceChkpt;

    public GgsLogDto() {
    }

    public GgsLogDto(Long id, LocalDate time, Boolean program, Boolean status, String group, LocalTime lagAtChkpt, LocalTime lagSinceChkpt) {
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
