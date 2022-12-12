package ir.sheikhoo.goldengatemonitoring.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class RunCommand {
    public Boolean isWindows(){
        return System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
    }

    public BufferedReader run(String command, @Nullable String runner) throws IOException {
        var builder = new ProcessBuilder();
        if (isWindows()) {
            if(runner.equals("powershell")) {
                builder.command("powershell.exe", "-Command", command);
            }else {
                builder.command("cmd.exe", "/c", command);
            }
        } else {
            builder.command("sh", "-c", command);
        }
        builder.redirectErrorStream(true);
        Process p = builder.start();

        return new BufferedReader(new InputStreamReader(p.getInputStream()));

    }
}
