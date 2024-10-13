package com.bwoil.c2b.migration.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;

public class StepChunkNotificationListener implements ChunkListener {

    private static final Logger log = LoggerFactory.getLogger(StepChunkNotificationListener.class);

    private long beginTime;

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        beginTime = System.currentTimeMillis();
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        long endTime = System.currentTimeMillis();
        long startTime = chunkContext.getStepContext().getStepExecution().getStartTime().getTime();
        StepContext stepContext = chunkContext.getStepContext();
        log.info("job [{}] step [{}] write count:{}, {}ms(chunk)|{}ms(total), {}", stepContext.getJobName(), stepContext.getStepName(), stepContext.getStepExecution().getWriteCount(), endTime - beginTime, endTime - startTime, stepContext.getStepExecution().getSummary());
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {

    }
}
