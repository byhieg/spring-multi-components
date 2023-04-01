package com.byhieg.dao.generate;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

/**
 * Created by byhieg on 2023/3/28.
 * Mail to byhieg@gmail.com
 */
public class MyBatisPlusGenerate {

	public static void main(String[] args) {
		DataSourceConfig dataSourceConfig = new DataSourceConfig
				.Builder("jdbc:mysql://192.168.3.106:3306/test01?characterEncoding=utf8", "root", "root")
				.build();
		GlobalConfig globalConfig = new GlobalConfig
				.Builder()
				.author("byhieg")
				.dateType(DateType.ONLY_DATE)
				.build();
		PackageConfig packageConfig = new PackageConfig.Builder()
				.parent("com.byhieg.dao")
				.mapper("mapper")
				.entity("entity")
				.build();

		StrategyConfig strategyConfig = new StrategyConfig.Builder()
				.addTablePrefix("tbl_")
				.entityBuilder()
				.disableSerialVersionUID()
				.enableChainModel()
				.enableRemoveIsPrefix()
				.enableTableFieldAnnotation()
				.enableActiveRecord()
				.naming(NamingStrategy.no_change)
				.columnNaming(NamingStrategy.underline_to_camel)
				.addTableFills(new Column("create_time", FieldFill.INSERT))
				.addTableFills(new Column("modified_time", FieldFill.INSERT_UPDATE))
				.idType(IdType.AUTO)
				.formatFileName("%sEntity")
				.mapperBuilder()
				.superClass(BaseMapper.class)
				.enableMapperAnnotation()
				.enableBaseResultMap()
				.enableBaseColumnList()
				.formatMapperFileName("%sMapper")
				.build();

		new AutoGenerator(dataSourceConfig)
				.global(globalConfig)
				.packageInfo(packageConfig)
				.strategy(strategyConfig).execute();

	}
}
