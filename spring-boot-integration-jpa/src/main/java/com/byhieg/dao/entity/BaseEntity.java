package com.byhieg.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by byhieg on 2023/4/10.
 * Mail to byhieg@gmail.com
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Date createTime;

	@Column
	private Date modifiedTime;
}
