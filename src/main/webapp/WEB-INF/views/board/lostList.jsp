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
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/boardList.css">
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
        <article class="widget">
            <div class="weatherIcon">
                <div class="date">
                    <h4 class="month" id="month"></h4>
                    <h5 class="day" id="day"></h5>
                </div>
                <!--        <i class="wi wi-night-fog"></i>-->
                <h1 id="temp" class="temperature">25&deg;</h1>
            </div>
            <div class="weatherData">
                <h2 class="description">오늘,<br> 우리 동네는</h2>
                <h3 class="city" id="comment">KR, Seochodong</h3>
            </div>
        </article>

        <!--content-->
        <div class="board-logo">
            <h3>분실·실종</h3>
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
                                작성자
                            </th>
                            <th>
                                동네
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
                                <c:when test="${not empty lostlist}">
                                    <c:forEach var="lost" items="${lostlist}">
                                        <tr>
                                            <td>${lost.idx}</td>
                                            <td>
                                                <c:forEach var="i" begin="1" end="${lost.depth}" step="1">
                                                    &nbsp;&nbsp;&nbsp;
                                                </c:forEach>
                                                <c:if test="${lost.depth>0}">
                                                    RE:
                                                </c:if>
                                                <a href="lostContent.board?idx=${lost.idx}&id=${lost.id}&cp=${cpage}&ps=${pagesize}">
                                                        ${lost.title}
                                                </a>
                                            </td>
                                            <td>${lost.id}</td>
                                            <td>${lost.address}</td>

                                                <%--    <c:if test="${lost.fileName!='empty'}">--%>
                                                <%--        <img src="assets/upload/${lost.fileName}"/>--%>
                                                <%--    </c:if>--%>
                                            <td>${lost.writeDate}</td>
                                            <td>${lost.hit}</td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <td>등록된 글이 없습니다</td>
                                </c:otherwise>
                            </c:choose>
                            <%--                            <tr>--%>
                            <%--                                <td>--%>
                            <%--                                    1--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Dakota Rice--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Niger--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Oud-Turnhout--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    $36,738--%>
                            <%--                                </td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>--%>
                            <%--                                    2--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Minerva Hooper--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Curaçao--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Sinaai-Waas--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    $23,789--%>
                            <%--                                </td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>--%>
                            <%--                                    3--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Sage Rodriguez--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Netherlands--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Baileux--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    $56,142--%>
                            <%--                                </td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>--%>
                            <%--                                    4--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Philip Chaney--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Korea, South--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Overland Park--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    $38,735--%>
                            <%--                                </td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>--%>
                            <%--                                    5--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Doris Greene--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Malawi--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Feldkirchen in Kärnten--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    $63,542--%>
                            <%--                                </td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>--%>
                            <%--                                    6--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Mason Porter--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Chile--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    Gloucester--%>
                            <%--                                </td>--%>
                            <%--                                <td>--%>
                            <%--                                    $78,615--%>
                            <%--                                </td>--%>
                            <%--                            </tr>--%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <button class="write-btn" onclick="location.href='lostForm.board'">글쓰기</button>
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                ${pager}
                <%--                <li class="page-item disabled">--%>
                <%--                    <a class="page-link" href="#" tabindex="-1"><</a>--%>
                <%--                </li>--%>
                <%--                <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
                <%--                <li class="page-item active">--%>
                <%--                    <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>--%>
                <%--                </li>--%>
                <%--                <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
                <%--                <li class="page-item"><a class="page-link" href="#">4</a></li>--%>
                <%--                <li class="page-item"><a class="page-link" href="#">5</a></li>--%>
                <%--                <li class="page-item">--%>
                <%--                    <a class="page-link" href="#">></a>--%>
                <%--                </li>--%>
            </ul>
        </nav>
    </div>
</div>
<footer>
    <p>Copyright © 2021 chicken 4JO. All rights reserved.</p>
</footer>
<%--<c:choose>--%>
<%--    <c:when test="${not empty lostlist}">--%>
<%--        <c:forEach var="lost" items="${lostlist}">--%>
<%--            <p>${lost.idx} / ${lost.id} / ${lost.address}--%>
<%--                <c:forEach var="i" begin="1" end="${lost.depth}" step="1">--%>
<%--                    &nbsp;&nbsp;&nbsp;--%>
<%--                </c:forEach>--%>
<%--                <c:if test="${lost.depth>0}">--%>
<%--                    RE:--%>
<%--                </c:if>--%>
<%--                <a href="lostContent.board?idx=${lost.idx}&id=${lost.id}&cp=${cpage}&ps=${pagesize}">--%>
<%--                        ${lost.title}--%>
<%--                </a>--%>
<%--            </p>--%>
<%--            &lt;%&ndash;    <c:if test="${lost.fileName!='empty'}">&ndash;%&gt;--%>
<%--            &lt;%&ndash;        <img src="assets/upload/${lost.fileName}"/>&ndash;%&gt;--%>
<%--            &lt;%&ndash;    </c:if>&ndash;%&gt;--%>
<%--            <hr>--%>
<%--        </c:forEach>--%>
<%--    </c:when>--%>
<%--    <c:otherwise> 등록된 글이 없습니다</c:otherwise>--%>
<%--</c:choose>--%>


</body>
<!-- bootstrap4 -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/assets/js/core/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/bootstrap-material-design.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<!--  DataTables.net Plugin, full documentation here: https://datatables.net/  -->
<script src="${pageContext.request.contextPath}/assets/js/plugins/jquery.dataTables.min.js"></script>
<!--bootstrp js-->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<!--image js-->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css"></script>

<script src="${pageContext.request.contextPath}/assets/js/productPetContent.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
