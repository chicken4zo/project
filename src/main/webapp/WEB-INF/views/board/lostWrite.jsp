<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/productWrite.css">
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
    <style>
        .product-photo {
            background-image: url("${pageContext.request.contextPath}/assets/images/upload.png");
            background-size: 20%;
            background-position: center center;
            background-repeat: no-repeat;
        }
    </style>

    <script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
</head>
<body>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="lost" value="${requestScope.lost}"/>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>
        <%--weather--%>
        <jsp:include page="../../include/weather.jsp"/>

        <%--content--%>
        <div class="title">분실·실종</div>
        <form name="lost" action="lostWrite.board" method="post" enctype="multipart/form-data">
            <div class="product-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">아이디</div>
                            <input type="text" name="id" value="${loginId}" readonly/>
                        </li>
                        <%--<li class="info-title">
                            <div class="info-detail">이미지</div>

                            <div class="product-photo">
                                <input type="file" id="file" name="file" accept="image/jpeg, image/png, image/jpg">
                            </div>
                        </li>--%>
                        <li class="info-title">
                            <div class="info-detail">이미지</div>

                            <div class="product-photo" id="${lost.fileName}">
                                <input type="file" id="file" name="file" accept="image/jpeg, image/png, image/jpg">
                            </div>

                            <div class="file"></div>
                            <button id="delete" class="del-btn">삭제</button>
                            <input hidden class="originalfile" name="originalfile" value="${lost.fileName}">
                        </li>
                        <li class="info-title">
                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="50자 내로 입력해주세요." id="writeTitle"
                                   class="write-title" required>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">
                                내용
                            </div>
                            <textarea placeholder="500자 내로 입력해주세요" name="content" id="editor"></textarea>
                            <script>
                                //CKEditor5를 생성할 textarea 지정
                                ClassicEditor
                                    .create(document.querySelector('#editor'), {
                                        placeholder: '500자 내로 입력해주세요',
                                        removePlugins: ['ImageUpload']
                                    })
                                    .then(newEditor => {
                                        editor = newEditor;
                                    })
                                    .catch(error => {
                                        console.error(error);
                                    });
                            </script>
                        </li>
                    </ul>
                </section>
                <div class="product-btn">
                    <input id="writeBtn" type="submit" class="write-btn" value="등록하기">
                </div>
                <%--    <img src="" id="preview"/>--%>
                <%--    <input type="text" name="id">--%>
                <%--    <input type="text" name="title">--%>
                <%--    <input type="text" name="content">--%>
                <%--    <input type="file" name="file" id="file">--%>
                <%--    <input type="submit" value="전송">--%>
        </form>
    </div>
</div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/fileChange.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

</html>
