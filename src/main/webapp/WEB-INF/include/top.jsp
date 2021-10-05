<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${sessionScope.id}" var="id"/>
<!--header-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.7/dist/sweetalert2.all.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<header>
    <div id="header_wrap">
        <c:choose>
            <c:when test="${not empty id}">
                <img id="logo_img" src="${pageContext.request.contextPath}/assets/images/gogu_logo.png" alt="logo"
                     onclick="location.href='productList.board';"
                     style="cursor:pointer;">
            </c:when>
            <c:otherwise>
                <img id="logo_img" src="${pageContext.request.contextPath}/assets/images/gogu_logo.png" alt="logo"
                     onclick="location.href='index.jsp';"
                     style="cursor:pointer;">
            </c:otherwise>
        </c:choose>

        <div id="header_search">
            <c:if test="${not empty id}">

                <label>
                    <select class="selectpicker" data-width="fit">
                        <option value="" style="color: black; font-size: 0.89rem">게시판</option>
                        <option value="product">상품</option>
                        <option value="daily">일상</option>
                        <option value="lost">분실·실종</option>
                        <option value="pet">반려동물</option>
                        <option value="restaurant">맛있구마</option>
                    </select>
                </label>

                <div class="search-form">
                    <input type="text" name="search" id="searchText" value="Search" onclick="this.value=''; ">
                    <button id="searchBtn" class="btn btn-search fa fa-search"></button>
                </div>
            </c:if>

        </div>
        <div id="header_nav">

            <c:if test="${not empty id}">

                <ul>
                    <a href="${pageContext.request.contextPath}/productForm.board">
                        <li>상품등록</li>
                    </a>
                    <a href="${pageContext.request.contextPath}/dailyList.board">
                        <li>우리동네</li>
                    </a>
                    <a href="${pageContext.request.contextPath}/chartView.comment">
                        <li>구마차트</li>
                    </a>
                    <a href="${pageContext.request.contextPath}/NoticeList.board">
                        <li>공지사항</li>
                    </a>
                </ul>
            </c:if>

            <div id="loginlogout">
            </div>

            <%--login Logout Ajax--%>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>


            <script type="text/javascript">
                $.ajax(
                    {
                        url: "LoginLogout",
                        type: "GET",
                        dataType: "html",
                        success: function (responseData) {
                            document.getElementById("loginlogout").innerHTML = responseData;
                        },
	                    error: function (xhr) {
		                    console.log(xhr.status);
	                    }
                    }
                );

                $(document).ready(function () {
	                $('select').niceSelect();
                });

                $('#searchBtn').click(function (e) {
	                e.preventDefault();
	                e.stopPropagation();
	                const boardName = $('.selectpicker').val();
	                const searchText = $('#searchText').val();
	                if (boardName === "" || boardName === null || searchText === "" || searchText === null || searchText === "Search") {
		                Swal.fire(
			                '검색할 내용이 없습니다',
			                '게시판 및 검색 내용을 입력해 주세요',
			                'error'
		                )
	                } else {
		                location.href = "search.board?boardName=" + boardName + "&searchText=" + searchText;
	                }
                });
            </script>

        </div>
    </div>
</header>

