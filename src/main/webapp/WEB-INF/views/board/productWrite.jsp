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
        .product-photo::before {
            background-image: url("${pageContext.request.contextPath}/assets/images/upload.png");
        }
    </style>

    <!-- ckeditor -->
    <script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>

</head>
<body>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="/WEB-INF/include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <div class="title">상품등록</div>
        <form name="bbs" action="petWriteOk.board" method="POST" enctype="multipart/form-data">
            <div class="product-group">
                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->
                    <ul class="product-info">


                        <li class="info-title idAddress">
                            <div class="info-detail">아이디</div>
                            <input type="text" name="id" placeholder="!!!아이디 불러오기" class="write-title" readonly>
                            <div class="info-detail">주소</div>
                            <input type="text" name="address" placeholder="!!!주소 불러오기" class="write-title" readonly>
                        </li>

                        <li class="info-title">
                            <div class="info-detail">제목</div>
                            <input type="text" name="title" placeholder="제목을 입력하세요." class="write-title" value>
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
                                <input name="filename1" type="file" accept="image/jpeg, image/png, image/jpg" multiple>
                            </div>
                            <div class="product-photo">
                                <input name="filename2" type="file" accept="image/jpeg, image/png, image/jpg" multiple>
                            </div>
                            <div class="product-photo">
                                <input name="filename3" type="file" accept="image/jpeg, image/png, image/jpg" multiple>
                            </div>
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
                    <button type="submit" class="write-btn">등록하기</button>
                </div>
            </div>
        </form>

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
<script src="${pageContext.request.contextPath}/assets/js/index.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>