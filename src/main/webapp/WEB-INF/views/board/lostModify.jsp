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
<c:set var="lostBoard" value="${requestScope.lostBoard}"/>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <%--content--%>
        <div class="title">수정</div>

        <form action="lostModify.board?title=${lostBoard.title}" method="post" enctype="multipart/form-data">
            <input type="hidden" name="idx" value="${lostBoard.idx}">
            <div class="product-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">아이디</div>
                            ${lostBoard.id}<input type="hidden" name="id" value="${lostBoard.id}" readonly/>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">이미지</div>
                            <div class="product-photo" id="${lostBoard.fileName}">
                                <input type="file" id="file" name="file" accept="image/jpeg, image/png, image/jpg">
                            </div>
                            <c:if test="${not empty lostBoard.fileName}">
                                <button id="delete" class="del-btn">삭제</button>
                            </c:if>
                            <input type="hidden" name="originalfile" value="${lostBoard.fileName}">
                            <%--                            <c:set target="${lostBoard.fileName}" property="name" value=""/>--%>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="제목을 입력하세요." class="write-title"
                                   value="${lostBoard.title}" required>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">
                                내용
                            </div>
                            <textarea name="content" id="editor">${lostBoard.content}</textarea>
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
                    <input type="submit" class="write-btn" value="수정완료">
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

<%--<h2>수정 페이지</h2>--%>
<%--<form action="lostModify.board?title=${lostBoard.title}" method="post" enctype="multipart/form-data">--%>
<%--    <div></div>--%>
<%--    <input type="hidden" name="idx" value="${lostBoard.idx}">--%>
<%--    <input type="text" name="title" value="${lostBoard.title}">--%>
<%--    <input type="text" name="content" value="${lostBoard.content}">--%>
<%--    <c:if test="${not empty lostBoard.fileName}">--%>
<%--        <img src="assets/upload/${lostBoard.fileName}"/>--%>
<%--        <button>삭제</button>--%>
<%--        <div>${lostBoard.fileName}</div>--%>
<%--    </c:if>--%>
<%--    <input type="file" name="file">--%>
<%--    <input type="submit" value="수정">--%>
<%--</form>--%>
</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/fileChange.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/index.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

<script>

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
    <%--const file = document.querySelector("#file");--%>
    <%--const fileName = document.querySelector(".product-photo").getAttribute("id");--%>


    <%--$('#delete').click(function (e) {--%>
    <%--	e.preventDefault();--%>
    <%--    fileDelete(e)--%>
    <%--})--%>

    <%--if (fileName !== null) {--%>
    <%--	$('.product-photo').css({--%>
    <%--		"background": "url(${pageContext.request.contextPath}/assets/upload/" + fileName + ")",--%>
    <%--		'background-repeat': 'no-repeat',--%>
    <%--		'background-position': 'center center',--%>
    <%--		'background-size': 'cover'--%>
    <%--	})--%>
    <%--} else {--%>
    <%--	$('.product-photo').css({--%>
    <%--		"background": "url(${pageContext.request.contextPath}/assets/images/upload.png)",--%>
    <%--		'background-repeat': 'no-repeat',--%>
    <%--		'background-position': 'center center',--%>
    <%--		'background-size': '20%'--%>
    <%--	})--%>
    <%--}--%>

    <%--function fileDelete(e) {--%>
    <%--			$('.product-photo').css({--%>
    <%--				"background": "url(${pageContext.request.contextPath}/assets/images/upload.png)",--%>
    <%--				'background-repeat': 'no-repeat',--%>
    <%--				'background-position': 'center center',--%>
    <%--				'background-size': '20%'--%>
    <%--			});--%>
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
