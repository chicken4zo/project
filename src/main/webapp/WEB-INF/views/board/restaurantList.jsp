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
<c:set var="totalboardcount" value="${requestScope.totalBoardCount}"/>
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
        <%--dropdown--%>
        <jsp:include page="../../include/boardMenu.jsp"/>

        <!-- weather -->
        <jsp:include page="../../include/weather.jsp"/>



        <!-- forEach()  목록 출력하기  -->
        <%--            <c:forEach var="restaurant" items="${list}">--%>
        <%--                <tr onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">--%>
        <%--                    <td align="center">${restaurant.idx}</td>--%>
        <%--                    <td align="left">--%>
        <%--                        <a href="RestaurantContent.board?idx=${restaurant.idx}&id=${restaurant.id}&cp=${cpage}&ps=${pagesize}">--%>

                    <!--content-->
                    <div class="board-logo">
                        <h3>맛있구마</h3>
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
                                            <c:when test="${not empty list}">
                                                <c:forEach var="restaurant" items="${list}">
                                                    <tr>
                                                        <td>${restaurant.idx}</td>
                                                        <td>
                                                            <a href="RestaurantContent.board?idx=${restaurant.idx}&cp=${cpage}&ps=${pagesize}">
                                                                <c:choose>
                                                                    <c:when test="${restaurant.title != null && fn:length(restaurant.title) > 10}">
                                                                        ${fn:substring(restaurant.title,0,10)}...
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${restaurant.title}
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </a>
                                                            <c:if test="${not empty restaurant.fileName}">
                                                                <i class="far fa-image" style="margin-left: 5px"></i>
                                                            </c:if>
                                                        </td>
                                                        <td>${restaurant.id}</td>
                                                        <td>${restaurant.address}</td>

                                                            <%--    <c:if test="${lost.fileName!='empty'}">--%>
                                                            <%--        <img src="assets/upload/${lost.fileName}"/>--%>
                                                            <%--    </c:if>--%>
                                                        <td>${restaurant.writeDate}</td>
                                                        <td>${restaurant.hit}</td>
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
                    <button class="write-btn" onclick="location.href='RestaurantWrite.board'">글쓰기</button>
                    <form name="list">
                        <!-- 한 페이지안에 보여줄 게시글 건수 -->
                        <select name="ps" onchange="submit()"
                                style="color: rgb(73,80,87); border-radius: 15%; border: 1px solid #858585; font-size: 0.7rem;">
                            <c:forEach var="i" begin="5" end="20" step="5">
                                <c:choose>
                                    <c:when test="${pagesize == i}">
                                        <option value="${i}" selected>${i}건</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${i}">${i}건</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </form>

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"
        integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/"
        crossorigin="anonymous"></script>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"--%>
<%--        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"--%>
<%--        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"--%>
<%--        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>

<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>--%>
<%--<script src="https://code.jquery.com/jquery-3.3.1.min.js"--%>
<%--        integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"--%>
<%--        crossorigin="anonymous"></script>--%>

<!--image js-->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css"></script>

<script src="${pageContext.request.contextPath}/assets/js/productPetContent.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js?ver=2"></script>
</html>