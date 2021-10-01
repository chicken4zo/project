<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>
        <div class="board-logo">
            <h3>분실·실종</h3>
        </div>
        <form name="lost" action="lostWrite.board" method="post" enctype="multipart/form-data">
            <div class="produdct-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">아이디</div>
                            <input type="text" name="id" value=""/>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">이미지</div>

                            <div class="product-photo">
                                <input type="file" id="file" name="file" accept="image/jpeg, image/png, image/jpg">
                            </div>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="제목을 입력하세요." class="write-title" value>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">
                                내용
                            </div>
                            <textarea name="content" id="editor"></textarea>
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
                    <input type="submit" class="write-btn" value="등록하기">
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>

	$('#file').on("change", fileChange);

	function fileChange(e) {
		const files = e.target.files;
		const filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function (f) {
			const reader = new FileReader();
			reader.onload = function (e) {
				$('.product-photo').css({
					"background": "url(" + e.target.result + ")",
					'background-repeat': 'no-repeat',
					'background-position': 'center center',
					'background-size': 'cover'
				});
				$('.product-photo::before').css({'background-image': 'url("")'});
			}
			reader.readAsDataURL(f);
		})
	}
</script>
</html>
