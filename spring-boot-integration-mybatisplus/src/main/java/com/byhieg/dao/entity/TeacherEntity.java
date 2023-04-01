package com.byhieg.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 老师表
 * </p>
 *
 * @author byhieg
 * @since 2023-04-01
 */
@TableName("teacher")
public class TeacherEntity extends Model<TeacherEntity> {

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
    @TableField(value = "modified_time",fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别
     */
    @TableField("sex")
    private SexEnum sex;

    @TableField("address_id")
    private Long addressId;

    public Date getCreateTime() {
        return createTime;
    }

    public TeacherEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public TeacherEntity setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeacherEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public TeacherEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public TeacherEntity setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }

    public Long getAddressId() {
        return addressId;
    }

    public TeacherEntity setAddressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }

    @Override
    public Serializable pkVal() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
        "createTime = " + createTime +
        ", modifiedTime = " + modifiedTime +
        ", name = " + name +
        ", age = " + age +
        ", sex = " + sex +
        ", addressId = " + addressId +
        "}";
    }
}
