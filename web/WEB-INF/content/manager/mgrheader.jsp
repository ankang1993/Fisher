<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<table width="960" border="0" align="center"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td>
            <div align="center"><a href="viewPunch.action">查看打卡</a></div>
        </td>
        <%--<td width="104">--%>
            <%--<div align="center"><a href="viewmanagerSalary.action">查看历史工资</a></div>--%>
        <%--</td>--%>
        <td>
            <div align="center"><a href="viewApp.action">签核申请</a></div>
        </td>
        <td>
            <div align="center"><a href="viewEmp.action">管理成员</a></div>
        </td>
        <td>
            <div align="center"><a href="editPass.action">修改密码</a></div>
        </td>
        <td>
            <div align="center"><a href="upload.action">上传文件</a></div>
        </td>
        <td>
        <div align="center"><a href="viewFile.action?page=1">查看文件</a></div>
        </td>
        <%--<td width="94">--%>
            <%--<div align="center"><a href="addEmp.action">新增员工</a></div>--%>
        <%--</td>--%>
        <%--<td width="160">--%>
            <%--<div align="center"><a href="viewDeptSal.action">查看上月部门发薪</a></div>--%>
        <%--</td>--%>
        <td>
            <div align="center"><a href="logout.action">退出系统</a></div>
        </td>
    </tr>
</table>
