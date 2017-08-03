# Java EE简单系统——Fisher

这仅仅是一个Java EE的框架程序。应用模拟一个简单的工作系统。系统包含两个角色：<

1. 普通员工的功能包括员工出勤打卡，员工的人事异动申请，查看文件；
2. 经理的功能包括管理部门员工，签核申请，查看文件等。

应用使用轻量级Java EE架构，技术包括：Struts 2.3、Spring 4.0、Hibernate 4.3、Quartz2.2。整个应用使用Spring提供的DAO支持操作数据库，同时利用Spring的声明式事务，程序中的权限检查使用Spring的AOP框架支持，也利用了Spring的任务调度抽象，Hibernate为底层的数据库访问提供支持,作为O/R Mapping框架使用。
