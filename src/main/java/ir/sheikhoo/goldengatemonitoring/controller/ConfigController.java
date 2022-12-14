package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.model.GgsUserInfoDto;
import ir.sheikhoo.goldengatemonitoring.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/config")
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping("/set/ggsHome/{home}")
    private boolean setGgsHome(@PathVariable("home") String home){
        return configService.setGgsHome(home);
    }

    @PostMapping("/set/ggsUserInfo")
    private boolean setGgsUserInfo(@RequestBody GgsUserInfoDto ggsUserInfo){
        return configService.setGgsUserInfo(ggsUserInfo);
    }
}
