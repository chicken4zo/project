<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>board : content</title>
  <link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css"/>
  <!-- js -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<c:set var="idx" value="${requestScope.idx}"></c:set>
<c:set var="board" value="${requestScope.board}"></c:set>
<c:set var="cpage" value="${requestScope.cpage}"></c:set>
<c:set var="pagesize" value="${requestScope.pagesize}"></c:set>
<script type="text/javascript">
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
          <td width="20%" align="center">미리보기</td>
          <td width="80%" align="left"><img id="preview" src="" width="300" alt=""></td>
        </tr>
        <tr>
          <!-- 첨부파일 <file> -->
          <td width="20%" align="center"><b>첨부파일</b></td>
          <td>
            <c:set var="originalfilename" value="${board.fileName}"/>
            <c:set var="lowerfilename" value="${fn:toLowerCase(originalfilename)}"/>
            <c:forTokens var="file" items="${lowerfilename}" delims="." varStatus="status">
              <c:if test="${status.last}">
                <c:choose>
                  <c:when test="${empty board.fileName || board.fileName eq 'null'}">
                    첨부파일이 없습니다
                  </c:when>
                  <c:when test="${file eq 'jpg' || file eq 'png' || file eq 'gif'}">
                    <a href="upload/${originalfilename}" target="_blank">이미지 클릭해서 보기</a>
                  </c:when>
                  <c:otherwise>
                    <a href="filedownload.board?fileName=${originalfilename}" id="download">${originalfilename}</a>
                  </c:otherwise>
                </c:choose>
              </c:if>
            </c:forTokens>
          </td>
          <!-- 첨부파일 <file> -->
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
            <a href="list.board?cp=${cpage}&ps=${pagesize}">목록가기</a> |
            <a href="edit.board?idx=${idx}&cp=${cpage}&ps=${pagesize}">편집</a> |
            <a href="delete.board?idx=${idx}&cp=${cpage}&ps=${pagesize}">삭제</a> |
            <a href="rewrite.board?idx=${idx}&cp=${cpage}&ps=${pagesize}&subject=${board.title}">답글</a>
          </td>
        </tr>
      </table>
      <!--  꼬리글 달기 테이블 -->
      <form name="reply" method="POST">
        <!-- hidden 태그  값을 숨겨서 처리  -->
        <input type="hidden" name="idx" value="${idx}" id="idx">
        <input type="hidden" name="userid" value="">
        <!-- 추후 필요에 따라  -->
        <!-- hidden data -->
        <table width="80%" border="1">
          <tr>
            <th colspan="2">덧글 쓰기</th>
          </tr>
          <tr>
            <td align="left">작성자 : <input type="text"
                                          name="id" id="id"><br/> 내&nbsp;&nbsp;용 : <textarea
                    name="comment" rows="2" cols="50" id="comment"></textarea>
            </td>
            <td align="right">댓글등록 <input type="button" value="등록" id="commentbtn">
            </td>
          </tr>
        </table>
      </form>
      <!-- 유효성 체크	 -->
      <br>
      <!-- 꼬리글 목록 테이블 -->
      <table width="80%" border="1">
        <thead>
        <tr>
          <th colspan="2">COMMENT LIST</th>
        </tr>
        <thead>
        <tbody id="tbody">
        <tr align="left">
          <td>등록된 댓글이 없습니다</td>
        </tr>
        </tbody>
      </table>
    </center>
  </div>
</div>
</body>
<script type="text/javascript">


  $(function () {
    replyList();
    replyAdd();
  });

  function replyList() {
    $.ajax({
      url: "Replylist",
      type: 'GET',
      dataType: "json",
      data: {
        idx: $('#idx').val()
      },
      success: function (data) {

        $('#tbody').empty();
        if (data.length == 0) {
          $('#tbody').append('<tr align="left"><td>등록된 댓글이 없습니다</td></tr>');

        } else {
          $.each(data, function (index, obj) {
            $('#tbody').append('<tr align="left"><td width="80%">['
                    + obj.writer + '] : ' + obj.content
                    + '<br> 작성일 :' + obj.writedate + '</td><td width="20%">'
                    + '<form method="POST" name="replyDel">'
                    + '<input type="hidden" name="no" value="' + obj.no + '" class="comment_no">'
                    + '<input type="hidden" name="idx" value="' + obj.idx_fk + '" class="comment_idx">'
                    + ' <input type="button" value="삭제" onclick="comment_del(this.form)">'
                    + '</form></td></tr>');
          });
        }
      },
      error: function () {
        alert('댓글 로드 실패');
      }
    });

  }

  function replyAdd() {
    $('#commentbtn').click(function () {

      var frm = document.reply;
      if (frm.comment_content.value == "") {
        alert("댓글 내용을 입력해주세요.");
        return false;
      }

      $.ajax({
        url: "commentAdd",
        type: 'POST',
        data: {
          "comment_content": $('#comment_content').val(),
          "idx": $('#idx').val()
        },
        success: function (data) {
          commentList();
          $('#comment_content').val("");

        },
        error: function () {
          alert('댓글 등록 실패');
        }
      });
    });
  }

  function reply_del(frm) {

    //console.log(frm);

    $.ajax({
      url: "Commentdelete",
      type: 'POST',
      datatype: "text",
      data: {

        "no": frm.no.value,
        "idx_fk": frm.idx.value
      },
      success: function (data) {
        commentList();
      },
      error: function () {
        alert('댓글 삭제 실패');
      }
    });
  }

</script>
</html>

