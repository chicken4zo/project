<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lostlist" value="${requestScope.lostList}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagecount" value="${requestScope.pagecount}"/>
<c:set var="pager" value="${requestScope.pager}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="lost" items="${lostlist}">
    <div>${lost.idx}</div>
    <div>${lost.id}</div>
    <div>${lost.address}</div>
    <c:forEach var="i" begin="1" end="${lost.depth}" step="1">
        &nbsp;&nbsp;&nbsp;
    </c:forEach>
    <c:if test="${lost.depth>0}">
        ㄴ
    </c:if>
    <a href="lostContent.board?idx=${lost.idx}&id=${lost.id}&cp=${cpage}&ps=${pagesize}">
            ${lost.title}
    </a>
    <div>${lost.content}</div>
    <div>${lost.fileName}</div>
    <div>${lost.filePath}</div>
    <c:if test="${lost.fileName!='empty'}">
        <img src="assets/upload/${lost.fileName}"/>
    </c:if>

    <hr>
</c:forEach>


</body>
</html>
