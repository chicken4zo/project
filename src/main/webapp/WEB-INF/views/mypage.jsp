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

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<body>
<c:set var="mylist" value="${requestScope.list}"/>
<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="/WEB-INF/include/top.jsp"/>

        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <div class="board-logo">
            <h3>마이구마</h3>
        </div>
        <div class="post-main">
            <h3>나의 상품</h3>
        </div>
        <section id="post-group">
            <section class="post-wrap">
                <c:choose>
                    <c:when test="${not empty mylist}">

                        <c:forEach items="${mylist}" var="post">
                            <article class="post">
                                <div class="post-photo">
                                    <img alt="사진이름"
                                         src="${pageContext.request.contextPath}/assets/upload/${post.fileName1}">
                                </div>
                                <div class="post-desc">
                                    <a href="productContent.board?idx=${post.idx}"><h2
                                            class="post-title">${post.title}</h2></a>
                                    <div class="post-price">${post.address}</div>
                                    <div class="post-content">${post.content}</div>
                                </div>
                            </article>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h4>게시한 상품이 없습니다</h4>
                    </c:otherwise>
                </c:choose>
            </section>
        </section>
        <div class="post-main">
            <h3>정보수정</h3>
        </div>
        <div class="form-group">
            <c:set var="memberDto" value="${requestScope.memberDto}"/>
            <form id="form1" action="EditMember.member" method="post">
                <label>아이디</label>
                <input type="text" class="login_input input" name="id" id="id" value="${memberDto.id}" readonly>

                <label>패스워드</label>
                <input type="password" class="login_input input" id="password" name="password"
                       value="${memberDto.password}">
                <div class="tdpw"></div>

                <label>이름</label>
                <input type="text" class="login_input input" id="name" name="name" value="${memberDto.name}">
                <div class="tdname"></div>

                <label>주소</label>
                <input type="text" id="address" name="address" class="login_input input" value="${memberDto.address}">
                <input type="button" class="button search-btn" onclick="sample6_execDaumPostcode()" value="검색">


                <label>생년월일</label>
                <input placeholder="&nbsp;8자리를 입력하세요 예)19990101" type="text" class="login_input input" id="birth"
                       name="birth" value="${memberDto.birth}">
                <div class="tdbirth"></div>

                <div class="edit-btn">
                    <button type="submit" class="button" id="btn1" class="submit_btn">정보수정
                    </button>
                    <button type="button" class="button" id="cancel" style="border: none;"
                            onclick="location.href='Unregister.member?id=${memberDto.id}'">회원탈퇴
                    </button>
                </div>
            </form>
        </div>


    </div>
</div>
<%--weather--%>
<jsp:include page="/WEB-INF/include/footer.jsp"/>

</body>
<!--bootstrp js-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<script>

	$(document).ready(function () {

		$('#form1').submit(function () {

			//    비밀번호 유효성
			const password = $('#password').val();
			const num = password.search(/[0-9]/g);
			const eng = password.search(/[a-z]/ig);
			const space = password.search(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
			const text = "";
			//1. 6자리 ~12자리
			if (password.length < 8 || password.length > 12) {

				swal({
					icon: 'warning',
					text: '비밀번호 : 8-12자리 이내로 입력하세요.'
				});
				$('#password').focus();
				return false;
			} else if (password.search(/\s/) != -1) {
				swal({
					icon: 'warning',
					text: '비밀번호 : 공백은 입력할 수 없습니다.'
				});
				$('#password').focus();
				return false;
			} else if (num < 0 || eng < 0 || space < 0) {

				swal({
					icon: 'warning',
					text: '비밀번호 : 영어,숫자,특수문자를 포함해주세요.'
				});
				$('#password').focus();
				return false;
			}

			//  비밀번호 일치 체크


			//    생년월일

			console.log("여기는 탑니까?");
			const birth = $('#birth').val();
			const birthreg = birth.search(/[0-9]/g);

			var year = Number(birth.substr(0, 4)); // 입력한 값의 0~4자리까지 (연)
			var month = Number(birth.substr(4, 2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
			var day = Number(birth.substr(6, 2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
			var today = new Date(); // 날짜 변수 선언
			var yearNow = today.getFullYear(); // 올해 연도 가져옴

			if (birth.length < 7) { //연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
				swal({
					icon: 'warning',
					text: '생년월일은 8자리(YYYY/MM/DD) 로 입력해주세요.'
				});
				$('#birth').focus();
				return false;
			} else if (1900 > year || year > yearNow) {
				swal({
					icon: 'warning',
					text: '생년월일 : 년도를 확인해주세요'
				});
				$('#birth').focus();
				return false;
			} else if (month < 1 || month > 12) {
				swal({
					icon: 'warning',
					text: '생년월일 : 월을 확인해주세요'
				});
				$('#birth').focus();
				return false;
			} else if (day < 1 || day > 31) {

				swal({
					icon: 'warning',
					text: '생년월일 : 일을 확인해주세요'
				});
				$('#birth').focus();
				return false;
			} else if (birthreg < 0) {
				swal({
					icon: 'warning',
					text: '생년월일 : 숫자만 입력해주세요.'
				});
				$('#birth').focus();
				return false;
			}


		});

	});


</script>
</html>
