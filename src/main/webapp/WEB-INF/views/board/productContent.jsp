<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="product" value="${requestScope.product}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="replyList" value="${requestScope.commentList}"/>

<div>${product.idx}</div>
<div>${product.id}</div>
<div>${product.title}</div>
<div>${product.content}</div>

<img src="assets/upload/${product.fileName1}"/>
<img src="assets/upload/${product.fileName2}"/>
<img src="assets/upload/${product.fileName3}"/>

<br>
<button onclick="location.href='productModify.board?idx=${product.idx}&id=${product.id}'">수정</button>
<button onclick="location.href='productDelete.board?idx=${product.idx}'">삭제</button>
<hr>
<h1>댓글 리스트</h1>
<form name="productComment" action="productCommentWrite.comment" method="post">
    <div class="commentList">
        등록된 댓글이 없습니다
    </div>
    <input type="hidden" name="idx" id="idx" value="${product.idx}">
    <input type="hidden" name="writerid" value="${product.id}">
    <h3>댓글</h3>
    <input type="text" name="commentid">
    <textarea name="commentcontent" rows="2" cols="50"></textarea>
    <input type="submit" value="등록">
</form>


</body>
<script src="${pageContext.request.contextPath}/webapp/assets/js/productComment.js"></script>
</html>
