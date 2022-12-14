package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.ChartTimeEnum;
import ir.sheikhoo.goldengatemonitoring.model.GgsLog;
import ir.sheikhoo.goldengatemonitoring.model.GgsLogChartDto;

import java.util.List;


public interface GgsLogService {
    List<GgsLog> getAllLog();
    List<GgsLog> getCurrent();
    List<GgsLog> getAllSevenDaysAgo(String groupName);
    GgsLogChartDto getDataSevenDaysAgo(String groupName);
    GgsLogChartDto getChartDataList(String groupName, ChartTimeEnum type);
    String ggsLogs();
}
