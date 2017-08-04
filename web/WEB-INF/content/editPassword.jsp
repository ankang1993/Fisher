<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>修改密码</title>
    <s:head/>
</head>
<body>
<%@include file="header.jsp" %>
<s:if test="#session.level == 'mgr'">
    <%@include file="manager/mgrheader.jsp" %>
</s:if>
<s:else>
    <%@include file="employee/empheader.jsp" %>
</s:else>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td>
            更改密码<br/>
            <s:if test="actionMessages.size()>0">
                <div class="error">
                    <s:actionmessage/>
                </div>
            </s:if>
            <s:actionerror cssClass="error"/>
            <div align="center">
                <s:form action="editPassword">
                    <s:password name="originalPass" label="原密码"/>
                    <s:password name="newPass" label="新密码"/>
                    <s:password name="confirmPass" label="确认密码"/>
                    <tr>
                        <td colspan="2">
                            <s:submit value="更改" theme="simple"/>
                        </td>
                    </tr>
                </s:form>
            </div>
        </td>
    </tr>
</table>
<%@include file="footer.jsp" %>
</body>
</html>