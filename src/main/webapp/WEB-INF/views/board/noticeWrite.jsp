<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글쓰기</title>
    <SCRIPT type="text/javascript">
        function check(){
            if(!notice.title.value){
                alert("제목을 입력하세요");
                notice.title.focus();
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
            document.notice.submit();
        }
    </SCRIPT>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>


<div id="pageContainer">
    <div style="padding-top: 25px; text-align: center">
        <!-- form 시작 ---------->
        <form name="notice" action="NoticeWriteOk.board" method="POST">
            <table width="95%" border="2" align="center">
                <tr>
                    <td width="20%" align="center">제목</td>
                    <td width="80%" align="left"><input type="text" name="title" size="40"></td>
                </tr>
                <tr>
                    <td width="20%" align="center">글쓴이</td>
                    <td width="80%" align="left"><input type="text" name="writer" size="40"></td>
                </tr>
                <tr>
                    <td width="20%" align="center">글내용</td>
                    <td width="80%" align="left"><textarea rows="10" cols="60" name="content"></textarea></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="button" value="글쓰기" onclick="check();"/>
                        <input type="reset"  value="다시쓰기" />
                    </td>
                </tr>
            </table>
        </form>

    </div>
</div>
</body>
</html>
