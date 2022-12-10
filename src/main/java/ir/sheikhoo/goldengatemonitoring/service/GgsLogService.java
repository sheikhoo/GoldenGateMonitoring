package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.GgsLog;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GgsLogService {
    public String testCmd();
    public List<GgsLog> getAllLog();
}
