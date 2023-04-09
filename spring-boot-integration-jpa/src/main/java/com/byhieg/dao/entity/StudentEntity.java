package com.byhieg.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by byhieg on 2023/4/2.
 * Mail to byhieg@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "student")
@Data
@ToString(callSuper = true)
@DynamicUpdate
public class StudentEntity extends BaseEntity{
	
	private String name;
	
	private String sex;
	
	private Integer age;

	private String className;

	private Long addressId;
}
