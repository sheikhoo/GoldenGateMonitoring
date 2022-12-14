package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.model.GgsUserInfoDto;
import ir.sheikhoo.goldengatemonitoring.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/config")
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping("/set/ggsHome")
    private Map<String, Boolean> setGgsHome(@RequestParam("ggsHome") String home){
        System.out.println(home);
        return Collections.singletonMap("success", configService.setGgsHome(home));
    }

    @PostMapping("/set/ggsUserInfo")
    private Map<String, Boolean> setGgsUserInfo(@RequestBody GgsUserInfoDto ggsUserInfo){
        return Collections.singletonMap("success", configService.setGgsUserInfo(ggsUserInfo));
    }

    @GetMapping("/get/ggsHome")
    private Map<String, String> getGgsHome(){
        return Collections.singletonMap("ggsHome", configService.getGgsHome());
    }
    @GetMapping("/get/ggsUser")
    private Map<String, String> getGgsUser(){
        return Collections.singletonMap("ggsUser", configService.getGgsUser());
    }
}
