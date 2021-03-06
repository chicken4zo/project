<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="petBoardList" value="${requestScope.petBoardList}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagecount" value="${requestScope.pagecount}"/>
<c:set var="pager" value="${requestScope.pager}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css?after">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/petList.css?after">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="/WEB-INF/include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <div style="display: flex;justify-content: space-between;align-items: center">
            <h3 class="pet-logo">귀엽구마</h3>
            <%--dropdown--%>
            <jsp:include page="../../include/boardMenu.jsp"/>
        </div>

        <section class="cards">
            <c:forEach var="pet" items="${petBoardList}">
                <c:choose>
                    <c:when test="${pet.title=='deleted'}">
                    </c:when>
                    <c:otherwise>
                        <article class="card card--1">
                            <div class="card__info-hover">
                                <i class="fas fa-eye"
                                   style="color:white; font-size: 0.9rem; margin-right: 4px"></i><span
                                    class="hit-size">${pet.hit}</span>
                                <div class="card__hit-info">
                                    <!--                ><i class="far fa-eye" style="color:rgba(0,0,0,0.5)"</i>-->
                                </div>
                            </div>
                            <div class="card__img">
                                <img src="${pageContext.request.contextPath}/assets/upload/${pet.fileName1}" alt="pet">
                            </div>
                            <a href="petContent.board?idx=${pet.idx}&id=${pet.id}&cp=${cpage}&ps=${pagesize}"
                               class="card_link">
                                <div class="card__img--hover"></div>
                            </a>
                            <div class="card__info">
                                <button type="button" class="card__btn">${fn:substring(pet.address,0,3)}</button>
                                <h3 class="card__title">${pet.title}</h3>
                                <span class="card__by">${pet.id}</span>
                            </div>
                        </article>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </section>
        <button class="write-btn" onclick="location.href='${pageContext.request.contextPath}/petForm.board'">글쓰기
        </button>
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                ${pager}
            </ul>
        </nav>
    </div>


</div>
<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
<!--bootstrp js-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
