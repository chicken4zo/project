<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="cp" value="${requestScope.cpage}"/>
<c:set var="ps" value="${requestScope.pagesize}"/>
<c:set var="idx" value="${requestScope.idx}"/>
<h1>답글 쓰기</h1>
<form name="lost" action="lostReplyOk.board" method="post" enctype="multipart/form-data">
    <input type="hidden" name="idx" value="${idx}">
    <input type="hidden" name="ps" value="${ps}">
    <input type="hidden" name="cp" value="${cp}">
    <img src="" id="preview"/>
    <input type="text" name="id">
    <input type="text" name="title">
    <input type="text" name="content">
    <input type="file" name="file" id="file">
    <input type="submit" value="전송">
</form>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
        integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
<script>

	$('#file').on("change", fileChange);

	function fileChange(e) {
		const files = e.target.files;
		const filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function (f) {
			const reader = new FileReader();
			reader.onload = function (e) {
				$('#preview').attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		})
	}
</script>
</html>
