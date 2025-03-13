package io.sample.java_new_changes.features.modules;

import static java.lang.System.Logger.*;

public class Logger {

    private static final System.Logger log = System.getLogger(Logger.class.getName());

    public static void main(String[] args) {

        log.log(Level.DEBUG, "This will not be printed for custom logger");
        log.log(Level.INFO, "Custom logger Test");
    }
}
