<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mypage.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link href="https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
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
        <div class="board-logo">
            <h3>공지사항</h3>
        </div>
        <div class="post-main">
            <h3>작성한글</h3>
        </div>
        <section id="post-group">
            <section class="post-wrap">
                <article class="post">
                    <div class="post-photo">
                        <img alt="사진이름" src="/assets/images/logo.png">
                    </div>
                    <div class="post-desc">
                        <h2 class="post-title">글제목</h2>
                        <div class="post-price">50,000원</div>
                        <div class="post-content">내용</div>
                    </div>
                </article>
                <article class="post">
                    <div class="post-photo">
                        <img alt="사진이름" src="/assets/images/logo.png">
                    </div>
                    <div class="post-desc">
                        <h2 class="post-title">글제목</h2>
                        <div class="post-price">50,000원</div>
                        <div class="post-content">내용</div>
                    </div>
                </article>
                <article class="post">
                    <div class="post-photo">
                        <img alt="사진이름" src="/assets/images/logo.png">
                    </div>
                    <div class="post-desc">
                        <h2 class="post-title">글제목</h2>
                        <div class="post-price">50,000원</div>
                        <div class="post-content">내용</div>
                    </div>
                </article>
            </section>
        </section>
        <div class="post-main">
            <h3>정보수정</h3>
        </div>
        <div class="form-group">
            <form>
                <label>아이디</label>
                <input type="text">
            </form>
            <form>
                <label>패스워드</label>
                <input type="password">
            </form>
            <form>
                <label>주소</label>
                <input type="text">
            </form>
            <form>
                <label>생년월일</label>
                <input placeholder="&nbsp;8자리를 입력하세요 예)19990101" type="text">
            </form>
            <div class="edit-btn">
                <button type="submit" class="button">정보수정</button>
                <button type="button" class="button">회원탈퇴</button>
            </div>
        </div>


    </div>
</div>
<%--weather--%>
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

<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
