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
<c:set var="productBoard" value="${requestScope.productBoard}"/>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="../../include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <div class="title">수정</div>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <c:set var="title" value="${fn:substringAfter(productBoard.title,']')}"/>

        <form action="productModify.board?title=${title}" method="post" enctype="multipart/form-data">
            <input type="hidden" name="idx" value="${productBoard.idx}">
            <div class="product-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">아이디</div>
                            ${productBoard.id}<input type="hidden" name="id" value="${productBoard.id}" readonly/>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">카테고리</div>
                            <select name="category" id="category">
                                <option value="의류&신발">&nbsp;의류 & 신발</option>
                                <option value="반려동물">&nbsp;반려동물</option>
                                <option value="주방용품">&nbsp;주방용품</option>
                                <option value="스포츠&생활">&nbsp;스포츠 & 생활</option>
                                <option value="전자제품">&nbsp;전자제품</option>
                                <option value="육아용품">&nbsp;육아용품</option>
                            </select>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">가격</div>
                            <input type="text" name="price" placeholder="가격을 입력하세요." class="write-price"
                                   value="${productBoard.price}" required
                                   oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">이미지</div>
                            <div class="imgupload">
                                <div class="product-photo" id="${productBoard.fileName1}">
                                    <input name="filename1" type="file" class="files"
                                           accept="image/jpeg, image/png, image/jpg">
                                </div>
                            </div>
                            <div class="imgupload">
                                <div class="product-photo" id="${productBoard.fileName2}">
                                    <input name="filename2" type="file" class="files"
                                           accept="image/jpeg, image/png, image/jpg">
                                </div>
                            </div>
                            <div class="imgupload">
                                <div class="product-photo" id="${productBoard.fileName3}">
                                    <input name="filename3" type="file" class="files"
                                           accept="image/jpeg, image/png, image/jpg">
                                </div>
                            </div>
                            <input hidden class="originalfile" name="originalfile1" id="originalfile1"
                                   value="${productBoard.fileName1}">
                            <input hidden class="originalfile" name="originalfile2" id="originalfile2"
                                   value="${productBoard.fileName2}">
                            <input hidden class="originalfile" name="originalfile3" id="originalfile3"
                                   value="${productBoard.fileName3}">
                        </li>
                        <li class="info-title">

                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="제목을 입력하세요." class="write-title"
                                   value="${title}" required>
                        </li>
                        <li class="info-title">
                            <div class="info-detail">
                                내용
                            </div>
                            <textarea name="content" id="editor" required>${productBoard.content}</textarea>
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
<script src="${pageContext.request.contextPath}/assets/js/fileChange.js?ver=1"></script>
<script src="${pageContext.request.contextPath}/assets/js/index.js?ver=1"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js?ver=1"></script>
<script>
	const urlTest = window.location.href.split("/");
	const fileNameList = document.querySelectorAll('.product-photo');
	$.each(fileNameList, function (index, obj) {
		console.log(obj.id);
		console.log(fileNameList);
		console.log(this);
		$(this).css({
			"background": "url('/" + urlTest[3] + "/assets/upload/" + obj.id + "')",
			'background-repeat': 'no-repeat',
			'background-position': 'center center',
			'background-size': 'cover'
		});
	});
</script>

</html>
