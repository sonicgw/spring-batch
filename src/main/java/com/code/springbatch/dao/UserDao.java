package com.code.springbatch.dao;



import com.code.springbatch.entity.User;

import java.util.List;

public interface UserDao {
	
	List<User> select();

	int insert(User user);
}
