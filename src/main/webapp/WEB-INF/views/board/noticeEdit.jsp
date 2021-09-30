<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-09-29
  Time: 오후 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글쓰기</title>
    <SCRIPT type="text/javascript">
        function check(){
            if(!notice.subject.value){
                alert("제목을 입력하세요");
                notice.subject.focus();
                return false;
            }
            if(!notice.writer.value){

                alert("이름을 입력하세요");
                notice.writer.focus();
                return false;
            }
            if(!notice.content.value){
                alert("글 내용을 입력하세요");
                notice.content.focus();
                return false;
            }
            if(!notice.pwd.value){
                alert("비밀번호를 입력하세요");
                notice.pwd.focus();
                return false;
            }

            document.notice.submit();

        }
    </SCRIPT>
</head>
<body>

<c:set var="idx" value="${requestScope.idx}"/>
<c:set var="board" value="${requestScope.board}"/>
<div id="pageContainer">
    <div style="padding-top: 25px; text-align: center">
        <!-- form 시작 ---------->
        <form name="notice" action="NoticeEditOk.board" method="POST">
            <table width="95%" border="2" align="center">
                <tr>
                    <td width="30%" align="center"><b>글번호</b></td>
                    <td width="20%">
                        <input type="text" name="idx" value="${idx}">
                    </td>
                    <td width="50%" align="center"><b>작성일</b></td>
                    <td>${board.writeDate}</td>
                </tr>
                <tr>
                    <td width="20%" align="center"><b>제목</b></td>
                    <td width="80%" align="left"><input type="text" name="title" size="40" value="${board.title}"></td>
                </tr>
                <tr>
                    <td width="20%" align="center"><b>글쓴이</b></td>
                    <td width="20%" align="left"><input type="text" name="writer" size="40" value="${board.id}"></td>
                    <td width="20%" align="center"><b>조회수</b></td>
                    <td width="40%" align="center"><input type="text" name="hit" size="40" value="${board.hit}" </td>
                </tr>
                <tr height="100">
                    <td width="20%" align="center"><b>글내용</b></td>
                    <td colspan="3">
                        <textarea rows="7" cols="50" name="content" class="ckeditor">
                            ${board.content}
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="수정완료" onclick="editCheck();"/>
                        <input type="reset"  value="취소" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
