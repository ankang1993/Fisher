<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>查看打卡</title>
    <link href="images/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="../header.jsp" %>
<%@include file="mgrheader.jsp" %>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td>
            <table width="80%" border="0" align="center" bgcolor="#cccccc">
                <tr bgcolor="#e1e1e1">
                    <td colspan="5">
                        <div class="mytitle">查看员工打卡情况</div>
                    </td>
                </tr>
                <tr bgcolor="#e1e1e1">
                    <td colspan="5">您只能查看全部员工最近七天的非正常打卡。</td>
                </tr>
                <tr class="pt11" height="45">
                    <td><b>用户名</b></td>
                    <td><b>姓名</b></td>
                    <td><b>异动类型</b></td>
                    <td><b>打卡日期</b></td>
                    <td><b>打卡时间</b></td>
                </tr>
                <s:iterator value="punchs" status="index">
                    <s:if test="#index.odd == true">
                        <tr style="background-color:#dddddd" class="pt11" height="32">
                    </s:if>
                    <s:else>
                        <tr class="pt11" height="32">
                    </s:else>
                    <td><s:property value="empName"/></td>
                    <td><s:property value="empRealName"/></td>
                    <td><s:property value="unType"/></td>
                    <td><s:property value="dutyDay"/></td>
                    <td><s:property value="time"/></td>
                    </tr>
                </s:iterator>
            </table>
        </td>
    </tr>
</table>
<%@include file="../footer.jsp" %>
</body>
</html>