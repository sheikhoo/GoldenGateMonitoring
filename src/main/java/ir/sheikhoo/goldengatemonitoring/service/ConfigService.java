package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.Config;
import ir.sheikhoo.goldengatemonitoring.model.GgsUserInfoDto;

public interface ConfigService {
    Boolean isConfigOk();
    String getGgsHome();
    String getGgsUser();
    String getGgsUserPwd();
    Boolean setGgsHome(String ggsHome);
    Boolean setGgsUserInfo(GgsUserInfoDto ggsUserInfo);
}
