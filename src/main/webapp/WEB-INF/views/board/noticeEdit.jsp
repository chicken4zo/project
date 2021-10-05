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
<c:set var="idx" value="${requestScope.idx}"/>
<c:set var="board" value="${requestScope.board}"/>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <div class="title">수정</div>
        <form name="notice" action="NoticeEditOk.board" method="post">
            <input type="hidden" name="idx" value="${idx}">
            <input type="hidden" name="hit" size="40" value="${board.hit}">
            <div class="product-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">아이디</div>
                            ${board.id}<input type="hidden" name="writer" value="${board.id}" readonly/>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="제목을 입력하세요." class="write-title"
                                   value="${board.title}" required>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">
                                내용
                            </div>
                            <textarea name="content" id="editor">${board.content}</textarea>
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
            </div>
        </form>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
        integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/index.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

<SCRIPT type="text/javascript">
	function check() {
		if (!notice.subject.value) {
			alert("제목을 입력하세요");
			notice.subject.focus();
			return false;
		}
		if (!notice.writer.value) {

			alert("이름을 입력하세요");
			notice.writer.focus();
			return false;
		}
		if (!notice.content.value) {
			alert("글 내용을 입력하세요");
			notice.content.focus();
			return false;
		}
		if (!notice.pwd.value) {
			alert("비밀번호를 입력하세요");
			notice.pwd.focus();
			return false;
		}

		document.notice.submit();

	}
</SCRIPT>
</html>
