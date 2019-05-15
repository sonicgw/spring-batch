package com.code.springbatch.processor;

import com.code.springbatch.entity.Person;
import com.code.springbatch.entity.User;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author sonic
 * @date 2019/4/25 13:51
 */
public class UserProcess implements ItemProcessor<User, Person> {

    @Override
    public Person process(User user) throws Exception {
        if(user.getName().equals("ls")){
            throw new Exception("test");
        }
        Person person = new Person();
        person.setAge(user.getAge());
        person.setName(user.getName());

        return person;
    }
}
