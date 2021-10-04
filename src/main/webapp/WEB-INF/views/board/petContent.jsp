<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pet" value="${requestScope.pet}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="commentList" value="${requestScope.commentList}"/>
<c:set var="id" value="${sessionScope.id}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/productPetContent.css?after">
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
        <div class="container-fluid py-4">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="mb-4">Details</h5>
                            <input hidden id="boardName" value="pet">
                            <!--product details-->
                            <div class="row">
                                <!--image space-->
                                <!-- Gallery -->
                                <div id="js-gallery" class="gallery col-xl-5 col-lg-6 text-center">

                                    <!--Gallery Hero-->
                                    <div class="gallery__hero parent">

                                        <img class="slide w-100 border-radius-lg shadow-lg mx-auto"
                                             src="${pageContext.request.contextPath}/assets/upload/${pet.fileName1}">
                                        <!--                                        <img class="slide" src="https://public-619e3.firebaseapp.com/Product-Gallery/products/thumb/product-01_view-02.jpg">-->
                                        <!--                                        <img class="slide" src="https://public-619e3.firebaseapp.com/Product-Gallery/products/thumb/product-01_view-03.jpg">-->
                                    </div>
                                    <!--Gallery Hero-->

                                    <!--Gallery Thumbs-->
                                    <div class="gallery__thumbs">
                                        <a href="${pageContext.request.contextPath}/assets/upload/${pet.fileName1}"
                                           data-gallery="thumb" class="is-active">
                                            <img src="${pageContext.request.contextPath}/assets/upload/${pet.fileName1}">
                                        </a>
                                        <a href="${pageContext.request.contextPath}/assets/upload/${pet.fileName2}"
                                           data-gallery="thumb">
                                            <img src="${pageContext.request.contextPath}/assets/upload/${pet.fileName2}">
                                        </a>
                                        <a href="${pageContext.request.contextPath}/assets/upload/${pet.fileName3}"
                                           data-gallery="thumb">
                                            <img src="${pageContext.request.contextPath}/assets/upload/${pet.fileName3}">
                                        </a>
                                    </div>
                                    <!--Gallery Thumbs-->

                                </div><!--.gallery-->
                                <!-- Gallery -->
                                <div class="col-lg-5 mx-auto">
                                    <%--<h4 class="mt-lg-0 mt-4 id">${pet.id}</h4>
                                    <h5 class="mt-lg-0 mt-4 address"><i class="fas fa-map-marker-alt"></i>${pet.address}
                                    </h5>
                                    <div class="detail_bar"></div>

                                    <ul class="view_icon">
                                        <li><i class="fas fa-eye"></i><span>${pet.hit}</span></li>
                                        <li><i class="far fa-calendar-alt"></i><span>${pet.writeDate}</span></li>
                                    </ul>
                                    <h5 class="mb-0 mt-3">${pet.title}</h5>
                                    <div class="content">
                                        ${pet.content}
                                    </div>--%>
                                    <div class="titleContainer">
                                        <h5 class="mb-0 mt-3" style="font-size: 1.6rem">${pet.title}</h5>
                                    </div>
                                    <div class="idAddr">
                                        <h4 class="mt-lg-0 mt-4 id">${pet.id}</h4>
                                        <h5 class="mt-lg-0 mt-4 address"><i class="fas fa-map-marker-alt"></i>
                                            ${pet.address}
                                        </h5>
                                    </div>
                                    <div class="detail_bar"></div>
                                    <ul class="view_icon">
                                        <li><i class="fas fa-eye"></i><span>${pet.hit}</span></li>
                                        <li><i class="far fa-calendar-alt"></i><span>${pet.writeDate}</span>
                                        </li>
                                    </ul>
                                    <div class="content">
                                        ${pet.content}
                                    </div>

                                </div>
                            </div>
                            <div class="row mt-5">
                                <div class="col-12">
                                    <!--                                    <div class="bottom_bar"></div>-->
                                    <div class="ms-3 button_container">
                                        <button type="button" class="btn btn-warning btn-sm"
                                                onclick="location.href='petModify.board?idx=${pet.idx}&id=${pet.id}'">
                                            <i class="fas fa-pen"></i>
                                            <span>수정</span>
                                        </button>
                                        <button type="button" class="btn btn-danger btn-sm"
                                                onclick="location.href='petDelete.board?idx=${pet.idx}&id=${pet.id}'">
                                            <i class="fas fa-trash-alt"></i>
                                            <span>삭제</span>
                                        </button>
                                        <button type="button" class="btn btn-primary btn-sm"
                                                onclick="location.href='petList.board?idx=${pet.idx}&id=${pet.id}'">
                                            <i class="fas fa-list-ul"></i>
                                            <span>목록</span>
                                        </button>

                                    </div>
                                    <!--댓글-->
                                    <div class="bottom_bar"></div>
                                    <div>
                                        <i class="far fa-comment-dots"></i>
                                        <span>댓글 </span>
                                    </div>
                                    <!--댓글 리스트-->
                                    <div class="commentList_wrap">
                                        <div class="commentList">
                                            <h2></h2>
                                            <p></p>
                                            <h4></h4>
                                            <input type="button" value="삭제" onclick="">
                                        </div>
                                        <!--지우기-->
                                    </div>
                                    <!-- replylist_wrap END -->

                                    <!--reply write-->
                                    <form name="petComment" method="POST" id="petComment">
                                        <div class="commentWrite_Wrap">
                                            <input type="hidden" name="idx" id="idx" value="${pet.idx}">
                                            <input type="hidden" name="writerid" id="writerId" value="${pet.id}">
                                            <h2>${id}</h2>
                                            <input type="text" name="commentid" id="commentId">
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

<!--image js-->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css"></script>

<script src="${pageContext.request.contextPath}/assets/js/comment.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/productPetContent.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
