package ir.sheikhoo.goldengatemonitoring.service;

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

    public BufferedReader run(String command) throws IOException {
        var builder = new ProcessBuilder();
        if (isWindows()) {
            builder.command("cmd.exe", "/c", command);
        } else {
            builder.command("sh", "-c", command);
        }
        builder.redirectErrorStream(true);
        Process p = builder.start();

        return new BufferedReader(new InputStreamReader(p.getInputStream()));

    }
}
