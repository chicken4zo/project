<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--header-->
<header>
    <div id="header_wrap">
        <img id="logo_img" src="assets/images/gogu_logo.png" alt="logo" onclick="location.href='index.html';"
             style="cursor:pointer;">
        <div id="header_search">
            <div class="sel sel--black-panther">
                <select name="select-profession" id="select-profession">
                    <option value="" disabled>게시판</option>
                    <option value="1">상품</option>
                    <option value="2">일상</option>
                    <option value="3">분실·실종</option>
                    <option value="4">반려동물</option>
                </select>
            </div>
            <div class="search-form">
                <input type="text" name="search" value="Search" onclick="this.value=''; ">
                <button type="submit" class="btn btn-search fa fa-search"></button>
            </div>
        </div>
        <div id="header_nav">
            <ul>
                <a href="#">
                    <li>상품등록</li>
                </a>
                <a href="#">
                    <li>우리동네</li>
                </a>
                <a href="#">
                    <li>구마차트</li>
                </a>
                <a href="#">
                    <li>공지사항</li>
                </a>
            </ul>
            <a href="Register.member">
                <div class="register_btn">Register</div>
            </a>
            <a href="Login.member">
                <div class="myPage_btn">My GUMA</div>
            </a>
        </div>
    </div>
</header>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



