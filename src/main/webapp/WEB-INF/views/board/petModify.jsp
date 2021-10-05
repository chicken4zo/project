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
<c:set var="petBoard" value="${requestScope.petBoard}"/>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <%--content--%>
        <div class="title">수정</div>

        <form action="petModify.board?title=${petBoard.title}" method="post" enctype="multipart/form-data">
            <input type="hidden" name="idx" value="${petBoard.idx}">
            <div class="product-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">아이디</div>
                            ${petBoard.id}<input type="hidden" name="id" value="${petBoard.id}" readonly/>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">이미지</div>
                            <div class="imgupload">
                                <div class="product-photo" id="${petBoard.fileName1}">
                                    <input name="filename1" type="file" class="files"
                                           accept="image/jpeg, image/png, image/jpg">
                                </div>
                                <button id="delete1" class="del-btn">삭제</button>
                            </div>
                            <div class="imgupload">
                                <div class="product-photo" id="${petBoard.fileName2}">
                                    <input name="filename2" type="file" class="files"
                                           accept="image/jpeg, image/png, image/jpg">
                                </div>
                                <button id="delete2" class="del-btn">삭제</button>
                            </div>
                            <div class="imgupload">
                                <div class="product-photo" id="${petBoard.fileName3}">
                                    <input name="filename3" type="file" class="files"
                                           accept="image/jpeg, image/png, image/jpg">
                                </div>
                                <button id="delete3" class="del-btn">삭제</button>
                            </div>
                            <input hidden class="originalfile" name="originalfile1" id="originalfile1"
                                   value="${petBoard.fileName1}">
                            <input hidden class="originalfile" name="originalfile2" id="originalfile2"
                                   value="${petBoard.fileName2}">
                            <input hidden class="originalfile" name="originalfile3" id="originalfile3"
                                   value="${petBoard.fileName3}">
                        </li>
                        <%--<li class="info-title">
                            <div class="info-detail">이미지 1</div>
                            <div class="product-photo" id="${petBoard.fileName1}">
                                <input type="file" name="filename1" accept="image/jpeg, image/png, image/jpg">
                            </div>
                            &lt;%&ndash;                            <c:if test="${not empty petBoard.fileName1}">&ndash;%&gt;
                            &lt;%&ndash;                                <button id="delete" class="write-btn">삭제</button>&ndash;%&gt;
                            &lt;%&ndash;                            </c:if>&ndash;%&gt;
                            <input type="hidden" name="originalfile1" value="${petBoard.fileName1}">
                            &lt;%&ndash;                            <c:set target="${petBoard.fileName1}" property="name" value=""/>&ndash;%&gt;

                            <div class="info-detail">이미지 2</div>
                            <div class="product-photo" id="${petBoard.fileName2}">
                                <input type="file" name="filename2" accept="image/jpeg, image/png, image/jpg">
                            </div>
                            &lt;%&ndash;                            <c:if test="${not empty petBoard.fileName2}">&ndash;%&gt;
                            &lt;%&ndash;                                <button id="delete" class="write-btn">삭제</button>&ndash;%&gt;
                            &lt;%&ndash;                            </c:if>&ndash;%&gt;
                            &lt;%&ndash;                            <input type="hidden" name="originalfile2" value="${petBoard.fileName2}">&ndash;%&gt;
                            &lt;%&ndash;                            <c:set target="${petBoard.fileName2}" property="filename2" value=""/>&ndash;%&gt;

                            <div class="info-detail">이미지 3</div>
                            <div class="product-photo" id="${petBoard.fileName3}">
                                <input type="file" name="filename3" accept="image/jpeg, image/png, image/jpg">
                            </div>
                            &lt;%&ndash;                            <c:if test="${not empty petBoard.fileName3}">&ndash;%&gt;
                            &lt;%&ndash;                                <button id="delete" class="write-btn">삭제</button>&ndash;%&gt;
                            &lt;%&ndash;                            </c:if>&ndash;%&gt;
                            &lt;%&ndash;                            <input type="hidden" name="originalfile3" value="${petBoard.fileName3}">&ndash;%&gt;
                            &lt;%&ndash;                            <c:set target="${petBoard.fileName3}" property="filename3" value=""/>&ndash;%&gt;
                        </li>--%>

                        <li class="info-title">
                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="제목을 입력하세요." class="write-title"
                                   value="${petBoard.title}" required>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">
                                내용
                            </div>
                            <textarea name="content" id="editor">${petBoard.content}</textarea>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/index.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/fileChange.js"></script>

<script>
	<%--const file = document.querySelector("#file");--%>
	const fileNames = document.querySelectorAll('.product-photo');
	$.each(fileNames, function (index, obj) {
		console.log(obj.id);
		console.log(fileNames);
		if (obj.id !== null) {
			$(this).css({
				"background": "url(${pageContext.request.contextPath}/assets/upload/" + obj.id + ")",
				'background-repeat': 'no-repeat',
				'background-position': 'center center',
				'background-size': 'cover'
			});
		}
		<%--} else {--%>
		<%--	$(this).css({--%>
		<%--		"background": "url(${pageContext.request.contextPath}/assets/images/upload.png)",--%>
		<%--		'background-repeat': 'no-repeat',--%>
		<%--		'background-position': 'center center',--%>
		<%--		'background-size': '20%'--%>
		<%--	});--%>
		<%--}--%>
	})


	<%--$('#delete').click(function (e) {--%>
	<%--	e.preventDefault();--%>
	<%--    fileDelete(e)--%>
	<%--})--%>



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
