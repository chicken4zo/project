<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="daily" value="${requestScope.daily}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="commentList" value="${requestScope.commentList}"/>

<div>${daily.idx}</div>
<div>${daily.id}</div>
<div>${daily.title}</div>
<div>${daily.content}</div>
<c:if test="${not empty daily.fileName}">
    <img src="assets/upload/${daily.fileName}"/>
</c:if>
<br>
<button onclick="location.href='dailyModify.board?idx=${daily.idx}&id=${daily.id}'">수정</button>
<button onclick="location.href='dailyDelete.board?idx=${daily.idx}'">삭제</button>
<button onclick="location.href='dailyReply.board?idx=${daily.idx}&cp=${cpage}&ps=${pagesize}'">답글</button>
<hr>
<h1>댓글 리스트</h1>
<form name="dailyComment" method="post" id="dailyComment">
    <div class="commentList">
        등록된 댓글이 없습니다
    </div>
    <input type="hidden" name="idx" id="idx" value="${daily.idx}">
    <input type="hidden" name="writerid" id="writerId" value="${daily.id}">
    <h3>댓글</h3>
    <input type="text" name="commentid" id="commentId">
    <textarea name="commentcontent" id="commentContent" rows="2" cols="50"></textarea>
    <input type="button" id="commentWriteBtn" value="등록">
</form>


</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/dailyComment.js"></script>
</html>

