<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="bbs" action="productWrite.board" method="POST" enctype="multipart/form-data">
    <label>title</label>
    <input type="text" name="title">
    <label>id</label>
    <input type="text" name="id">
    <label>content</label>
    <input type="text" name="content">
    <label>price</label>
    <input type="text" name="price">
    <label>address</label>
    <input type="text" name="address">
    <label>file1</label>
    <input type="file" name="filename1">
    <label>file2</label>
    <input type="file" name="filename2">
    <label>file3</label>
    <input type="file" name="filename3">
    <input type="submit">
</form>
</body>
</html>
