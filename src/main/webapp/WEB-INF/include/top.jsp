<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--header-->
<header>
    <div id="header_wrap">
        <img id="logo_img" src="${pageContext.request.contextPath}/assets/images/gogu_logo.png" alt="logo"
             onclick="location.href='index.jsp';"
             style="cursor:pointer;">
        <div id="header_search">
            <%--            <div class="sel sel--black-panther">--%>
            <%--                <select name="select-profession" id="select-profession" onchange="location.href=this.value">--%>
            <%--                        <option value="" disabled>게시판</option>--%>
            <%--                        <option value="http://www.webmadang.net">상품</option>--%>
            <%--                        <option value="2">일상</option>--%>
            <%--                        <option value="3">분실·실종</option>--%>
            <%--                        <option value="4">반려동물</option>>--%>
            <%--                    </select>--%>
            <%--            </div>--%>

            <select class="selectpicker" data-width="fit" onchange="location.href=this.value">
                <option value="" disabled style="color: black; font-size: 0.89rem">게시판</option>
                <option value="productList.board">상품</option>
                <option value="dailyList.board">일상</option>
                <option value="lostList.board">분실·실종</option>
                <option value="petList.board">반려동물</option>
            </select>


            <div class="search-form">
                <input type="text" name="search" value="Search" onclick="this.value=''; ">
                <button type="submit" class="btn btn-search fa fa-search"></button>
            </div>
        </div>
        <div id="header_nav">
            <ul>
                <a href="${pageContext.request.contextPath}/productForm.board">
                    <li>상품등록</li>
                </a>
                <a href="${pageContext.request.contextPath}/lostList.board">
                    <li>우리동네</li>
                </a>
                <a href="${pageContext.request.contextPath}/lostForm.board">
                    <li>구마차트</li>
                </a>
                <a href="#">
                    <li>공지사항</li>
                </a>
            </ul>
            <a href="${pageContext.request.contextPath}/Register.member">
                <div class="register_btn">Register</div>
            </a>
            <a href="${pageContext.request.contextPath}/Login.member">
                <div class="myPage_btn">Log In</div>
            </a>
        </div>
    </div>
</header>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajaz/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
<script>
    $(document).ready(function () {
        $('select').niceSelect();
    });
</script>



