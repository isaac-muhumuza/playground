package io.sample.playground.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ProcessorModeCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String dexiMode = activeProfiles.length > 0 ? activeProfiles[0] : "service";

        return "processor".equalsIgnoreCase(dexiMode);
    }
}
