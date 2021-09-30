<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="productBoard" value="${requestScope.productBoard}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>수정 페이지</h2>
<form action="productModify.board?title=${productBoard.title}" method="post" enctype="multipart/form-data">
    <input type="hidden" name="idx" value="${productBoard.idx}">
    <input type="text" name="title" value="${productBoard.title}">
    <input type="text" name="content" value="${productBoard.content}">
    <input type="text" name="price" value="${productBoard.price}">

    <img src="assets/upload/${productBoard.fileName1}"/>
    <button>삭제</button>
    <div>${productBoard.fileName1}</div>
    <img src="assets/upload/${productBoard.fileName2}"/>
    <button>삭제</button>
    <div>${productBoard.fileName2}</div>
    <img src="assets/upload/${productBoard.fileName3}"/>
    <button>삭제</button>
    <div>${productBoard.fileName3}</div>

    <label>file1</label>
    <input type="file" name="filename1">
    <label>file2</label>
    <input type="file" name="filename2">
    <label>file3</label>
    <input type="file" name="filename3">
    <input type="submit" value="수정">
</form>
</body>
</html>
