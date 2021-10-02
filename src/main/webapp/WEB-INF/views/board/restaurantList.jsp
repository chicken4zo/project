<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@page import="kr.or.bit.util.ThePager" %>
<%@page import="kr.or.bit.dto.NoticeBoard" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

게시판 목록
<br>

<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagecount" value="${requestScope.pagecount}"/>
<c:set var="list" value="${requestScope.list}"/>
<c:set var="totalboardcount" value="${requestScope.totalBoardCount}"/>
<c:set var="pager" value="${requestScope.pager}"/>

<div id="pagecontainer">
    <div style="padding-top: 30px; text-align: center">
        <table width="80%" border="1" cellspacing="0" align="center">
            <tr>
                <td colspan="5">
                    <form name="list">
                        PageSize설정: <!-- 한 페이지안에 보여줄 게시글 건수 -->
                        <select name="ps" onchange="submit()">
                            <c:forEach var="i" begin="5" end="20" step="5">
                                <c:choose>
                                    <c:when test="${pagesize == i}">
                                        <option value="${i}" selected>${i}건</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${i}">${i}건</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </form>
                </td>
            </tr>
            <tr>
                <th width="10%">NO</th>
                <th width="40%">제목</th>
                <th width="20%">글쓴이</th>
                <th width="20%">날짜</th>
                <th width="10%">조회수</th>
            </tr>
            <!-- 데이터가 한건도 없는 경우  -->

            <!-- forEach()  목록 출력하기  -->
            <c:forEach var="restaurant" items="${list}">
                <tr onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
                    <td align="center">${restaurant.idx}</td>
                    <td align="left">
                        <a href="RestaurantContent.board?idx=${restaurant.idx}&cp=${cpage}&ps=${pagesize}">
                            <c:choose>
                                <c:when test="${restaurant.title != null && fn:length(restaurant.title) > 10}">
                                    ${fn:substring(restaurant.title,0,10)}...
                                </c:when>
                                <c:otherwise>
                                    ${restaurant.title}
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                    <td align="center">${restaurant.id}</td>
                    <td align="center">${restaurant.writeDate}</td>
                    <td align="center">${restaurant.hit}</td>
                </tr>
            </c:forEach>
            <!-- forEach()  -->
            <tr>
                <td colspan="5" align="center">
                    <hr width="100%" color="red">
                </td>
            </tr>
            <tr>
                <td colspan="3" align="center">

                    <!--이전 링크 -->
                    <c:if test="${cpage > 1}">
                        <a href="RestaurantList.board?cp=${cpage-1}&ps=${pagesize}">이전</a>
                    </c:if>
                    <!-- page 목록 나열하기 -->
                    <c:forEach var="i" begin="1" end="${pagecount}" step="1">
                        <c:choose>
                            <c:when test="${cpage==i}">
                                <font color="red">[${i}]</font>
                            </c:when>
                            <c:otherwise>
                                <a href="RestaurantList.board?cp=${i}&ps=${pagesize}">[${i}]</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <!--다음 링크 -->
                    <c:if test="${cpage < pagecount}">
                        <a href="RestaurantList.board?cp=${cpage+1}&ps=${pagesize}">다음</a>
                    </c:if>
                </td>
                <td colspan="2" align="center">총 게시물 수 : ${totalboardcount}
                </td>
            </tr>
            <tr>
                <td colspan="5" align="center">
                    ${pager}
                </td>
        </table>
        <a href="RestaurantWrite.board">맛있구마 글쓰러가기</a>
    </div>
</div>
</body>
</html>