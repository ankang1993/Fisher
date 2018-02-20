<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>查看全部成员</title>
</head>
<body>
<%@include file="../header.jsp" %>
<%@include file="mgrheader.jsp" %>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td>
            <br>
            <table width="80%" border="0" align="center" cellspacing="1" bgcolor="#cccccc">
                <tr bgcolor="#e1e1e1">
                    <td colspan="3">
                        <div class="mytitle">查询成员</div>
                    </td>
                    <td colspan="2">
                        <s:form action="searchEmp" theme="simple">
                            <s:textfield name="name" label="姓名"/>
                            &nbsp;&nbsp;
                            <s:submit value="搜索"/>
                        </s:form>
                    </td>
                </tr>
                <tr bgcolor="#e1e1e1">
                    <td colspan="5">
                        <div class="mytitle">成员列表</div>
                    </td>
                </tr>
                <tr class="pt11" height="45">
                    <td><b>用户名</b></td>
                    <td><b>姓名</b></td>
                    <td><b>密码</b></td>
                    <td><b>电话</b></td>
                    <td><b>操作</b></td>
                </tr>
                <s:iterator value="emps" status="index">
                    <s:if test="#index.odd == true">
                        <tr style="background-color:#dddddd" class="pt11" height="32">
                    </s:if>
                    <s:else>
                        <tr class="pt11" height="32">
                    </s:else>
                    <td><s:property value="empName"/></td>
                    <td><s:property value="empRealName"/></td>
                    <td><s:property value="empPass"/></td>
                    <td><s:property value="phone"/></td>
                    <td><INPUT type="button" value="删除" onClick="deleteEmp(${empId})"></td>
                    </tr>
                </s:iterator>
            </table>
        </td>
    </tr>
</table>
<script>
    function deleteEmp(id){
        window.location.href="deleteEmp.action?empId=" + id;
    }
</script>
<%@include file="../footer.jsp" %>
</body>
</html>