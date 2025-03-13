package io.sample.java_new_changes.features.modules;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@Slf4j
public class CustomConsoleLogger implements System.Logger {
    private final String name;

    public CustomConsoleLogger(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isLoggable(Level level) {

        return level.getSeverity() >= Level.INFO.getSeverity();
    }

    @Override
    public void log(Level level, ResourceBundle bundle, String msg, Throwable thrown) {
        if (isLoggable(level)) {
            log.info("ConsoleLogger [{}}]: {}", level, msg);
            if (thrown != null) {
                thrown.printStackTrace();
            }
        }
    }

    @Override
    public void log(Level level, ResourceBundle bundle, String format, Object... params) {
        if (isLoggable(level)) {
            log.info("ConsoleLogger [{}]: {}", level, MessageFormat.format(format, params));
        }
    }
}
