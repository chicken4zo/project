<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/productWrite.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link href="https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <!-- bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
<c:set var="board" value="${requestScope.board}"/>
<c:set var="idx" value="${requestScope.idx}"/>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>
        <div class="board-logo">
            <h3>일상</h3>
        </div>
        <form action="RestaurantEditOk.board?title=${board.title}" method="post" enctype="multipart/form-data">
            <input type="hidden" name="idx" value="${board.idx}">
            <div class="produdct-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">아이디</div>
                            <input type="text" name="id" value="${board.id}"/>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">이미지</div>

                            <div class="product-photo" id="${board.fileName}">
                                <input type="file" id="file" name="file" accept="image/jpeg, image/png, image/jpg">
                            </div>
                            <c:if test="${not empty board.fileName}">
                                <button id="delete" class="write-btn">삭제</button>
                            </c:if>
                            <div class="file"></div>
                            <input hidden class="originalfile" name="originalfile" value="${board.fileName}">
                            ${board.fileName}
                        </li>
                        <li class="info-title">
                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="제목을 입력하세요." class="write-title"
                                   value="${board.title}">
                        </li>
                        <li class="info-title">
                            <div class="info-detail">
                                내용
                            </div>
                            <textarea name="content" id="editor">${board.content}</textarea>
                            <script>
                                //CKEditor5를 생성할 textarea 지정
                                ClassicEditor
                                    .create(document.querySelector('#editor'))
                                    .catch(error => {
                                        console.error(error);
                                    });
                            </script>
                        </li>
                    </ul>
                </section>
                <div class="product-btn">
                    <input type="submit" class="write-btn" value="수정완료">
                </div>
        </form>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
        integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/fileChange.js"></script>
<script>
    <%--const file = document.querySelector("#file");--%>
    <%--const fileName = document.querySelector(".product-photo").getAttribute("id");--%>


    <%--$('#delete').click(function (e) {--%>
    <%--	e.preventDefault();--%>
    <%--	fileDelete(e)--%>
    <%--})--%>

    if (fileName !== null) {
        $('.product-photo').css({
            "background": "url(${pageContext.request.contextPath}/assets/upload/" + fileName + ")",
            'background-repeat': 'no-repeat',
            'background-position': 'center center',
            'background-size': 'cover'
        })
    } else {
        $('.product-photo').css({
            "background": "url(${pageContext.request.contextPath}/assets/images/upload.png)",
            'background-repeat': 'no-repeat',
            'background-position': 'center center',
            'background-size': '20%'
        })
    }

    <%--function fileDelete(e) {--%>
    <%--	$('.product-photo').css({--%>
    <%--		"background": "url(${pageContext.request.contextPath}/assets/images/upload.png)",--%>
    <%--		'background-repeat': 'no-repeat',--%>
    <%--		'background-position': 'center center',--%>
    <%--		'background-size': '20%'--%>
    <%--	});--%>
    <%--}--%>

    <%--$('#file').on("change", fileChange);--%>

    <%--function fileChange(e) {--%>
    <%--	const files = e.target.files;--%>
    <%--	const filesArr = Array.prototype.slice.call(files);--%>

    <%--	filesArr.forEach(function (f) {--%>
    <%--		const reader = new FileReader();--%>
    <%--		reader.onload = function (e) {--%>
    <%--			$('.product-photo').css({--%>
    <%--				"background": "url(${pageContext.request.contextPath}/assets/upload/" + e.target.result + ")",--%>
    <%--				'background-repeat': 'no-repeat',--%>
    <%--				'background-position': 'center center',--%>
    <%--				'background-size': 'cover'--%>
    <%--			});--%>
    <%--			$('.product-photo::before').css({'background-image': 'url("")'});--%>
    <%--		}--%>
    <%--		reader.readAsDataURL(f);--%>
    <%--	})--%>
    <%--}--%>
</script>
</html>