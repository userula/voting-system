package com.example.finalproj.logger;

import com.example.finalproj.repository.dto.Account;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginLogger implements Runnable {
    private final File file;
    private final Account user;

    public LoginLogger(Account user) {
        file = new File("src/main/resources/logs/login.log");
        this.user = user;
    }

    @Override
    public void run() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        String logMsg = '[' + dateFormat.format(currentDate)
                + " " + user.getUserId()
                + " " + user.getUsername()
                + " " + user.getRole().getRoleName()
                + "]";

        LogWriter.writeLog(file, logMsg);
    }
}
