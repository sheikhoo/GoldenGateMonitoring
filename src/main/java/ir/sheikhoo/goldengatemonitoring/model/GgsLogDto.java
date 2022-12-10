package ir.sheikhoo.goldengatemonitoring.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GgsLogDto {
    private Long id;
    private LocalDateTime time;
    private String program;
    private Boolean status;
    private String groupName;
    private LocalTime lagAtChkpt;
    private LocalTime lagSinceChkpt;

    public GgsLogDto() {
    }

    public GgsLogDto(Long id, LocalDateTime time, String program, Boolean status, String groupName, LocalTime lagAtChkpt, LocalTime lagSinceChkpt) {
        this.id = id;
        this.time = time;
        this.program = program;
        this.status = status;
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

    public String getgroupName() {
        return groupName;
    }

    public void setgroupName(String groupName) {
        this.groupName = groupName;
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
