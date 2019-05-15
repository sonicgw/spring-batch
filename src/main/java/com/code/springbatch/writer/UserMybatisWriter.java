package com.code.springbatch.writer;

import org.mybatis.spring.batch.MyBatisBatchItemWriter;

/**
 * @author sonic
 * @date 2019/4/30 9:24
 */
public class UserMybatisWriter extends MyBatisBatchItemWriter {
    public UserMybatisWriter(){
        setStatementId("com.code.springbatch.dao.UserDao.insert");
    }
}
