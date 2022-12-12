package ir.sheikhoo.goldengatemonitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GgsLogChartDto {
    private List<LocalDateTime> times;
    private List<LocalTime> lagAtChkpts;
    private List<Integer> lagAtChkptsSec;
    private List<LocalTime> lagSinceChkpt;
    private List<Integer> lagSinceChkptSec;
}
