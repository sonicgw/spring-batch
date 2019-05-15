//package com.code.springbatch.quartz;
//
//import com.code.springbatch.dao.JobDao;
//import com.code.springbatch.entity.JobEntity;
//import org.springframework.batch.core.configuration.JobLocator;
//import org.springframework.batch.core.configuration.JobRegistry;
//import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//public class QuartzConfiguration {
//
//    /**
//     * 自动注入进来的是SimpleJobLauncher
//      */
//	@Autowired
//	private JobLauncher jobLauncher;
//
//	@Autowired
//	private JobLocator jobLocator;
//
//	@Autowired
//    private JobDao jobDao;
//
//	/*用来注册job*/
//	/*JobRegistry会自动注入进来*/
//	@Bean
//	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry){
//		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
//		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
//		return jobRegistryBeanPostProcessor;
//	}
//
//	@Bean
//	public JobDetailFactoryBean jobDetailFactoryBean(){
//		JobDetailFactoryBean jobFactory = new JobDetailFactoryBean();
//		jobFactory.setJobClass(QuartzJobLauncher.class);
//		jobFactory.setGroup("my_group");
//		jobFactory.setName("my_job");
//		Map<String, Object> map = new HashMap<>();
//        List<JobEntity> jobEntities = jobDao.select();
//
//        map.put("jobName", jobEntities.get(0).getName());
//        map.put(jobEntities.get(0).getKey(),jobEntities.get(0).getValue());
//		map.put("jobLauncher", jobLauncher);
//		map.put("jobLocator", jobLocator);
//		jobFactory.setJobDataAsMap(map);
//		return jobFactory;
//	}
//
//	@Bean
//	public CronTriggerFactoryBean cronTriggerFactoryBean(){
//		CronTriggerFactoryBean cTrigger = new CronTriggerFactoryBean();
//		System.out.println("------- : " + jobDetailFactoryBean().getObject());
//		cTrigger.setJobDetail(jobDetailFactoryBean().getObject());
//		cTrigger.setStartDelay(5000);
//		cTrigger.setName("my_trigger");
//		cTrigger.setGroup("trigger_group");
//        // 每间隔5s触发一次Job任务
//		cTrigger.setCronExpression("0/10 * * * * ? ");
//		return cTrigger;
//	}
//
//	@Bean
//	public SchedulerFactoryBean schedulerFactoryBean(){
//		SchedulerFactoryBean schedulerFactor = new SchedulerFactoryBean();
//		schedulerFactor.setTriggers(cronTriggerFactoryBean().getObject(), cronTriggerFactoryBean().getObject());
//		return schedulerFactor;
//	}
//
//}
