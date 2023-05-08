package com.clairvoyant.services.skillmatrix.graphql.instrumentation;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestLoggingInstrumentation extends SimpleInstrumentation {

    private final Logger log = LogManager.getLogger(this.getClass());


    private final Clock clock = Clock.systemUTC();


    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {

        var start = Instant.now(clock);

        var executionId = parameters.getExecutionInput().getExecutionId();

        log.info("{}: query: {} with variables: {}",executionId,parameters.getQuery(),parameters.getVariables());

        System.out.println(parameters.getVariables());

        return SimpleInstrumentationContext.whenCompleted(((executionResult, throwable) -> {
            var duration = Duration.between(start, Instant.now(clock));

            if (throwable == null) {
                log.info("{}: completed successfully in: {} with data : {}",executionId,duration,executionResult.getData());
            } else {
                log.warn("{}: failed in: {}",executionId,duration,throwable);
            }
        }));
    }
}
