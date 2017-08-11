drop database if exists fisher;

create database fisher CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

use fisher;

create table attend_type_inf
(
 type_id int auto_increment,
 amerce_amount double not null,
 type_name varchar(50) not null,
 primary key(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table employee_inf
(
 emp_id int auto_increment,
 emp_type int,
 emp_name varchar(50) not null,
 emp_realname varchar(50) not null,
 emp_pass varchar(50) not null,
 emp_phone varchar(50) not null,
 mgr_id int,
 dept_name varchar(50),
 primary key(emp_id),
 unique key(emp_name),
 foreign key(mgr_id) references employee_inf(emp_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table file_inf
(
 file_id int auto_increment,
 file_name varchar(255) not null,
 file_address varchar(255) not null,
 file_type varchar(255) not null,
 primary key(file_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table attend_inf
(
 attend_id int auto_increment,
 duty_day varchar(50) not null,
 punch_time datetime,
 is_come boolean not null,
 type_id int not null,
 emp_id int not null,
 primary key(attend_id),
 foreign key(type_id) references attend_type_inf(type_id),
 foreign key(emp_id) references employee_inf(emp_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table application_inf
(
 app_id int auto_increment,
 attend_id int not null,
 app_reason varchar(255),
 app_result boolean,
 type_id int not null,
 primary key(app_id),
 foreign key(type_id) references attend_type_inf(type_id),
 foreign key(attend_id) references attend_inf(attend_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table checkback_inf
(
 check_id int auto_increment,
 app_id int not null,
 check_result boolean not null,
 check_reason varchar(255),
 mgr_id int not null,
 primary key(check_id),
 foreign key(app_id) references application_inf(app_id),
 foreign key(mgr_id) references employee_inf(emp_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into attend_type_inf ( type_name , amerce_amount)
	values ( '正常', 0);
insert into attend_type_inf ( type_name , amerce_amount)
	values ( '事假', -20);
insert into attend_type_inf ( type_name , amerce_amount)
	values ( '病假', -10);
insert into attend_type_inf ( type_name , amerce_amount)
	values ( '迟到', -10);
insert into attend_type_inf ( type_name , amerce_amount)
	values ( '早退', -10);
insert into attend_type_inf ( type_name , amerce_amount)
	values ( '旷工', -30);
insert into attend_type_inf ( type_name , amerce_amount)
	values ( '出差', 10);

# 插入经理
insert into employee_inf (emp_type , emp_name , emp_realname , emp_pass , emp_phone , mgr_id , dept_name)
	values (2, 'admin', 'BOSS', 'admin' , '010-1234567' , null , 'AK');
# 员工
insert into employee_inf (emp_type , emp_name , emp_realname , emp_pass , emp_phone , mgr_id)
	values (1 , 'test', '测试', 'test' , '155555555555' , 1);

