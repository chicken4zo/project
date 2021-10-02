<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>고민하지말구, 고구마켓</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/productPetContent.css">
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
<c:set var="idx" value="${requestScope.idx}"/>
<c:set var="board" value="${requestScope.board}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="commentList" value="${requestScope.commentList}"/>
<c:set var="id" value="${sessionScope.id}"/>

<div id="body_wrap">
  <div class="wrapper">
    <!--header-->
    <jsp:include page="/WEB-INF/include/top.jsp"/>

    <%--weather--%>
    <jsp:include page="/WEB-INF/include/weather.jsp"/>

    <!--content-->
    <div class="title_container">맛있구마</div>
    <div class="container-fluid py-4">
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-body">
              <!--product details-->
              <c:choose>

                <c:when test="${not empty board.fileName}">
                  <div class="row">
                    <!--image space-->
                    <!-- Gallery -->
                    <div id="js-gallery" class="gallery col-xl-5 col-lg-6 text-center">

                      <!--Gallery Hero-->
                      <div class="gallery__hero parent">

                        <img class="slide w-100 border-radius-lg shadow-lg mx-auto"
                             src="assets/upload/${board.fileName}">
                      </div>
                      <!--Gallery Hero-->

                    </div>

                    <!--.gallery-->
                    <!-- Gallery -->
                    <div class="col-lg-5 mx-auto">
                      <div class="titleContainer">
                        <h5 class="mb-0 mt-3" style="font-size: 1.6rem">${board.title}</h5>
                      </div>
                      <div class="idAddr">
                        <h4 class="mt-lg-0 mt-4 id">${board.id}</h4>
                        <h5 class="mt-lg-0 mt-4 address"><i class="fas fa-map-marker-alt"></i>
                            ${board.address}
                        </h5>
                      </div>
                      <div class="detail_bar"></div>
                      <ul class="view_icon">
                        <li><i class="fas fa-eye"></i><span>${board.hit}</span></li>
                        <li><i class="far fa-calendar-alt"></i><span>${board.writeDate}</span>
                        </li>
                      </ul>
                      <div class="content">
                          ${board.content}
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
                        <h5 class="mb-0 mt-3">${board.title}</h5>
                      </div>
                      <div class="idAddr">
                        <h4 class="mt-lg-0 mt-4 id">${board.id}</h4>
                        <h5 class="mt-lg-0 mt-4 address"><i class="fas fa-map-marker-alt"></i>
                            ${board.address}
                        </h5>
                      </div>
                      <div class="detail_bar" style="width: 100%"></div>
                      <ul class="view_icon">
                        <li><i class="fas fa-eye"></i><span>${board.hit}</span></li>
                        <li><i class="far fa-calendar-alt"></i><span>${board.writeDate}</span>
                        </li>
                      </ul>
                      <div class="content">
                          ${board.content}
                      </div>

                    </div>
                  </div>

                </c:otherwise>
              </c:choose>
              <div class="row mt-5">
                <div class="col-12">
                  <div class="ms-3 button_container">
                    <%--                                        <c:set value="${sessionScope.id}" var="id"/>--%>
                    <c:if test="${board.id eq id}">
                      <button type="button" class="btn btn-warning btn-sm"
                              onclick="location.href='RestaurantEdit.board?idx=${idx}&cp=${cpage}&ps=${pagesize}'">
                        <i class="fas fa-pen"></i>
                        <span>수정</span>
                      </button>
                      <button type="button" class="btn btn-danger btn-sm"
                              onclick="location.href='RestaurantDelete.board?idx=${idx}&cp=${cpage}&ps=${pagesize}'">
                        <i class="fas fa-trash-alt"></i>
                        <span>삭제</span>
                      </button>
                    </c:if>
                    <button type="button" class="btn btn-primary btn-sm"
                            onclick="location.href='RestaurantList.board?cp=${cpage}&ps=${pagesize}'">
                      <i class="fas fa-list-ul"></i>
                      <span>목록</span>
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
                      <h2>ID</h2>
                      <p>내용 The most beautiful curves of this swivel stool adds an elegant touch
                        to
                        any environment Memory swivel seat returns to original seat position
                        Comfortable integrated layered chair seat cushion design Fully
                        assembled! No
                        assembly required</p>
                      <h4>2021. 09. 30</h4>
                      <input type="button" value="삭제" onclick="">

                    </div>
                  </div>
                  <!-- replylist_wrap END -->

                  <!--reply write-->
                  <form name="lostComment" method="POST" id="lostComment">
                    <div class="commentWrite_Wrap">
                      <input type="hidden" name="idx" id="idx" value="${idx}">
                      <input type="hidden" name="writerid" id="id" value="${board.id}">
                      <h2>${id}</h2>
                      <input type="hidden" name="commentid" id="commentId" value="${id}">
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

<script src="${pageContext.request.contextPath}/assets/js/lostComment.js?ver=1"></script>
<script src="${pageContext.request.contextPath}/assets/js/productPetContent.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
