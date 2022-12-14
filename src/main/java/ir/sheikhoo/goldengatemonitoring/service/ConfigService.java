package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.GgsUserInfoDto;

public interface ConfigService {
    Boolean isConfigOk();
    String getGgsHome();
    GgsUserInfoDto getGgsUserInfo();
    Boolean setGgsHome(String ggsHome);
    Boolean setGgsUserInfo(GgsUserInfoDto ggsUserInfo);
}
