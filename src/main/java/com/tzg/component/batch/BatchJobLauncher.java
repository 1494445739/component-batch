package com.tzg.component.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BatchJobLauncher {

    private static final Logger logger = LoggerFactory.getLogger( BatchJobLauncher.class );

    @Resource
    private JobLauncher jobLauncher;

    private Job job;

    public void execute() throws Exception {

        Map< String, String > params = new HashMap<>();
        params.put( "now", new Date().getTime() + "" );

        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addString( "now", new Date().getTime() + "" );

        JobExecution jobExecution   = jobLauncher.run( job, builder.toJobParameters() );
        Long         jobExecutionId = jobExecution.getId();

        logger.info( "开始执行方法execute(): name=[{}], id=[{}], status=[{}]", job.getName(), jobExecutionId, jobExecution.getStatus() );
    }

    public void setJob( Job job ) {
        this.job = job;
    }

}
