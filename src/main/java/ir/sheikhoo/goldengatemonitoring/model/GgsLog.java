package ir.sheikhoo.goldengatemonitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}