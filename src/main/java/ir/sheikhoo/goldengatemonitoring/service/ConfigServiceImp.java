package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.Config;
import ir.sheikhoo.goldengatemonitoring.repository.ConfigRepository;
import org.springframework.stereotype.Service;

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
        Config config=configRepository.findById(1L).get();
        if(config.getGgsHome() != null && !config.getGgsHome().isBlank() && !config.getGgsHome().isEmpty()){
            if(runCommand.isWindows()){
                return true;
            }else return !config.getGgsUser().isEmpty() && !config.getGgsUser().isBlank()
                    && !config.getGgsUserPwd().isBlank() && !config.getGgsUserPwd().isEmpty();
        }
        return false;
    }

    @Override
    public String getGgsHome() {
        return configRepository.findById(1L).get().getGgsHome();
    }

    @Override
    public String getGgsUser() {
        return configRepository.findById(1L).get().getGgsUser();
    }

    @Override
    public String getGgsUserPwd() {
        return configRepository.findById(1L).get().getGgsUserPwd();
    }
}
