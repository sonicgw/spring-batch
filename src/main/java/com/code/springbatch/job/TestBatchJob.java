package com.code.springbatch.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sonic
 * @date 2019/5/9 16:05
 */
public class TestBatchJob implements SimpleJob {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job userJob;

    @Override
    public void execute(ShardingContext shardingContext) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder().addLong("time", System.currentTimeMillis());
        int key = shardingContext.getShardingItem();
        switch (key) {
            case 0:
                System.out.println("任务调度执行: " + key);
                jobParametersBuilder.addLong("fromid", 1L);
                jobParametersBuilder.addLong("toid", 2L);
                break;
            case 1:
                System.out.println("任务调度执行: " + key);
                jobParametersBuilder.addLong("fromid", 2L);
                jobParametersBuilder.addLong("toid", 3L);
                break;
            case 2:
                System.out.println("任务调度执行: " + key);
                jobParametersBuilder.addLong("fromid", 3L);
                jobParametersBuilder.addLong("toid", 4L);
                break;
        }
        try {
            jobLauncher.run(userJob, jobParametersBuilder.toJobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
