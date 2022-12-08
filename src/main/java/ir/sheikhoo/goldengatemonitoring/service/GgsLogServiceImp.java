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
                command = "ls " + Setting.GGS_HOME;
            }
            var r = runCommand.run(command);
            String line;

            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                out = out + "\n" + line;
            }
        }catch (Exception e){
            return e.getMessage();
        }
        return out;

    }
}
