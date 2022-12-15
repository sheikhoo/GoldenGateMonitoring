package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.model.User;
import ir.sheikhoo.goldengatemonitoring.model.UserDto;
import ir.sheikhoo.goldengatemonitoring.service.ConfigService;
import ir.sheikhoo.goldengatemonitoring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController {
    private final UserService userService;
    private final ConfigService configService;

    public AuthController(UserService userService, ConfigService configService) {
        this.userService = userService;
        this.configService = configService;
    }

    @GetMapping("/admin/user")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "admin/user";
    }

    @PostMapping("/admin/user/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "admin/user";
        }

        userService.saveUser(userDto);
        return "redirect:/admin/user?success";
    }

    @PostMapping("/admin/user/add")
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

    @GetMapping("/admin/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
