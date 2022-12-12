package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.Config;

public interface ConfigService {
    Boolean isConfigOk();
    String getGgsHome();
    String getGgsUser();
    String getGgsUserPwd();
}
