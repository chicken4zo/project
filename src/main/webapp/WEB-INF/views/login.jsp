<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<h1 class="h3 mb-3 fw-normal">로그인</h1>
<form action="LoginOk.member" method="post">

    <div class="form-floating">
        <input type="text" class="form-control" id="floatingInput" name="id" placeholder="아이디 입력" required="required">
        <label for="floatingInput">계정</label>
    </div>
    <div class="form-floating">
        <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="비밀번호 입력"
               required="required">
        <label for="floatingPassword">비밀번호</label>
    </div>

    <div class="checkbox mb-3"></div>
    <button class="w-100 btn btn-lg btn-warning" id="loginsubmit" type="submit">로그인</button>
    <div class="checkbox mb-3"></div>
    <a href="Register.member"><h5>회원가입</h5></a>
</form>
</body>
</html>
