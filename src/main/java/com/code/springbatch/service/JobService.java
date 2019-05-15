package com.code.springbatch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sonic
 * @date 2019/4/30 13:36
 */
@Service
public class JobService {
    @Autowired
    private Job userJob;

    @Autowired
    private JobLauncher jobLauncher;

    public void batchStart() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(userJob, jobParameters);
    }
}
