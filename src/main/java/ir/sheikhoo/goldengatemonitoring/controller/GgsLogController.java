package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.model.GgsLog;
import ir.sheikhoo.goldengatemonitoring.model.GgsLogChartDto;
import ir.sheikhoo.goldengatemonitoring.service.GgsLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GgsLogController {

    private final GgsLogService ggsLogService;

    public GgsLogController(GgsLogService ggsLogService) {
        this.ggsLogService = ggsLogService;
    }

    @GetMapping("/test")
    public String testCmd(){
        return ggsLogService.testCmd();
    }

    @GetMapping("/getAll")
    public List<GgsLog> getAll(){
        return ggsLogService.getAllLog();
    }

    @GetMapping("/getCurrent")
    public List<GgsLog> getCurrent(){
        return ggsLogService.getCurrent();
    }

    @GetMapping("/getSevenDays")
    public GgsLogChartDto getSevenDays(){
        return ggsLogService.getDataSevenDaysAgo();
    }
}
