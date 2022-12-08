package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.service.GgsLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GgsLogController {

    private final GgsLogService ggsLogService;

    public GgsLogController(GgsLogService ggsLogService) {
        this.ggsLogService = ggsLogService;
    }

    @GetMapping(value = {"/",""})
    public String testCmd(){
        return ggsLogService.testCmd();
    }
}
