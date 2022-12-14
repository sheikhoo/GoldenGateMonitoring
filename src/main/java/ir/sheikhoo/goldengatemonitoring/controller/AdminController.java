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
public class AdminController {

    private final UserService userService;
    private final ConfigService configService;

    public AdminController(UserService userService, ConfigService configService) {
        this.userService = userService;
        this.configService = configService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model){
        return "admin/index";
    }

    @GetMapping("/admin/add/user")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "admin/addUser";
    }

    @PostMapping("/admin/add/user/save")
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
            return "admin/addUser";
        }

        userService.saveUser(userDto);
        return "redirect:admin/addUser?success";
    }

    @GetMapping("/admin/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/admin/config")
    public String home(Model model) {
        return "admin/config";
    }
}
