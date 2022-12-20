package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.model.GgsUserInfoDto;
import ir.sheikhoo.goldengatemonitoring.model.User;
import ir.sheikhoo.goldengatemonitoring.model.UserDto;
import ir.sheikhoo.goldengatemonitoring.service.ConfigService;
import ir.sheikhoo.goldengatemonitoring.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/config")
public class ConfigController {
    private final ConfigService configService;
    private final UserService userService;

    public ConfigController(ConfigService configService, UserService userService) {
        this.configService = configService;
        this.userService = userService;
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

    @PostMapping("/user/add")
    public @ResponseBody Map<String, Boolean> addUser(@RequestBody UserDto userDto){
        if(!configService.isConfigOk()) {
            User existingUser = userService.findUserByEmail(userDto.getEmail());

            if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
                return Collections.singletonMap("success", false);
            }
            if(userDto.getEmail()==null || userDto.getPassword()==null){
                return Collections.singletonMap("success", false);
            }

            try {
                userService.saveUser(userDto);
                return Collections.singletonMap("success", true);
            } catch (Exception e) {
                return Collections.singletonMap("success", false);
            }
        }else {
            return Collections.singletonMap("success", false);
        }
    }
}
