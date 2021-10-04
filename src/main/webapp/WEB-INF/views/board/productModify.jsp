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
<c:set var="productBoard" value="${requestScope.productBoard}"/>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>
        <div class="board-logo">
            <h3>반려동물</h3>
        </div>
        <form action="productModify.board?title=${productBoard.title}" method="post" enctype="multipart/form-data">
            <input type="hidden" name="idx" value="${productBoard.idx}">
            <div class="produdct-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">아이디</div>
                            <input type="text" name="id" value="${productBoard.id}"/>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">카테고리</div>
                            <select name="select-profession" id="select-profession">
                                <option value="1">&nbsp;의류 & 신발</option>
                                <option value="2">&nbsp;반려동물</option>
                                <option value="3">&nbsp;주방용품</option>
                                <option value="4">&nbsp;스포츠 & 생활</option>
                                <option value="4">&nbsp;전자제품</option>
                                <option value="4">&nbsp;육아용품</option>
                            </select>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">가격</div>
                            <input type="text" name="price" placeholder="가격을 입력하세요." class="write-price" value> <span
                                class="product-price">원</span>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">이미지</div>

                            <div class="product-photo">
                                <input name="filename1" type="file" class="files"
                                       accept="image/jpeg, image/png, image/jpg">
                            </div>
                            <div class="product-photo">
                                <input name="filename2" type="file" class="files"
                                       accept="image/jpeg, image/png, image/jpg">
                            </div>
                            <div class="product-photo">
                                <input name="filename3" type="file" class="files"
                                       accept="image/jpeg, image/png, image/jpg">
                            </div>
                        </li>
                        <%--<li class="info-title">
                            <div class="info-detail">이미지 1</div>
                            <div class="product-photo" id="${productBoard.fileName1}">
                                <input type="file" name="filename1" accept="image/jpeg, image/png, image/jpg">
                            </div>
                            &lt;%&ndash;                            <c:if test="${not empty productBoard.fileName1}">&ndash;%&gt;
                            &lt;%&ndash;                                <button id="delete" class="write-btn">삭제</button>&ndash;%&gt;
                            &lt;%&ndash;                            </c:if>&ndash;%&gt;
                            <input type="hidden" name="originalfile1" value="${productBoard.fileName1}">
                            &lt;%&ndash;                            <c:set target="${productBoard.fileName1}" property="name" value=""/>&ndash;%&gt;

                            <div class="info-detail">이미지 2</div>
                            <div class="product-photo" id="${productBoard.fileName2}">
                                <input type="file" name="filename2" accept="image/jpeg, image/png, image/jpg">
                            </div>
                            &lt;%&ndash;                            <c:if test="${not empty productBoard.fileName2}">&ndash;%&gt;
                            &lt;%&ndash;                                <button id="delete" class="write-btn">삭제</button>&ndash;%&gt;
                            &lt;%&ndash;                            </c:if>&ndash;%&gt;
                            &lt;%&ndash;                            <input type="hidden" name="originalfile2" value="${productBoard.fileName2}">&ndash;%&gt;
                            &lt;%&ndash;                            <c:set target="${productBoard.fileName2}" property="filename2" value=""/>&ndash;%&gt;

                            <div class="info-detail">이미지 3</div>
                            <div class="product-photo" id="${productBoard.fileName3}">
                                <input type="file" name="filename3" accept="image/jpeg, image/png, image/jpg">
                            </div>
                            &lt;%&ndash;                            <c:if test="${not empty productBoard.fileName3}">&ndash;%&gt;
                            &lt;%&ndash;                                <button id="delete" class="write-btn">삭제</button>&ndash;%&gt;
                            &lt;%&ndash;                            </c:if>&ndash;%&gt;
                            &lt;%&ndash;                            <input type="hidden" name="originalfile3" value="${productBoard.fileName3}">&ndash;%&gt;
                            &lt;%&ndash;                            <c:set target="${productBoard.fileName3}" property="filename3" value=""/>&ndash;%&gt;
                        </li>--%>

                        <li class="info-title">
                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="제목을 입력하세요." class="write-title"
                                   value="${productBoard.title}">
                        </li>
                        <li class="info-title">
                            <div class="info-detail">
                                내용
                            </div>
                            <textarea name="content" id="editor">${productBoard.content}</textarea>
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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/fileChange.js"></script>
<script>
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
