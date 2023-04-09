package com.byhieg.dao.repository;

import com.byhieg.dao.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by byhieg on 2023/4/5.
 * Mail to byhieg@gmail.com
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> , JpaSpecificationExecutor<StudentEntity> {


	public List<StudentEntity> findByNameStartingWith(String name);

	public List<StudentEntity> findByAgeGreaterThan(Integer age);


	@Query("from StudentEntity where age > :age order by id desc")
	public List<StudentEntity> findByAge(Integer age);

	@Query("from StudentEntity where id in :ids")
	List<StudentEntity> findAllByIds(@Param("ids") List<Long> ids);
}
