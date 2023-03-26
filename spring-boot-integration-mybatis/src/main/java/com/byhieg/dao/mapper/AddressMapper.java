package com.byhieg.dao.mapper;

import com.byhieg.dao.entity.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by byhieg on 2023/3/27.
 * Mail to byhieg@gmail.com
 */
@Mapper
public interface AddressMapper {
	
	@Insert({
			"insert into address (create_time, modified_time, address) values (#{createTime},#{modifiedTime},#{address})"
	})
	Long insertAddress(Address address);
	
	
}
