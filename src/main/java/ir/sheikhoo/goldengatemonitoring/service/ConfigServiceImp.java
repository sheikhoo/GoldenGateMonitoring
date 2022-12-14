package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.Config;
import ir.sheikhoo.goldengatemonitoring.model.GgsUserInfoDto;
import ir.sheikhoo.goldengatemonitoring.repository.ConfigRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ConfigServiceImp implements ConfigService {
    private final ConfigRepository configRepository;
    private final RunCommand runCommand;

    public ConfigServiceImp(ConfigRepository configRepository, RunCommand runCommand) {
        this.configRepository = configRepository;
        this.runCommand = runCommand;
    }

    @Override
    public Boolean isConfigOk() {
        if(true){
            return false;
        }
        if(configRepository.existsById(1L)) {
            Config config = configRepository.getReferenceById(1L);
            if (config.getGgsHome() != null && !config.getGgsHome().isBlank() && !config.getGgsHome().isEmpty()) {
                if (runCommand.isWindows()) {
                    return true;
                } else return !config.getGgsUser().isEmpty() && !config.getGgsUser().isBlank()
                        && !config.getGgsUserPwd().isBlank() && !config.getGgsUserPwd().isEmpty();
            }
        }
        return false;
    }

    @Override
    public String getGgsHome() {
        Config config=configRepository.findById(1L).orElse(null);
        return config!=null?config.getGgsHome():"";
    }

    @Override
    public String getGgsUser() {
        Config config=configRepository.findById(1L).orElse(null);
        return config!=null?config.getGgsUser():"";
    }

    @Override
    public GgsUserInfoDto getGgsUserInfo() {
        Config config=configRepository.findById(1L).orElse(null);
        return config!=null?new GgsUserInfoDto(config.getGgsUser(),config.getGgsUserPwd()):null;
    }

    @Override
    public Boolean setGgsHome(String ggsHome) {
        try {
            Config config;
            if (configRepository.existsById(1L)){
                config=configRepository.getReferenceById(1L);
            }else {
                config=new Config();
                config.setId(1L);
            }
            config.setGgsHome(ggsHome);
            configRepository.save(config);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean setGgsUserInfo(GgsUserInfoDto ggsUserInfo) {
        try {
            Config config;
            if (configRepository.existsById(1L)){
                config=configRepository.getReferenceById(1L);
            }else {
                config=new Config();
                config.setId(1L);
            }
            config.setGgsUser(ggsUserInfo.getGgsUser());
            config.setGgsUserPwd(ggsUserInfo.getGgsUserPwd());
            configRepository.save(config);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
