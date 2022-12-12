package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.ChartTimeType;
import ir.sheikhoo.goldengatemonitoring.model.GgsLog;
import ir.sheikhoo.goldengatemonitoring.model.GgsLogChartDto;

import java.util.List;


public interface GgsLogService {
    String getDataFromCmd();
    List<GgsLog> getAllLog();
    List<GgsLog> getCurrent();
    List<GgsLog> getAllSevenDaysAgo(String groupName);
    GgsLogChartDto getDataSevenDaysAgo(String groupName);
    GgsLogChartDto getChartDataList(String groupName, ChartTimeType type);
    String ggsLogs();
}
