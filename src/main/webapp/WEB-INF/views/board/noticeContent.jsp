<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>board : content</title>

</head>
<body>


<c:set var="idx" value="${requestScope.idx}"></c:set>
<c:set var="board" value="${requestScope.board}"></c:set>
<c:set var="cpage" value="${requestScope.cpage}"></c:set>
<c:set var="pagesize" value="${requestScope.pagesize}"></c:set>

<div id="pageContainer">
    <div style="padding-top: 30px; text-align: center; margin: 0 auto;">
        <center>
            <b>게시판 글내용</b>
            <table width="80%" border="1">
                <tr>
                    <td width="20%" align="center"><b> 글번호 </b></td>
                    <td width="30%">${idx}</td>
                    <td width="20%" align="center"><b>작성일</b></td>
                    <td>${board.writeDate}</td>
                </tr>
                <tr>
                    <td width="20%" align="center"><b>글쓴이</b></td>
                    <td width="30%">${board.id}</td>
                    <td width="20%" align="center"><b>조회수</b></td>
                    <td>${board.hit}</td>
                </tr>
                <tr>
                    <td width="20%" align="center"><b>제목</b></td>
                    <td colspan="3">${board.title}</td>
                </tr>
                <tr height="100">
                    <td width="20%" align="center"><b>글내용</b></td>
                    <td colspan="3">${fn:replace(board.content, newLineChar,"<br>")}</td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="NoticeList.board?cp=${cpage}&ps=${pagesize}">목록가기</a> |
                        <a href="NoticeEdit.board?idx=${idx}&cp=${cpage}&ps=${pagesize}">편집</a> |
                        <a href="NoticeDelete.board?idx=${idx}&cp=${cpage}&ps=${pagesize}">삭제</a>
                    </td>
                </tr>
            </table>
        </center>
    </div>
</div>
</body>
</html>

