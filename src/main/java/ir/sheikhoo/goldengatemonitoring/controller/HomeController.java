package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.service.ConfigService;
import ir.sheikhoo.goldengatemonitoring.service.GgsLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final GgsLogService ggsLogService;
    private final ConfigService configService;

    public HomeController(GgsLogService ggsLogService, ConfigService configService) {
        this.ggsLogService = ggsLogService;
        this.configService = configService;
    }

    @GetMapping(value = {"/","","/index"})
    public String home(Model model) {
        if(configService.isConfigOk()) {
            try {
                model.addAttribute("currents", ggsLogService.getCurrent());
            } catch (Exception e) {
                model.addAttribute("currents", null);
            }
            return "index";

        }else {
            return "config/index";
        }
    }
}
