package io.sample.java_new_changes.features.modules;

import java.util.HashMap;
import java.util.Map;

public class CustomLoggerFinder extends System.LoggerFinder{
    private static final Map<String, CustomConsoleLogger> LOGGERS = new HashMap<>();

    @Override
    public System.Logger getLogger(String name, Module module) {
        return LOGGERS.computeIfAbsent(name, CustomConsoleLogger::new);
    }
}
