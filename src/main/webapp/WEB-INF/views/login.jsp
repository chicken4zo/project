<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>



<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mypage.css">
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
    <%--    <!-- sweet alert -->--%>
    <%--    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>--%>
    <%--    <link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />--%>
</head>
<body>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="/WEB-INF/include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <div style="text-align: center; margin-top: 60px">
            <img src="${pageContext.request.contextPath}/assets/images/loginlogo.png" width="30%" height="30%">
        </div>

        <div class="form-group">
            <form id="logform" action="LoginOk.member" method="POST">

                <label>아이디</label>
                <input class="login_input input" type="text" name="id" id="id" required>


                <label>패스워드</label>
                <input name="password" id="password" class="login_input input" type="password">
                <div class="btn login_btn">
                    <button type="submit" class="button" id="login_btn">로그인</button>
                </div>
            </form>
        </div>

    </div>
</div>
<%--weather--%>
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

<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script type="text/javascript">
    // $().ready(function () {
    //     $(".login_btn").click(function () {
    //         Swal.fire({
    //             icon: 'success',
    //             title: '로그인 성공',
    //             text: '고구마켓에서 즐거운 시간 되세요!',
    //         });
    //     });
    // });
    //
    // $().ready(function () {
    //     $(".login_btn").click(function () {
    //         Swal.fire({
    //             icon: 'error',
    //             title: '로그인 실패',
    //             text: '다시 시도하세요.',
    //         });
    //     });
    // });

    <!-- login logout sweet alert Ajax -->
    <%--$(document).ready(function() {--%>
    <%--    $("#login_btn").on('click', function() {--%>
    <%--        const dataString = $('#logform').attr("action");--%>
    <%--        $.ajax({--%>
    <%--            type: "POST",--%>
    <%--            url: "${pageContext.request.contextPath}/Login.member",--%>
    <%--            data: dataString.result,--%>
    <%--            success: function(data) {--%>
    <%--                if (data =! null) {--%>
    <%--                    swal({--%>
    <%--                            icon: 'success',--%>
    <%--                            title: '로그인 성공',--%>
    <%--                            text: '고구마켓에서 즐거운 시간 되세요!',--%>
    <%--                            closeOnConfirm: false--%>
    <%--                        }).then(function() {--%>
    <%--                        window.location.href = "productList.board";--%>
    <%--                        swal.close();--%>
    <%--                    });--%>

    <%--                } else if (data == null) {--%>
    <%--                    swal({--%>
    <%--                        // title: "Submission Failed!",--%>
    <%--                        closeOnConfirm: true,--%>
    <%--                        icon: 'error',--%>
    <%--                        title: '로그인 실패',--%>
    <%--                        text: '일치하는 회원정보가 없습니다.',--%>
    <%--                    });--%>
    <%--                }--%>
    <%--            }.then(function() {--%>
    <%--                window.location.href = "productList.board";--%>
    <%--                swal.close();--%>
    <%--            }),--%>
    <%--        });--%>
    <%--    });--%>
    <%--});--%>

</script>


</html>
