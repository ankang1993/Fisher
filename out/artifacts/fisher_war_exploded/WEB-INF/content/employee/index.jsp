<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>成员首页</title>
</head>
<body>
<%@include file="../header.jsp" %>
<%@include file="empheader.jsp" %>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr height="60">
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <%--<s:if test="actionMessages.size()>0">--%>
                <%--<div class="error">--%>
                <%--<s:actionmessage/>--%>
                <%--</div>--%>
            <%--</s:if>--%>
        </td>
    </tr>
    <tr height="80">
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
            <s:property value="#session.user"/>，欢迎您使用Fisher系统，您是普通成员。
        </td>
    </tr>
    <tr height="60">
        <td>&nbsp;</td>
    </tr>
</table>
<%@include file="../footer.jsp" %>
</body>
</html>
