<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
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
            <img src="${pageContext.request.contextPath}/assets/images/registerlogo.png" width="35%" height="35%">
        </div>
        <div class="form-group">
            <form id="form1" action="RegisterOk.member" method="POST">
                <label>아이디</label>
                <input maxlength="20" id="id" name="id" class="login_input input" type="text">
                <button class="button search-btn" id="btn1" name="btn1" type="button">중복확인</button>

                <label>이름</label>
                <input maxlength="21" id="name" name="name" class="login_input input" type="text" required>

                <label>패스워드</label>
                <input maxlength="20" id="password" name="password" class="login_input input" type="password" required>

                <label>패스워드 확인</label>
                <input maxlength="20" id="password2" name="password2" class="login_input input" type="password"
                       required>

                <label>주소</label>
                <input type="text" id="address" name="address" class="login_input input" required>
                <input type="button" class="button search-btn" onclick="sample6_execDaumPostcode()" value="검색">


                <label>생년월일</label>
                <input type="text" maxlength="8" id="birth" name="birth" class="login_input input"
                       placeholder="&nbsp;8자리를 입력하세요 예)19990101" required>

                <div class="btn btn_wrap">
                    <button id="submit" type="submit" class="button">회원가입</button>
                    <button type="reset" class="button" id="cancel">취소</button>
                </div>
            </form>
        </div>


    </div>
</div>
<%--footer--%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $('#btn1').on('click', function () {
            const actionurl = $('#form1').attr("action");
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/IdCheck.member',
                data: {"id": $('#id').val()},
                success: function (data) {
                    if ($.trim(data) == 0) {
                        alert('사용가능한 아이디 입니다.');
                    } else {
                        alert('사용불가, 다른 아이디를 입력해주세요.');
                    }

                }
            });
        });
    });

    // 유효성 검사 ---

    $(document).ready(function () {

        //아이디 유효성 검사
        function NameCheck() {
            const name = $('#name').val();
            const kor = name.search(/^[가-힣]+$/g);

            if (name.length < 1 || name.length > 20) {
                $('.tdname').text("1글자 이상 20글자 이하로 입력해주세요.")
                return false;
            } else if (name.search(/\s/) != -1) {
                $('.tdname').text("공백은 입력할 수 없습니다");
                return false;
            } else if (kor < 0) {
                $('.tdname').text("한글만 입력해 주세요.")
                return false;
            } else {
                $('.tdname').text("");
                return true;
            }

        }

        $('#name').on('keyup', NameCheck);

        // 비밀번호 유효성 검사
        function CheckPw() {
            const password = $('#password').val();
            const num = password.search(/[0-9]/g);
            const eng = password.search(/[0-9]/ig);
            const space = password.search(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
            const text = "";
            //1. 6자리 ~12자리
            if (password.length < 8 || password.length > 12) {
                $('.tdpw').text("8-12자리 이내로 입력하세요");
                return false;
            } else if (password.search(/\s/) != -1) {
                $('.tdpw').text("공백은 입력할 수 없습니다");
                return false;
            } else if (num < 0 || eng < 0 || space < 0) {
                $('.tdpw').text("영어,숫자,특수문자를 포함해주세요:)");
                return false;
            } else {
                $('.tdpw').text("");
                return true;
            }
            if (password == '') {
                alert('비밀번호를 입력하세요');
            }
        }

        /* 비밀번호 일치 체크 */
        function CheckPw2() {
            const check = $('#password2').val();
            const password = $('#password').val();
            if (!(check === password)) {
                $('.tdpwch').text("비밀번호가 일치하지 않습니다");
                return false;
            } else {
                $('.tdpwch').text("비밀번호가 일치합니다 :)");
                return true;
            }

            if (check == '') {
                alert('비밀번호를 입력하세요');
            }
        }

        $('#password').on('keyup', CheckPw);
        $('#password2').on('keyup', CheckPw2);


        function isBirthday() {
            console.log("여기는 탑니까?");
            const birth = $('#birth').val();
            const birthreg = birth.search(/[0-9]/g);

            var year = Number(birth.substr(0, 4)); // 입력한 값의 0~4자리까지 (연)
            var month = Number(birth.substr(4, 2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
            var day = Number(birth.substr(6, 2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
            var today = new Date(); // 날짜 변수 선언
            var yearNow = today.getFullYear(); // 올해 연도 가져옴

            if (birth.length < 7) { //연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
                $('.tdbirth').text("생년월일은 8자리(YYYY/MM/DD 로 입력해주세요.");
                return false;
            } else if (1900 > year || year > yearNow) {
                $('.tdbirth').text("제대로된 년도를 입력해주세요");
                return false;
            } else if (month < 1 || month > 12) {
                $('.tdbirth').text("제대로된 월을 입력해주세요");
                return false;
            } else if (day < 1 || day > 31) {
                console.log("여기는 탑니까1?");
                $('.tdbirth').text("제대로된 일을 입력해주세요");
                return false;
            } else if (birthreg < 0) {
                $('.tdbirth').text("숫자만 입력해주세요");
                return false;
            } else {
                $('.tdbirth').text("");
                return true;
            }
        }

        $('#birth').on('keyup', isBirthday);
    });

    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address").value = addr;

            }
        }).open();
    }

</script>


</html>