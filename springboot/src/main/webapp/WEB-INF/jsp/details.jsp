<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        #body {
            margin: 0px auto;
            width: 800px;
            height: 600px;
        }
    </style>
</head>
<body>
<div id="body">
    <div style="margin: 0px auto; width: 500px;">
        <form action="BookInfo.do" id="forms" method="post">
            <table border="1px" style="text-align: center;">
                <tr>
                    <td>图书编号</td>
                    <td>${bookInfo.bookCode}</td>
                </tr>
                <tr>
                    <td>图书名称</td>
                    <td>${bookInfo.bookName}</td>
                </tr>
                <tr>
                    <td>图书分类</td>
                    <td style="text-align: left;">${bookInfo.bookTypes.typeName}</td>
                </tr>
                <tr>
                    <td>作者</td>
                    <td>${bookInfo.bookAuthor}</td>
                </tr>
                <tr>
                    <td>出版社</td>
                    <td>${bookInfo.publishPress}</td>
                </tr>
                <tr>
                    <td>出版时间</td>
                    <td><fmt:formatDate value="${bookInfo.publishDate }" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>借出状态</td>
                    <td>${bookInfo.isBorrow}</td>
                </tr>
                <tr>
                    <td>图片上传</td>
                    <td>
                        <img id="img" src="${bookInfo.bookPath}">
                        <a href="download.do?bookPath=${bookInfo.bookPath}">下载</a>
                    </td>

                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" value="返回" onclick="toBookInfo()">
                    </td>
                </tr>
            </table>
        </form>
    </div>

</div>
</body>
<script src="../../resource/js/jquery-2.1.4-baidu.js"></script>
<
<script >
    function toBookInfo() {
        window.location.href="BookInfo.do";
    }
</script>
</html>
