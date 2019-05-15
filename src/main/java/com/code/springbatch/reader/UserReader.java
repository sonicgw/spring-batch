package com.code.springbatch.reader;

import com.code.springbatch.entity.User;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sonic
 * @date 2019/4/25 13:44
 */
public class UserReader extends MyBatisPagingItemReader<User> {
    private static Logger LOG = LoggerFactory.getLogger(UserReader.class);



    public UserReader(int pageSize){
        super();
        this.setQueryId("com.code.springbatch.dao.UserDao.select");
        this.setPageSize(pageSize);
//        Map<String, Object> map = new HashMap<>();
//        map.put("fromid", fromid);
//        map.put("toid", toid);
//        this.setParameterValues(map);
    }
}
