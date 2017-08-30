<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>上传文件</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery/uploadify.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.uploadify.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#myFile").uploadify({
//                'debug'     : true, //开启调试
                'auto': false, //是否自动上传
                'swf': '${pageContext.request.contextPath}/jquery/uploadify.swf',  //引入uploadify.swf
                'uploader': 'uploadFile',//请求路径
                'queueID': 'fileQueue',//队列id,用来展示上传进度的
                'width': '75',  //按钮宽度
                'height': '24',  //按钮高度
                'queueSizeLimit': 100,  //同时上传文件的个数
                'multi': true,  //允许多文件上传
                'buttonText': '浏览文件',//按钮上的文字
                'fileSizeLimit': '1000MB', //设置单个文件大小限制
                'fileObjName': 'myFile',  //<input type="myFile"/>的name
                'method': 'post',
                'removeCompleted': true,//上传完成后自动删除队列
                'onFallback': function () {
                    alert("您未安装FLASH控件，无法上传文件！请安装FLASH控件后再试。");
                },
                'onQueueComplete': function () {//所有文件上传完成
                    alert("文件全部上传成功!");
                }
            });
        });
    </script>
</head>
<body>
<%@include file="header.jsp" %>
<s:if test="#session.level == 'mgr'">
    <%@include file="manager/mgrheader.jsp" %>
</s:if>
<s:else>
    <%@include file="employee/empheader.jsp" %>
</s:else>
<table align="center" width="960"
       background="${pageContext.request.contextPath}/images/bodybg.jpg">
    <tr>
        <td>
            <table width=80% border=0 align="center"
                   cellspacing="1" bgcolor="#cccccc">
                <tr bgcolor="#e1e1e1">
                    <td colspan="3">
                        <div class="mytitle">
                            上传文件
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="myFile" id="myFile" name="myFile">
                    </td>
                    <td>
                        <a href="javascript:$('#myFile').uploadify('upload','*')">开始所有上传</a>
                    </td>
                    <td>
                        <a href="javascript:$('#myFile').uploadify('cancel','*')">取消所有上传</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        文件队列
                    </td>
                    <td colspan="2">
                        <div id="fileQueue"></div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<%@include file="footer.jsp" %>
</body>
</html>
