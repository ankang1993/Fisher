<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>上传文件</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="../../js/uploadify.css">
    <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../../js/jquery.uploadify.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#myFile").uploadify({
//                'debug'     : true, //开启调试
                'auto'           : false, //是否自动上传
                'swf'            : '../../js/uploadify.swf',  //引入uploadify.swf
                'uploader'       : 'uploadFile',//请求路径
                'queueID'        : 'fileQueue',//队列id,用来展示上传进度的
                'width'     : '75',  //按钮宽度
                'height'    : '24',  //按钮高度
                'queueSizeLimit' : 5,  //同时上传文件的个数
                'multi'          : true,  //允许多文件上传
                'buttonText'     : '浏览文件',//按钮上的文字
                'fileSizeLimit' : '1000MB', //设置单个文件大小限制
                'fileObjName' : 'myFile',  //<input type="file"/>的name
                'method' : 'post',
                'removeCompleted' : true,//上传完成后自动删除队列
                'onFallback':function(){
                    alert("您未安装FLASH控件，无法上传文件！请安装FLASH控件后再试。");
                },
//                'onUploadSuccess' : function(file, data, response){//单个文件上传成功触发
//                    //data就是action中返回来的数据
//                },
                'onQueueComplete' : function(){//所有文件上传完成
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
                            上传文件（最多同时上传5个）
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="file" id="myFile" name="myFile">
                        <div id="fileQueue"></div>
                    </td>
                    <td>
                        <a href="javascript:$('#myFile').uploadify('upload','*')">开始上传</a>
                    </td>
                    <td>
                        <a href="javascript:$('#myFile').uploadify('cancel')">取消所有上传</a>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<%@include file="footer.jsp" %>
</body>
</html>
