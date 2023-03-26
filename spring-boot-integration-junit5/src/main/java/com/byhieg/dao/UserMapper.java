package com.byhieg.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@Mapper
public interface UserMapper {
	
	@Insert("insert into tbl_user (name) values (#{name})")
	Integer insertUser(User user);
	
	@Select("select * from tbl_user")
	List<User> selectAll();
}
