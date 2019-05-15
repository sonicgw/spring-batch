package com.code.springbatch.dao;


import com.code.springbatch.entity.JobEntity;

import java.util.List;

/**
 * @author sonic
 * @date 2019/5/6 16:30
 */
public interface JobDao {
    List<JobEntity> select();

    int insert(JobEntity user);
}
