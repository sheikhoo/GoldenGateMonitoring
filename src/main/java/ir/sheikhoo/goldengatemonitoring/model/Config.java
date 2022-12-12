package ir.sheikhoo.goldengatemonitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @Id
    private Long id;
    @Column
    private String ggsHome;
    @Column
    private String ggsUser;
    @Column
    private String ggsUserPwd;
}
