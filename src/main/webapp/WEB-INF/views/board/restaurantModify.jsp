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
    <title>Title</title>
</head>
<body>
<c:set var="idx" value="${requestScope.idx}"></c:set>
<c:set var="board" value="${requestScope.board}"></c:set>

<div id="pageContainer">
    <div style="padding-top: 25px; text-align: center">
        <!-- form 시작 -->
        <form name="edit" action="editok.board" method="POST" enctype="multipart/form-data">
            <center>
                <table width="90%" border="1">
                    <tr>
                        <td width="20%" align="center"><b> 글번호 </b></td>
                        <td width="30%">
                            ${idx}
                            <input type="hidden" name="idx" value="${idx}"></td>
                        <td width="20%" align="center"><b>작성일</b></td>
                        <td>${board.writedate}</td>
                    </tr>
                    <tr>
                        <td width="20%" align="center"><b>글쓴이</b></td>
                        <td width="30%">
                            <input type="text" name="writer" value="${board.writer}">
                        </td>
                        <td width="20%" align="center"><b>홈페이지</b></td>
                        <td>
                            <input type="text" name="homepage" value="${board.homepage}">
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" align="center"><b>제목</b></td>
                        <td colspan="3">
                            <input type="text" name="subject" value="${board.subject}" size="40">
                        </td>
                    </tr>
                    <tr height="100">
                        <td width="20%" align="center"><b>글내용</b></td>
                        <td colspan="3">
								<textarea rows="7" cols="50" name="content" id="summernote">
                                    ${board.content}
                                </textarea>
                        </td>
                    </tr>

                    <tr>
                        <td width="20%" align="center"><b>첨부파일</b></td>
                        <td colspan="3">${board.filename} (${board.filesize}bytes)<br/>
                            <input type="file" name="filename" id="filename">
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" align="center">미리보기</td>
                        <td width="80%" align="left"><img id="preview" src="upload/${board.filename}" width="300"
                                                          alt=""></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center">
                            <input type="button" value="수정하기" onclick="editCheck();">
                            <input type="reset" value="다시쓰기">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center">
                            <a href="list.board">목록</a>
                        </td>
                    </tr>
                </table>
            </center>
        </form>
    </div>
</div>
</body>
</html>
