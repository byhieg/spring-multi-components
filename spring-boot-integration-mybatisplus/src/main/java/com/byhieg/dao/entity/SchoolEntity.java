package com.byhieg.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by byhieg on 2023/4/1.
 * Mail to byhieg@gmail.com
 */
@TableName(value = "school",autoResultMap = true)
public class SchoolEntity extends Model<SchoolEntity> {

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@TableField(value = "modified_time", fill = FieldFill.INSERT_UPDATE)
	private Date modifiedTime;


	
	@TableField(value = "content",typeHandler = FastjsonTypeHandler.class)
	private ContentEntity contentEntity;

	@Version
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public ContentEntity getContentEntity() {
		return contentEntity;
	}

	public void setContentEntity(ContentEntity contentEntity) {
		this.contentEntity = contentEntity;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	

	@Override
	public String toString() {
		return "SchoolEntity{" +
				"id=" + id +
				", createTime=" + createTime +
				", modifiedTime=" + modifiedTime +
				", contentEntity=" + contentEntity +
				'}';
	}
}
