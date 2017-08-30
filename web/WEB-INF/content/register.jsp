<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>注册新账户</title>
    <s:head/>
</head>
<body>
<%@include file="header.jsp" %>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td>
            请输入您的资料<br/>
            <s:if test="actionMessages.size()>0">
                <div class="error">
                    <s:actionmessage/>
                </div>
            </s:if>
            <s:actionerror cssClass="error"/>
            <div align="center">
                <s:form action="processAdd" validate="true" method="POST">
                    <s:textfield name="emp.name" label="用户名"/>
                    <s:textfield name="emp.realname" label="真实姓名"/>
                    <s:password name="emp.pass" label="密码"/>
                    <s:password name="confirmPass" label="确认密码"/>
                    <s:textfield name="emp.phone" label="手机号"/>
                    <tr>
                        <td colspan="2">
                            <s:submit value="注册" theme="simple"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:reset theme="simple" value="重填"/>
                        </td>
                    </tr>
                </s:form>
            </div>
            <br>
            已有账户？<INPUT type="button" value="登陆" onClick="location.href='login'">
        </td>
    </tr>
</table>
<%@include file="footer.jsp" %>
</body>
</html>
