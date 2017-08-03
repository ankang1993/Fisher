<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>电子打卡</title>
</head>
<body>
<%@include file="../header.jsp" %>
<%@include file="empheader.jsp" %>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td colspan="3"><br/><br/>
            <div class="mytitle">电子打卡系统</div>
            <s:if test="actionMessages.size()>0">
                <div class="error">
                    <s:actionmessage/>
                </div>
            </s:if>
        </td>
    </tr>
    <tr>
        <td colspan="3" style="text-align : center;">
            <br/>
            <!-- 当punchIsValid为1、3时，可上班打卡 -->
            <s:if test="punchIsValid == 1	|| punchIsValid == 3">
                <s:form action="employeeCome" theme="simple">
                    <s:submit value="上班打卡"/>
                </s:form>
            </s:if>
            <!-- 当punchIsValid为2、3时，可下班打卡 -->
            <s:if test="punchIsValid == 2 || punchIsValid == 3">
                <s:form action="employeeLeave" theme="simple">
                    <s:submit value="下班打卡"/>
                </s:form>
            </s:if>
            <br/>
        </td>
    </tr>
</table>
<%@include file="../footer.jsp" %>
</body>
</html>
