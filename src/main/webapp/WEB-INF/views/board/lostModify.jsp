<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="lostBoard" value="${requestScope.lostBoard}"/>
<h2>수정 페이지</h2>
<form action="lostModify.board?title=${lostBoard.title}" method="post" enctype="multipart/form-data">
    <div></div>
    <input type="hidden" name="idx" value="${lostBoard.idx}">
    <input type="text" name="title" value="${lostBoard.title}">
    <input type="text" name="content" value="${lostBoard.content}">
    <c:if test="${not empty lostBoard.fileName}">
        <img src="assets/upload/${lostBoard.fileName}"/>
        <button>삭제</button>
        <div>${lostBoard.fileName}</div>
    </c:if>
    <input type="file" name="file">
    <input type="submit" value="수정">
</form>
</body>
</html>
