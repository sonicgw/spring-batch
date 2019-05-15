package com.code.springbatch.writer;

import com.code.springbatch.dao.PersonDao;
import com.code.springbatch.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import java.util.List;

/**
 * @author sonic
 * @date 2019/4/25 13:56
 */
public class UserWriter implements ItemWriter<Person> {
    private static  Logger LOG = LoggerFactory.getLogger(UserWriter.class);
    @Autowired
    private PersonDao personDao;

    @Override
    public void write(List<? extends Person> persons) throws Exception {
        LOG.info("write start");
        persons.forEach(
                person-> {
                    personDao.insert(person);
                }
        );
    }
}
