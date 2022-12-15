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

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
