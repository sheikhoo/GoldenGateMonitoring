package ir.sheikhoo.goldengatemonitoring.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class GgsLogChartDto {
    private List<LocalDateTime> times;
    private List<LocalTime> lagAtChkpts;
    private List<LocalTime> lagSinceChkpt;

    public GgsLogChartDto() {
    }

    public GgsLogChartDto(List<LocalDateTime> times, List<LocalTime> lagAtChkpts, List<LocalTime> lagSinceChkpt) {
        this.times = times;
        this.lagAtChkpts = lagAtChkpts;
        this.lagSinceChkpt = lagSinceChkpt;
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
}
