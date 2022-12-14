package ir.sheikhoo.goldengatemonitoring.controller;

import ir.sheikhoo.goldengatemonitoring.model.ChartTimeEnum;
import ir.sheikhoo.goldengatemonitoring.model.GgsLog;
import ir.sheikhoo.goldengatemonitoring.model.GgsLogChartDto;
import ir.sheikhoo.goldengatemonitoring.service.GgsLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GgsLogController {

    private final GgsLogService ggsLogService;

    public GgsLogController(GgsLogService ggsLogService) {
        this.ggsLogService = ggsLogService;
    }

    @GetMapping("/getAll")
    public List<GgsLog> getAll(){
        return ggsLogService.getAllLog();
    }

    @GetMapping("/getCurrent")
    public List<GgsLog> getCurrent(){
        return ggsLogService.getCurrent();
    }

    @GetMapping("/getSevenDays/{groupName}")
    public GgsLogChartDto getSevenDays(@PathVariable("groupName") String groupName){
        return ggsLogService.getDataSevenDaysAgo(groupName);
    }

    @GetMapping("/getChartDataList/{groupName}/{type}")
    public GgsLogChartDto getChartDataList(@PathVariable("groupName") String groupName, @PathVariable("type") ChartTimeEnum type){
        return ggsLogService.getChartDataList(groupName,type);
    }

    @GetMapping("/ggsLogs")
    public String ggsLogs(){
        return ggsLogService.ggsLogs();
    }
}
