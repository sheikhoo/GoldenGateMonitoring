package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.Setting;
import ir.sheikhoo.goldengatemonitoring.model.ChartTimeType;
import ir.sheikhoo.goldengatemonitoring.model.GgsLog;
import ir.sheikhoo.goldengatemonitoring.model.GgsLogChartDto;
import ir.sheikhoo.goldengatemonitoring.model.GgsLogRepository;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Scheduled(fixedRate = 60000)
    public String getDataFromCmd() {
        System.out.println("Run cmd");
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
            var localDateTime=LocalDateTime.now();

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

                        ggsLog.setTime(localDateTime);
                        ggsLog.setProgram(srevice);
                        ggsLog.setStatus(status.trim().equals("RUNNING")?Boolean.TRUE:Boolean.FALSE);
                        ggsLog.setStatusTitle(status);
                        ggsLogs.add(ggsLog);

                        out = out + "\n , " + srevice + ":" + status;
                    }else if(srevice.equals("EXTRACT") || srevice.equals("REPLICAT")){
                        line=line.trim().replaceAll(" +", " ");
                        String[] parts = line.split(" ");

                        status=parts[1];
                        title=parts[2];
                        LagAtChkpt=parts[3];
                        LagSinceChkpt=parts[4];

                        ggsLog.setTime(localDateTime);
                        ggsLog.setProgram(srevice);
                        ggsLog.setStatus(status.trim().equals("RUNNING")?Boolean.TRUE:Boolean.FALSE);
                        ggsLog.setStatusTitle(status);
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

    @Override
    public List<GgsLog> getCurrent() {
        LocalDateTime time=ggsLogRepository.findFirstByOrderByTimeDesc().getTime();
        return ggsLogRepository.findAllByTime(time);
    }

    @Override
    public List<GgsLog> getAllSevenDaysAgo(String groupName) {
        LocalDateTime localDateTime=LocalDateTime.now().minusDays(7);
        return ggsLogRepository.findAllWithTimeSevenDaysAgo(localDateTime, groupName);
    }

    @Override
    public GgsLogChartDto getDataSevenDaysAgo(String groupName) {
        var ggsLogChart=new GgsLogChartDto();
        List<GgsLog> ggsLogs=getAllSevenDaysAgo(groupName);
        List<LocalDateTime> times =  ggsLogs.stream().map(t->t.getTime()).toList();
        List<LocalTime> lagAtChkpts = ggsLogs.stream().map(t->t.getLagAtChkpt()).toList();
        List<Integer> lagAtChkptsSec = ggsLogs.stream().map(t->t.getLagAtChkpt().toSecondOfDay()).toList();
        List<LocalTime> lagSinceChkpt = ggsLogs.stream().map(t->t.getLagSinceChkpt()).toList();
        List<Integer> lagSinceChkptSec = ggsLogs.stream().map(t->t.getLagSinceChkpt().toSecondOfDay()).toList();
        return new GgsLogChartDto(times,lagAtChkpts,lagAtChkptsSec,lagSinceChkpt,lagSinceChkptSec);
    }

    @Override
    public GgsLogChartDto getChartDataList(String groupName, ChartTimeType type) {
        LocalDateTime localDateTime=LocalDateTime.now();
        switch (type){
            case LAST_HOUR -> localDateTime=localDateTime.minusHours(1);
            case LAST_DAY -> localDateTime=localDateTime.minusDays(1);
            case LAST_7DAY -> localDateTime=localDateTime.minusDays(7);
        };
        List<GgsLog> ggsLogs=ggsLogRepository.findAllWithTimeSevenDaysAgo(localDateTime, groupName);

        var ggsLogChart=new GgsLogChartDto();
        List<LocalDateTime> times =  ggsLogs.stream().map(t->t.getTime()).toList();
        List<LocalTime> lagAtChkpts = ggsLogs.stream().map(t->t.getLagAtChkpt()).toList();
        List<Integer> lagAtChkptsSec = ggsLogs.stream().map(t->t.getLagAtChkpt().toSecondOfDay()).toList();
        List<LocalTime> lagSinceChkpt = ggsLogs.stream().map(t->t.getLagSinceChkpt()).toList();
        List<Integer> lagSinceChkptSec = ggsLogs.stream().map(t->t.getLagSinceChkpt().toSecondOfDay()).toList();
        return new GgsLogChartDto(times,lagAtChkpts,lagAtChkptsSec,lagSinceChkpt,lagSinceChkptSec);
    }

    @Override
    public String ggsLogs() {
        try{
            String command;
            if (runCommand.isWindows()) {
                command = "cd \"" + Setting.GGS_HOME + "\" && dir";
            }else {
                command = "tail -n 1000 " + Setting.GGS_HOME + "/ggserr.log";
            }
            command=command.formatted(Setting.GGS_USER,Setting.GGS_HOME,Setting.GGS_USER_PWD);

            var r = runCommand.run(command);

            String line;
            String out="";
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                } else {
                    if(line.indexOf("info all")==-1) {
                        out += line + " <br> ";
                    }
                }
            }
            return out;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
