package com.byhieg.dao.handler;

import com.byhieg.dao.entity.ClassNameEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class ClassNameHandler extends BaseTypeHandler<ClassNameEnum> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ClassNameEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getValue());
	}

	@Override
	public ClassNameEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return ClassNameEnum.getClassName(rs.getString(columnName));	
	}

	@Override
	public ClassNameEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return ClassNameEnum.getClassName(rs.getString(columnIndex));
	}

	@Override
	public ClassNameEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return ClassNameEnum.getClassName(cs.getString(columnIndex));
	}
}
