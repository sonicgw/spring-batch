package com.code.springbatch.controller;

import com.code.springbatch.dao.JobDao;
import com.code.springbatch.entity.JobEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sonic
 * @date 2019/5/1 17:12
 */
@RestController
public class TestController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job userJob;
    @Autowired
    private JobDao jobDao;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void hello() {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = null;
        try {
            jobExecution = jobLauncher.run(userJob, jobParameters);
            JobEntity jobEntity = new JobEntity();
            jobEntity.setKey(jobExecution.getJobParameters().getParameters().keySet().iterator().next());
            jobEntity.setValue((Long)jobExecution.getJobParameters().getParameters().entrySet().iterator().next().getValue().getValue());
            jobEntity.setName(jobExecution.getJobInstance().getJobName());
            jobEntity.setStatus(jobExecution.getStatus().name());
            jobDao.insert(jobEntity);
        } catch (Exception e) {
            JobEntity jobEntity = new JobEntity();
            jobEntity.setKey(jobExecution.getJobParameters().getParameters().keySet().iterator().next());
            jobEntity.setValue((Long)jobExecution.getJobParameters().getParameters().entrySet().iterator().next().getValue().getValue());
            jobEntity.setName(jobExecution.getJobInstance().getJobName());
            jobEntity.setStatus(jobExecution.getStatus().name());
            jobDao.insert(jobEntity);
            e.printStackTrace();
        }
    }
}
