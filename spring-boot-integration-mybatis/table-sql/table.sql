create table test01.address
(
    id            bigint auto_increment comment '主键'
        primary key,
    create_time   datetime     not null,
    modified_time datetime     null,
    address       varchar(200) not null
)
    comment '住址表';

create table test01.course
(
    id            bigint auto_increment comment '主键'
        primary key,
    create_time   datetime    not null comment '创建时间',
    modified_time datetime    not null comment '修改时间',
    name          varchar(20) not null comment '课程名'
)
    comment '课程表';

create table test01.student
(
    id            bigint auto_increment comment '主键id'
        primary key,
    create_time   datetime                        not null comment '创建时间',
    modified_time datetime                        not null comment '修改时间',
    name          varchar(50)                     not null comment '姓名',
    sex           enum ('male', 'female', 'none') not null comment '性别',
    age           int                             not null comment '年龄',
    class_name    varchar(20)                     not null comment '班级',
    address_id    bigint                          null comment '地址表的id'
)
    comment '学生表';

create index student_name_index
    on test01.student (name);

create table test01.student_course_ref
(
    id            bigint auto_increment comment '主键'
        primary key,
    create_time   datetime not null comment '创建时间',
    modified_time datetime not null comment '修改时间',
    student_id    bigint   not null comment '学生id',
    course_id     bigint   not null comment '课程id'
)
    comment '学生选课的关系表';

create index student_course_ref_course_id_index
    on test01.student_course_ref (course_id);

create index student_course_ref_student_id_course_id_index
    on test01.student_course_ref (student_id, course_id);

create index student_course_ref_student_id_index
    on test01.student_course_ref (student_id);

create table test01.teacher
(
    id            bigint auto_increment comment '主键'
        primary key,
    create_time   datetime                null comment '创建时间',
    modified_time datetime                not null comment '修改时间',
    name          varchar(50)             not null comment '姓名',
    age           int                     not null comment '年龄',
    sex           enum ('male', 'female') not null comment '性别',
    address_id    bigint                  null
)
    comment '老师表';

create table test01.teacher_course_ref
(
    id            bigint auto_increment comment '主键'
        primary key,
    create_time   datetime not null comment '创建时间',
    modified_time datetime not null comment '修改时间',
    teacher_id    bigint   not null comment '教师id',
    course_id     bigint   not null comment '课程id'
)
    comment '教师课程表';

create index teacher_course_ref_course_id_index
    on test01.teacher_course_ref (course_id);

create index teacher_course_ref_teacher_id_course_id_index
    on test01.teacher_course_ref (teacher_id, course_id);

create index teacher_course_ref_teacher_id_index
    on test01.teacher_course_ref (teacher_id);

