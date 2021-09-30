<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="lost" action="dailyWrite.board" method="post" enctype="multipart/form-data">
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
