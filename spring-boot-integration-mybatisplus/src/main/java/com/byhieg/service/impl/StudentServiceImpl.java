package com.byhieg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byhieg.dao.entity.StudentEntity;
import com.byhieg.dao.mapper.StudentMapper;
import com.byhieg.service.IStudentService;
import org.springframework.stereotype.Service;

/**
 * Created by byhieg on 2023/4/1.
 * Mail to byhieg@gmail.com
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements IStudentService {
}
