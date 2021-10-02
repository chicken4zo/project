<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글쓰기</title>
    <SCRIPT type="text/javascript">
        function check() {
            if (!restaurant.title.value) {
                alert("제목을 입력하세요");
                restaurant.title.focus();
                return false;
            }
            if (!restaurant.id.value) {
                alert("이름을 입력하세요");
                restaurant.id.focus();
                return false;
            }
            if (!restaurant.content.value) {
                alert("글 내용을 입력하세요");
                restaurant.content.focus();
                return false;
            }
            document.restaurant.submit();
        }
    </SCRIPT>
    <!-- 첨부파일 <file> -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- summbernote -->
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!-- include summernote css/js -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#filename').on("change", fileChange);
            $('#summernote').summernote({
                placeholder: '내용을 입력하세요',
                tabsize: 2,
                height: 300,
                minHeight: 300,
                maxHeight: 300,
            });
        })

        function fileChange(e) {
            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);

            filesArr.forEach(function (f) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#preview').attr("src", e.target.result);
                }
                reader.readAsDataURL(f);
            })
        }

    </script>
    </script>
</head>
<body>


<div id="pageContainer">
    <div style="padding-top: 25px; text-align: center">
        <!-- form 시작 ---------->
        <form name="restaurant" action="RestaurantWriteOk.board" method="POST" enctype="multipart/form-data">
            <table width="95%" border="2" align="center">
                <tr>
                    <td width="20%" align="center">제목</td>
                    <td width="80%" align="left"><input type="text" name="title" size="40"></td>
                </tr>
                <tr>
                    <td width="20%" align="center">글쓴이</td>
                    <td width="80%" align="left"><input type="text" name="id" size="40"></td>
                </tr>
                <tr>
                    <td width="20%" align="center">글내용</td>
                    <td width="80%" align="left"><textarea rows="10" cols="60" name="content"></textarea></td>
                </tr>
                <tr>
                    <td width="20%" align="center">첨부파일</td>
                    <td width="80%" align="left"><input type="file" id="filename" name="filename"></td>
                </tr>
                <tr>
                    <td width="20%" align="center">미리보기</td>
                    <td width="80%" align="left"><img id="preview" src="" width="300" alt=""></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="button" value="글쓰기" onclick="check();"/>
                        <input type="reset" value="다시쓰기"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
