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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/petList.css">
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
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="/WEB-INF/include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <div class="petlogo">
            <img src="/assets/images/petlogo.png" width="15%" height="20%">
        </div>


        <section class="cards">
            <c:forEach var="pet" items="${petBoardList}">
                <article class="card card--1">
                    <div class="card__info-hover">
                        <i class="far fa-heart" style="color:#dadada"></i><span class="hit-size">${pet.hit}</span>
                        <div class="card__hit-info">
                            <!--                ><i class="far fa-eye" style="color:rgba(0,0,0,0.5)"</i>-->
                            <i class="far fa-comment" style="color:#dadada"></i><span
                                class="hit-size"></span>
                        </div>
                    </div>
                    <div class="card__img"></div>
                    <a href="petContent.board?idx=${pet.idx}&id=${pet.id}&cp=${cpage}&ps=${pagesize}" class="card_link">
                        <div class="card__img--hover"></div>
                    </a>
                    <div class="card__info">
                        <button type="button" class="card__btn">${pet.address}</button>
                        <h3 class="card__title">${pet.title}</h3>
                        <span class="card__by">${pet.id}</span>
                    </div>
                </article>
            </c:forEach>
            <button class="write-btn" onclick="location.href='${pageContext.request.contextPath}/petForm.board'">글쓰기
            </button>

            <%--<article class="card card--2">
                <div class="card__info-hover">
                    <i class="far fa-heart" style="color:#dadada"></i>
                    <div class="card__hit-info">
                        <i class="far fa-comment" style="color:#dadada"></i>
                    </div>
                </div>
                <div class="card__img"></div>
                <a href="#" class="card_link">
                    <div class="card__img--hover"></div>
                </a>
                <div class="card__info">
                    <button type="button" class="card__btn">강아지</button>
                    <h3 class="card__title">제목</h3>
                    <span class="card__by">아이디</span>
                </div>
            </article>

            <article class="card card--3">
                <div class="card__info-hover">
                    <i class="far fa-heart" style="color:#dadada"></i>
                    <div class="card__hit-info">
                        <i class="far fa-comment" style="color:#dadada"></i>
                    </div>
                </div>
                <div class="card__img"></div>
                <a href="#" class="card_link">
                    <div class="card__img--hover"></div>
                </a>
                <div class="card__info">
                    <button type="button" class="card__btn">고양이</button>
                    <h3 class="card__title">제목</h3>
                    <span class="card__by">아이디</span>
                </div>
            </article>

            <article class="card card--4">
                <div class="card__info-hover">
                    <i class="far fa-heart" style="color:#dadada"></i>
                    <div class="card__hit-info">
                        <i class="far fa-comment" style="color:#dadada"></i>
                    </div>
                </div>
                <div class="card__img"></div>
                <a href="#" class="card_link">
                    <div class="card__img--hover"></div>
                </a>
                <div class="card__info">
                    <button type="button" class="card__btn">고양이</button>
                    <h3 class="card__title">제목</h3>
                    <span class="card__by">아이디</span>
                </div>
            </article>

            <article class="card card--5">
                <div class="card__info-hover">
                    <i class="far fa-heart" style="color:#dadada"></i>
                    <div class="card__hit-info">
                        <i class="far fa-comment" style="color:#dadada"></i>
                    </div>
                </div>
                <div class="card__img"></div>
                <a href="#" class="card_link">
                    <div class="card__img--hover"></div>
                </a>
                <div class="card__info">
                    <button type="button" class="card__btn">강아지</button>
                    <h3 class="card__title">제목</h3>
                    <span class="card__by">아이디</span>
                </div>
            </article>

            <article class="card card--6">
                <div class="card__info-hover">
                    <i class="far fa-heart" style="color:#dadada"></i>
                    <div class="card__hit-info">
                        <i class="far fa-comment" style="color:#dadada"></i>
                    </div>
                </div>
                <div class="card__img"></div>
                <a href="#" class="card_link">
                    <div class="card__img--hover"></div>
                </a>
                <div class="card__info">
                    <button type="button" class="card__btn">강아지</button>
                    <h3 class="card__title">제목</h3>
                    <span class="card__by">아이디</span>
                </div>
            </article>--%>
        </section>

        <%-- <nav aria-label="...">
             <ul class="pagination justify-content-center">
                 <li class="page-item disabled">
                     <a class="page-link" href="#" tabindex="-1"><</a>
                 </li>
                 <li class="page-item"><a class="page-link" href="#">1</a></li>
                 <li class="page-item active">
                     <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                 </li>
                 <li class="page-item"><a class="page-link" href="#">3</a></li>
                 <li class="page-item"><a class="page-link" href="#">4</a></li>
                 <li class="page-item"><a class="page-link" href="#">5</a></li>
                 <li class="page-item">
                     <a class="page-link" href="#">></a>
                 </li>
             </ul>
         </nav>--%>
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
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
