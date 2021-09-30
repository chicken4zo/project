<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="productBoardList" value="${requestScope.productBoardList}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagecount" value="${requestScope.pagecount}"/>
<c:set var="totalBoardCount" value="${requestScope.totalBoardCount}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="productBoard" items="${productBoardList}">
    <div>글번호 : ${productBoard.idx}</div>
    <div>글쓴이 : ${productBoard.id}</div>
    <div>주소 : ${productBoard.address}</div>

    <a href="productContent.board?idx=${productBoard.idx}&id=${productBoard.id}&cp=${cpage}&ps=${pagesize}">
        제목 : ${productBoard.title}
    </a>
    <div>내용 : ${productBoard.content}</div>
    <div>작성날짜 : ${productBoard.writeDate}</div>

    이미지 : <img src="/assets/upload/${productBoard.fileName1}" alt="${productBoard.fileName1}"/>


    <hr>
</c:forEach>
</body>
</html>
