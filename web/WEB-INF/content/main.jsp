<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Java EE简单系统</title>
</head>
<body>
<%@include file="header.jsp" %>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td colspan="3">请单击链接开始使用系统<br>
            <br>
            <a href="login.action">登录系统</a></td>
    </tr>
    <tr>
        <td colspan="3">
            <br>
            <p align="center">
                <span class="pt11">
                    这仅仅是一个Java EE的框架程序。应用模拟一个简单的工作系统。系统包含两个角色：<br>
                    普通员工的功能包括员工出勤打卡，查看打卡异动，人事异动申请，修改密码，查看文件；<br>
                    经理的功能包括查看员工打卡异动，管理员工异动申请，管理部门员工，修改密码，查看文件等。
                </span>
            </p>
            <p align="center" class="pt11">
                应用使用轻量级Java EE架构，技术包括：Struts 2.3、Spring 4.0、Hibernate 4.3、Quartz2.2。<br>
                整个应用使用Spring提供的DAO支持操作数据库，同时利用Spring的声明式事务，<br>
                程序中的权限检查使用Spring的AOP框架支持，也利用了Spring的任务调度抽象，<br>
                Hibernate为底层的数据库访问提供支持,作为O/R Mapping框架使用。
            </p>
            <p align="center" class="pt11">本程序版权属于AK<br>
                参考自李刚轻量级Java EE企业应用实战（第四版）<a href="http://www.crazyit.org">http://www.crazyit.org</a><br>
            </p>
        </td>
    </tr>
</table>
<%@include file="footer.jsp" %>
</body>
</html>
