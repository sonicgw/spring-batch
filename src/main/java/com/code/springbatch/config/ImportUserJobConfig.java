package com.code.springbatch.config;

import com.code.springbatch.entity.Person;
import com.code.springbatch.entity.User;
import com.code.springbatch.processor.UserProcess;
import com.code.springbatch.reader.UserJdbcReader;
import com.code.springbatch.reader.UserReader;
import com.code.springbatch.writer.UserMybatisWriter;
import com.code.springbatch.writer.UserWriter;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sonic
 * @date 2019/4/25 11:27
 */
@Configuration
@EnableBatchProcessing
public class ImportUserJobConfig {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private DataSource dataSource;


    @Bean
    @StepScope
    public UserJdbcReader userJDBCReader() {
        UserJdbcReader userReader = new UserJdbcReader(dataSource);
        return userReader;
    }

    @Bean
    @StepScope
    public UserReader userReader(@Value("#{jobParameters['fromid']}")Long fromid,
                                             @Value("#{jobParameters['toid']}") Long toid) {
        UserReader userReader = new UserReader(1);
        userReader.setSqlSessionFactory(sqlSessionFactory);
        Map<String, Object> map = new HashMap<>(1<<4);
        map.put("fromid", fromid);
        map.put("toid", toid);
        userReader.setParameterValues(map);
        return userReader;
    }

    @Bean
    @StepScope
    public UserProcess userProcess() {
        return new UserProcess();
    }

    @Bean
    @StepScope
    public UserWriter userWriter() {
        UserWriter userWriter = new UserWriter();
//        userWriter.setSqlSessionFactory(sqlSessionFactory);
        return userWriter;
    }

    @Bean
    @StepScope
    public UserMybatisWriter userMybatisWriter() {
        UserMybatisWriter userMybatisWriter = new UserMybatisWriter();
        userMybatisWriter.setSqlSessionFactory(sqlSessionFactory);
        return userMybatisWriter;
    }

    @Bean
    public Step userStep(@Qualifier("userReader") UserReader userReader, @Qualifier("userProcess") UserProcess userProcess, @Qualifier("userWriter") UserWriter userWriter) {
        return steps.get("userStep").<User, Person>chunk(10).reader(userReader).processor(userProcess).writer(userWriter).build();
    }

    @Bean
    public Job userJob(@Qualifier("userStep") Step userStep) throws Exception {
        return this.jobs.get("userJob").start(userStep).build();
    }
}
