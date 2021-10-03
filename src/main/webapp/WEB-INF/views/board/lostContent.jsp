<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/productPetContent.css?ver=2">
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
<c:set var="lost" value="${requestScope.lost}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="commentList" value="${requestScope.commentList}"/>
<c:set var="loginId" value="${sessionScope.id}"/>

<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="/WEB-INF/include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <div class="title_container">분실·실종</div>
        <input hidden id="boardName" value="lost">
        <div class="container-fluid py-4">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <!--product details-->
                            <c:choose>
                                <c:when test="${not empty lost.fileName}">
                                    <div class="row">
                                        <!--image space-->
                                        <!-- Gallery -->
                                        <div id="js-gallery" class="gallery col-xl-5 col-lg-6 text-center">

                                            <!--Gallery Hero-->
                                            <div class="gallery__hero parent">

                                                <img class="slide w-100 border-radius-lg shadow-lg mx-auto"
                                                     src="assets/upload/${lost.fileName}">
                                            </div>
                                            <!--Gallery Hero-->

                                            <!--Gallery Thumbs-->

                                                <%--                                    <div class="gallery__thumbs">--%>
                                                <%--                                        --%>
                                                <%--                                        <a href="https://public-619e3.firebaseapp.com/Product-Gallery/products/normal/product-01_view-01.jpg"--%>
                                                <%--                                           data-gallery="thumb" class="is-active">--%>
                                                <%--                                            <img src="https://public-619e3.firebaseapp.com/Product-Gallery/products/thumb/product-01_view-01.jpg">--%>
                                                <%--                                        </a>--%>
                                                <%--                                        <a href="https://public-619e3.firebaseapp.com/Product-Gallery/products/normal/product-01_view-02.jpg"--%>
                                                <%--                                           data-gallery="thumb">--%>
                                                <%--                                            <img src="https://public-619e3.firebaseapp.com/Product-Gallery/products/thumb/product-01_view-02.jpg">--%>
                                                <%--                                        </a>--%>
                                                <%--                                        <a href="https://public-619e3.firebaseapp.com/Product-Gallery/products/normal/product-01_view-03.jpg"--%>
                                                <%--                                           data-gallery="thumb">--%>
                                                <%--                                            <img src="https://public-619e3.firebaseapp.com/Product-Gallery/products/thumb/product-01_view-03.jpg">--%>
                                                <%--                                        </a>--%>
                                                <%--                                    </div>--%>
                                            <!--Gallery Thumbs-->

                                        </div>

                                        <!--.gallery-->
                                        <!-- Gallery -->
                                        <div class="col-lg-5 mx-auto">
                                            <div class="titleContainer">
                                                <h5 class="mb-0 mt-3" style="font-size: 1.6rem">${lost.title}</h5>
                                            </div>
                                            <div class="idAddr">
                                                <h4 class="mt-lg-0 mt-4 id">${lost.id}</h4>
                                                <h5 class="mt-lg-0 mt-4 address"><i class="fas fa-map-marker-alt"></i>
                                                        ${lost.address}
                                                </h5>
                                            </div>
                                            <div class="detail_bar"></div>
                                            <ul class="view_icon">
                                                <li><i class="fas fa-eye"></i><span>${lost.hit}</span></li>
                                                <li><i class="far fa-calendar-alt"></i><span>${lost.writeDate}</span>
                                                </li>
                                            </ul>
                                            <div class="content">
                                                    ${lost.content}
                                            </div>

                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="row">


                                        <!--.gallery-->
                                        <!-- Gallery -->
                                        <div class="col-12" style="padding: 0px 32px">
                                            <div class="titleContainer">
                                                <h5 class="mb-0 mt-3">${lost.title}</h5>
                                            </div>
                                            <div class="idAddr">
                                                <h4 class="mt-lg-0 mt-4 id">${lost.id}</h4>
                                                <h5 class="mt-lg-0 mt-4 address"><i class="fas fa-map-marker-alt"></i>
                                                        ${lost.address}
                                                </h5>
                                            </div>
                                            <div class="detail_bar" style="width: 100%"></div>
                                            <ul class="view_icon">
                                                <li><i class="fas fa-eye"></i><span>${lost.hit}</span></li>
                                                <li><i class="far fa-calendar-alt"></i><span>${lost.writeDate}</span>
                                                </li>
                                            </ul>
                                            <div class="content">
                                                    ${lost.content}
                                            </div>

                                        </div>
                                    </div>

                                </c:otherwise>
                            </c:choose>
                            <div class="row mt-5">
                                <div class="col-12">
                                    <div class="ms-3 button_container">
                                        <%--                                        <c:set value="${sessionScope.id}" var="id"/>--%>
                                        <c:if test="${lost.id eq loginId}">
                                            <button type="button" class="btn btn-warning btn-sm"
                                                    onclick="location.href='lostModify.board?idx=${lost.idx}&id=${lost.id}'">
                                                <i class="fas fa-pen"></i>
                                                <span>수정</span>
                                            </button>
                                            <button type="button" class="btn btn-danger btn-sm"
                                                    onclick="location.href='lostDelete.board?idx=${lost.idx}'">
                                                <i class="fas fa-trash-alt"></i>
                                                <span>삭제</span>
                                            </button>
                                        </c:if>
                                        <button type="button" class="btn btn-primary btn-sm"
                                                onclick="location.href='lostList.board'">
                                            <i class="fas fa-list-ul"></i>
                                            <span>목록</span>
                                        </button>
                                        <button type="button" class="btn btn-info btn-sm"
                                                onclick="location.href='lostReply.board?idx=${lost.idx}&cp=${cpage}&ps=${pagesize}'">
                                            <i class="fab fa-replyd"></i>
                                            <span>답글달기</span>
                                        </button>

                                    </div>
                                    <!--댓글-->
                                    <div class="bottom_bar"></div>
                                    <div>
                                        <i class="far fa-comment-dots"></i>
                                        <span>댓글</span>
                                    </div>
                                    <!--댓글 리스트-->
                                    <div class="commentList_wrap">
                                        <div class="commentList">
                                        </div>
                                    </div>
                                    <!-- replylist_wrap END -->

                                    <!--reply write-->
                                    <form name="lostComment" method="POST" id="lostComment">
                                        <div class="commentWrite_Wrap">
                                            <input type="hidden" name="idx" id="idx" value="${lost.idx}">
                                            <input type="hidden" name="writerid" id="writerId" value="${lost.id}">
                                            <input type="hidden" value="${loginId}" id="loginId">
                                            <h2>${loginId}</h2>
                                            <input type="hidden" name="commentid" id="commentId" value="${loginId}">
                                            <textarea name="content" id="commentContent" placeholder="댓글을 남겨보세요"
                                                      class="comment_inbox" rows="4"
                                                      cols="140"></textarea>
                                            <input type="button" value="등록" id="commentWriteBtn"> <!-- onclick 유효성체크 -->

                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<!--image js-->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css"></script>

<script src="${pageContext.request.contextPath}/assets/js/comment.js?ver=2"></script>
<script src="${pageContext.request.contextPath}/assets/js/productPetContent.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
