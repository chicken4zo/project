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
<c:choose>
    <c:when test="${not empty lostlist}">
        <c:forEach var="lost" items="${lostlist}">
            <p>${lost.idx} / ${lost.id} / ${lost.address}
                <c:forEach var="i" begin="1" end="${lost.depth}" step="1">
                    &nbsp;&nbsp;&nbsp;
                </c:forEach>
                <c:if test="${lost.depth>0}">
                    RE:
                </c:if>
                <a href="lostContent.board?idx=${lost.idx}&id=${lost.id}&cp=${cpage}&ps=${pagesize}">
                        ${lost.title}
                </a>
            </p>
            <%--    <c:if test="${lost.fileName!='empty'}">--%>
            <%--        <img src="assets/upload/${lost.fileName}"/>--%>
            <%--    </c:if>--%>
            <hr>
        </c:forEach>
    </c:when>
    <c:otherwise> 등록된 글이 없습니다</c:otherwise>
</c:choose>
${pager}


</body>
</html>
