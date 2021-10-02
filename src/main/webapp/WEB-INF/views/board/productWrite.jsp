<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:24
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

    <!--    mvp.css css 작업할 때 지워줘야함-->
    <!--    <link rel="stylesheet" href="https://unpkg.com/mvp.css">-->
    <style>
        .product-photo::before {
            background-image: url("${pageContext.request.contextPath}/assets/images/upload.png");
        }
    </style>
    <script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
</head>
<body>
<div id="body_wrap">
    <div class="wrapper">
        <jsp:include page="../../include/top.jsp"/>
        <form name="bbs" action="productWrite.board" method="POST" enctype="multipart/form-data">
            <div class="board-logo">
                <h3>상품 등록</h3>
            </div>
            <div class="product-group">

                <section class="product-wrap">
                    <!--    <h2>글쓰기</h2>-->

                    <ul class="product-info">
                        <li class="info-title">
                            <div class="info-detail">이미지</div>

                            <div class="product-photo">
                                <input type="file" name="filename1" accept="image/jpeg, image/png, image/jpg" multiple>
                            </div>
                            <div class="product-photo">
                                <input type="file" name="filename2" accept="image/jpeg, image/png, image/jpg" multiple>
                            </div>
                            <div class="product-photo">
                                <input type="file" name="filename3" accept="image/jpeg, image/png, image/jpg" multiple>
                            </div>
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
                <%--    <label>title</label>--%>
                <%--    <input type="text" name="title">--%>
                <%--    <label>id</label>--%>
                <%--    <input type="text" name="id">--%>
                <%--    <label>content</label>--%>
                <%--    <input type="text" name="content">--%>
                <%--    <label>price</label>--%>
                <%--    <input type="text" name="price">--%>
                <%--    <label>address</label>--%>
                <%--    <input type="text" name="address">--%>
                <%--    <label>file1</label>--%>
                <%--    <input type="file" name="filename1">--%>
                <%--    <label>file2</label>--%>
                <%--    <input type="file" name="filename2">--%>
                <%--    <label>file3</label>--%>
                <%--    <input type="file" name="filename3">--%>
                <%--    <input type="submit">--%>
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
