package com.byhieg.dao.interceptor;

import com.byhieg.dao.annotation.CreateTime;
import com.byhieg.dao.annotation.ModifiedTime;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@Intercepts(value = {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class DateInterceptor implements Interceptor {
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		Interceptor.super.setProperties(properties);
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

		Object parameter = invocation.getArgs()[1];

		List<Field> declaredFields = Arrays.asList(parameter.getClass().getDeclaredFields());

		for (Field field : declaredFields) {
			if (field.getAnnotation(CreateTime.class) != null) {
				if (sqlCommandType.equals(SqlCommandType.INSERT)) {
					field.setAccessible(true);
					field.set(parameter, new Date());
				}
			}
			if (field.getAnnotation(ModifiedTime.class) != null) {
				if (sqlCommandType.equals(SqlCommandType.INSERT) || sqlCommandType.equals(SqlCommandType.UPDATE)) {
					field.setAccessible(true);
					field.set(parameter, new Date());
				}
			}
		}
		
		return invocation.proceed();
	}
}
