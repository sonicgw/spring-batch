package com.code.springbatch.reader;

import com.code.springbatch.entity.User;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sonic
 * @date 2019/4/29 14:20
 */
public class UserJdbcReader extends JdbcPagingItemReader<User> {

    public UserJdbcReader(DataSource dataSource){
        super();
        setQueryProvider(mySqlPagingQueryProvider());
        setDataSource(dataSource);
        setRowMapper(new BeanPropertyRowMapper<>(User.class));
    }

    private MySqlPagingQueryProvider mySqlPagingQueryProvider() {
        MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();
        Map<String, Order> sortKeys = new HashMap(1<<4);
        //设置查询结果排序字段及方式
        sortKeys.put("id", Order.ASCENDING);
        //设置查询的表字段
        mySqlPagingQueryProvider.setSelectClause("select *");
        //设置表名及对应的简写
        mySqlPagingQueryProvider.setFromClause("user");
        mySqlPagingQueryProvider.setWhereClause("1=1");
        mySqlPagingQueryProvider.setSortKeys(sortKeys);
        return mySqlPagingQueryProvider;
    }
}
