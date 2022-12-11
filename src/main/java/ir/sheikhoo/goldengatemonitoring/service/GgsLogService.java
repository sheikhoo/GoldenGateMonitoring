package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.GgsLog;
import ir.sheikhoo.goldengatemonitoring.model.GgsLogChartDto;

import java.util.List;


public interface GgsLogService {
    public String testCmd();
    public List<GgsLog> getAllLog();
    public List<GgsLog> getCurrent();
    public List<GgsLog> getAllSevenDaysAgo(String groupName);
    public GgsLogChartDto getDataSevenDaysAgo(String groupName);
    public String ggsLogs();
}
