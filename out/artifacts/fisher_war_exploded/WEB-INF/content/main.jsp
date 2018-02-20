<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Java EE框架程序</title>
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
                    这仅仅是一个简单的Java EE框架程序，使用了轻量级Java EE架构，包括：Struts 2.3、Spring 4.0、Hibernate 4.3、Quartz 2.2、Jquery·Uploadify。<br>
                    应用模拟了一个简单的工作流系统，系统包含两个角色：<br>
                    普通员工：功能包括员工出勤打卡、查看打卡异动、人事异动申请、修改密码、上传文件、查看文件、退出系统；<br>
                    BOSS：功能包括查看员工打卡异动、签核员工异动申请、管理部门员工、修改密码、上传文件、查看文件、退出系统。
                </span>
            </p>
            <p align="center" class="pt11">本程序版权属于AK<br>
                参考自李刚轻量级Java EE企业应用实战（第四版）
            </p>
        </td>
    </tr>
</table>
<%@include file="footer.jsp" %>
</body>
</html>
