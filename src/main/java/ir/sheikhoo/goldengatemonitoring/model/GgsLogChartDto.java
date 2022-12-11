package ir.sheikhoo.goldengatemonitoring.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class GgsLogChartDto {
    private List<LocalDateTime> times;
    private List<LocalTime> lagAtChkpts;
    private List<Integer> lagAtChkptsSec;
    private List<LocalTime> lagSinceChkpt;
    private List<Integer> lagSinceChkptSec;

    public GgsLogChartDto() {
    }

    public GgsLogChartDto(List<LocalDateTime> times, List<LocalTime> lagAtChkpts, List<Integer> lagAtChkptsSec, List<LocalTime> lagSinceChkpt, List<Integer> lagSinceChkptSec) {
        this.times = times;
        this.lagAtChkpts = lagAtChkpts;
        this.lagAtChkptsSec = lagAtChkptsSec;
        this.lagSinceChkpt = lagSinceChkpt;
        this.lagSinceChkptSec = lagSinceChkptSec;
    }

    public List<LocalDateTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalDateTime> times) {
        this.times = times;
    }

    public List<LocalTime> getLagAtChkpts() {
        return lagAtChkpts;
    }

    public void setLagAtChkpts(List<LocalTime> lagAtChkpts) {
        this.lagAtChkpts = lagAtChkpts;
    }

    public List<LocalTime> getLagSinceChkpt() {
        return lagSinceChkpt;
    }

    public void setLagSinceChkpt(List<LocalTime> lagSinceChkpt) {
        this.lagSinceChkpt = lagSinceChkpt;
    }

    public List<Integer> getLagAtChkptsSec() {
        return lagAtChkptsSec;
    }

    public void setLagAtChkptsSec(List<Integer> lagAtChkptsSec) {
        this.lagAtChkptsSec = lagAtChkptsSec;
    }

    public List<Integer> getLagSinceChkptSec() {
        return lagSinceChkptSec;
    }

    public void setLagSinceChkptSec(List<Integer> lagSinceChkptSec) {
        this.lagSinceChkptSec = lagSinceChkptSec;
    }
}
