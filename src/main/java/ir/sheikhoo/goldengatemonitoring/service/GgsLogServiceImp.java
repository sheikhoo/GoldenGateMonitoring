package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.model.*;
import ir.sheikhoo.goldengatemonitoring.repository.GgsLogRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GgsLogServiceImp implements GgsLogService{

    private final RunCommand runCommand;
    private final GgsLogRepository ggsLogRepository;
    private final ConfigService configService;

    public GgsLogServiceImp(RunCommand runCommand, GgsLogRepository ggsLogRepository, ConfigService configService) {
        this.runCommand = runCommand;
        this.ggsLogRepository = ggsLogRepository;
        this.configService = configService;
    }

    @Scheduled(fixedRate = 60000)
    public void getDataFromCmd() {
        String ggsHome=configService.getGgsHome();
        GgsUserInfoDto ggsUserInfo=configService.getGgsUserInfo();

        System.out.println("Run cmd");
        var out="";

        try {
            String command;
            BufferedReader r;
            if (runCommand.isWindows()) {
                command = "echo info all | " + ggsHome + "\\ggsci.exe";
                r = runCommand.run(command,CmdRunnerEnum.CMD);
            }else {
                command = """
                    su %s -c %s/ggsci << EOF
                    %s
                    info all
                    exit
                    EOF
                    """;
                command=command.formatted(ggsUserInfo.getGgsUser(),ggsHome,ggsUserInfo.getGgsUserPwd());
                r = runCommand.run(command,CmdRunnerEnum.SH);
            }

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
            e.getMessage();
        }

    }

    @Override
    public List<GgsLog> getAllLog() {
        return ggsLogRepository.findAll();
    }

    @Override
    public List<GgsLog> getCurrent() throws Exception {
        LocalDateTime time;
        try {
            time=ggsLogRepository.findFirstByOrderByTimeDesc().getTime();
        }catch (Exception e){
            throw new Exception();
        }

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
    public GgsLogChartDto getChartDataList(String groupName, ChartTimeEnum type) {
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
            BufferedReader r;
            if (runCommand.isWindows()) {
                command = "Get-Content " + configService.getGgsHome() + "\\ggserr.log -Tail 1000";
                r = runCommand.run(command,CmdRunnerEnum.POWER_SHELL);
            }else {
                command = "tail -n 1000 " + configService.getGgsHome() + "/ggserr.log";
                r = runCommand.run(command, CmdRunnerEnum.SH);
            }

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
