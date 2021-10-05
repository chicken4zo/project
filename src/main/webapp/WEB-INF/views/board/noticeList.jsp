<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagecount" value="${requestScope.pagecount}"/>
<c:set var="list" value="${requestScope.list}"/>
<c:set var="totalnoticecount" value="${requestScope.totalNoticeCount}"/>
<c:set var="pager" value="${requestScope.pager}"/>
<c:set var="id" value="${sessionScope.id}"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css?after">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/boardList.css?after">
    <!-- favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <!-- bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- fontawesome  -->
    <script src="https://kit.fontawesome.com/a959489452.js" crossorigin="anonymous"></script>
    <!--font-->
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <!--weather icon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/weather-icons/1.2/css/weather-icons.min.css">
</head>
<body>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>
        <!-- weather -->
        <jsp:include page="../../include/weather.jsp"/>


        <!--content-->
        <div class="board-logo">
            <h3>공지사항</h3>
        </div>
        <div class="col-md-12">
            <div class="card card-plain">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead class="">
                            <th>
                                번호
                            </th>
                            <th>
                                제목
                            </th>
                            <th>
                                작성일
                            </th>
                            <th>
                                조회수
                            </th>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${not empty list}">
                                    <c:forEach var="notice" items="${list}">
                                        <tr>
                                            <td>${notice.idx}</td>
                                            <td>
                                                <c:choose>
                                                <c:when test="${notice.title=='deleted'}">
                                                    삭제된 게시글 입니다
                                                </c:when>
                                                    <c:otherwise>
                                                    <a href="NoticeContent.board?idx=${notice.idx}&id=${notice.id}&cp=${cpage}&ps=${pagesize}">
                                                        <c:choose>
                                                            <c:when test="${notice.title != null && fn:length(notice.title) > 10}">
                                                                ${fn:substring(notice.title,0,10)}...
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${notice.title}
                                                            </c:otherwise>
                                                        </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </td>
                                            <td>${notice.writeDate}</td>
                                            <td>${notice.hit}</td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <td>등록된 글이 없습니다</td>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${id eq 'admin'}">
            <button class="write-btn" onclick="location.href='NoticeWrite.board'">글쓰기</button>
        </c:if>
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                ${pager}
            </ul>
        </nav>
    </div>
</div>
<jsp:include page="../../include/footer.jsp"/>


</body>
<!-- bootstrap4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

<!--image js-->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css"></script>

<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
