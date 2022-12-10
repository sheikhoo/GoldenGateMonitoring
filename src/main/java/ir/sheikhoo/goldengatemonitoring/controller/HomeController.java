package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.service.GgsLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final GgsLogService ggsLogService;

    public HomeController(GgsLogService ggsLogService) {
        this.ggsLogService = ggsLogService;
    }

    @GetMapping(value = {"/",""})
    public String listStudent(Model model) {
        model.addAttribute("currents", ggsLogService.getCurrent());
        return "index";
    }
}
