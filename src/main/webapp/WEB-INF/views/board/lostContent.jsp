<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="lost" value="${requestScope.lost}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="replyList" value="${requestScope.commentList}"/>

<div>${lost.idx}</div>
<div>${lost.id}</div>
<div>${lost.title}</div>
<div>${lost.content}</div>
<c:if test="${lost.fileName!='test'}">
    <img src="assets/upload/${lost.fileName}"/>
</c:if>
<br>
<button onclick="location.href='lostModify.board?idx=${lost.idx}&id=${lost.id}'">수정</button>
<button onclick="location.href='lostDelete.board?idx=${lost.idx}'">삭제</button>
<button onclick="location.href='lostReply.board?idx=${lost.idx}&cp=${cpage}&ps=${pagesize}'">답글</button>
<hr>
<h1>댓글 리스트</h1>
<form name="lostComment" action="lostCommentWrite.comment" method="post">
    <div class="commentList">
        등록된 댓글이 없습니다
    </div>
    <input type="hidden" name="idx" id="idx" value="${lost.idx}">
    <input type="hidden" name="writerid" value="${lost.id}">
    <h3>댓글</h3>
    <input type="text" name="commentid">
    <textarea name="commentcontent" rows="2" cols="50"></textarea>
    <input type="submit" value="등록">
</form>


</body>
<script src="${pageContext.request.contextPath}/webapp/assets/js/lostComment.js"></script>
</html>
