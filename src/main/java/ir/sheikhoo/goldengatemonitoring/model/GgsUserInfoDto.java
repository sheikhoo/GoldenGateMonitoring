package ir.sheikhoo.goldengatemonitoring.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GgsUserInfoDto {
    private String ggsUser;
    private String ggsUserPwd;
}
