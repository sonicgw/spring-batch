package com.code.springbatch;

import com.code.springbatch.service.DbService;
import com.code.springbatch.service.JobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBatchApplicationTests {

    @Autowired
    private DbService dbService;

    @Autowired
    private JobService jobService;
    @Test
    public void contextLoads() {
        dbService.insertUser();
    }

    @Test
    public void jobStart() {
        try {
            jobService.batchStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
