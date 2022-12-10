package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.Setting;
import ir.sheikhoo.goldengatemonitoring.model.GgsLog;
import ir.sheikhoo.goldengatemonitoring.model.GgsLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GgsLogServiceImp implements GgsLogService{

    private final RunCommand runCommand;
    private final GgsLogRepository ggsLogRepository;

    public GgsLogServiceImp(RunCommand runCommand, GgsLogRepository ggsLogRepository) {
        this.runCommand = runCommand;
        this.ggsLogRepository = ggsLogRepository;
    }

    @Override
    public String testCmd() {
        var out="";

        try {
            String command;
            if (runCommand.isWindows()) {
                command = "cd \"" + Setting.GGS_HOME + "\" && dir";
            }else {
                command = """
                    su %s -c %s/ggsci << EOF
                    %s
                    info all
                    exit
                    EOF
                    """;
            }
            command=command.formatted(Setting.GGS_USER,Setting.GGS_HOME,Setting.GGS_USER_PWD);

            var r = runCommand.run(command);
            String line;
            String srevice;
            String status;
            String title;
            String LagAtChkpt;
            String LagSinceChkpt;

            List<GgsLog> ggsLogs=new ArrayList<>();

            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }else {
                    try {
                        srevice = line.substring(0, line.indexOf(' '));
                    } catch (Exception e) {
                        srevice = "NULL";
                    }
                    var ggsLog=new GgsLog();
                    if(srevice.equals("MANAGER")){
                        status=line.substring(srevice.length()+2).trim();

                        ggsLog.setTime(LocalDateTime.now());
                        ggsLog.setProgram(srevice);
                        ggsLog.setStatus(status.trim().equals("RUNNING")?Boolean.TRUE:Boolean.FALSE);
                        ggsLogs.add(ggsLog);

                        out = out + "\n , " + srevice + ":" + status;
                    }else if(srevice.equals("EXTRACT") || srevice.equals("REPLICAT")){
                        line=line.trim().replaceAll(" +", " ");
                        String[] parts = line.split(" ");

                        status=parts[1];
                        title=parts[2];
                        LagAtChkpt=parts[3];
                        LagSinceChkpt=parts[4];

                        ggsLog.setTime(LocalDateTime.now());
                        ggsLog.setProgram(srevice);
                        ggsLog.setStatus(status.trim().equals("RUNNING")?Boolean.TRUE:Boolean.FALSE);
                        ggsLog.setGroupName(title);
                        ggsLog.setLagAtChkpt(LocalTime.parse(LagAtChkpt));
                        ggsLog.setLagSinceChkpt(LocalTime.parse(LagSinceChkpt));
                        ggsLogs.add(ggsLog);

                        out = out + "\n , " + srevice + ":" + status + "-" + title + "-" + LagAtChkpt + "-" + LagSinceChkpt;
                    }
                }
            }
            if(ggsLogs.size()>0){
                ggsLogRepository.saveAll(ggsLogs);
            }
        }catch (Exception e){
            return e.getMessage();
        }
        return out;

    }

    @Override
    public List<GgsLog> getAllLog() {
        return ggsLogRepository.findAll();
    }
}
