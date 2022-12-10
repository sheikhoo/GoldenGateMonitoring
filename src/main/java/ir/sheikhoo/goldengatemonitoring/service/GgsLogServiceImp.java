package ir.sheikhoo.goldengatemonitoring.service;

import ir.sheikhoo.goldengatemonitoring.Setting;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;

@Service
public class GgsLogServiceImp implements GgsLogService{

    private final RunCommand runCommand;

    public GgsLogServiceImp(RunCommand runCommand) {
        this.runCommand = runCommand;
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

                    if(srevice.equals("MANAGER")){
                        status=line.substring(srevice.length()+2);
                        out = out + "\n , " + srevice + ":" + status;
                    }else if(srevice.equals("EXTRACT") || srevice.equals("REPLICAT")){
                        line=line.trim().replaceAll(" +", " ");
                        status=line.substring(srevice.length()+1,line.indexOf(' ',srevice.length()+1));
                        title=line.substring(srevice.length() + status.length() +2,line.indexOf(' ',srevice.length() + status.length() +2));
                        LagAtChkpt=line.substring(srevice.length() + status.length() + title.length() +3,line.indexOf(' ',srevice.length() + status.length() + title.length() +3));
                        LagSinceChkpt=line.substring(srevice.length() + status.length() + title.length() +LagAtChkpt.length() +4);
                        out = out + "\n , " + srevice + ":" + status + "-" + title + "-" + LagAtChkpt + "-" + LagSinceChkpt;
                    }

                }

            }
        }catch (Exception e){
            return "ERROR: " + e.getMessage();
        }
        return out;

    }
}
