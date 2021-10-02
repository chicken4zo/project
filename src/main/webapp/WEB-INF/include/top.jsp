<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${sessionScope.id}" var="id"/>
<!--header-->
<header>
    <div id="header_wrap">
        <img id="logo_img" src="${pageContext.request.contextPath}/assets/images/gogu_logo.png" alt="logo"
             onclick="location.href='index.jsp';"
             style="cursor:pointer;">

        <div id="header_search">
            <%--            <c:if test="${not empty id}">--%>

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
            <%--            </c:if>--%>

        </div>
        <div id="header_nav">

            <%--            <c:if test="${not empty id}">--%>

            <ul>
                <a href="${pageContext.request.contextPath}/productForm.board">
                    <li>상품등록</li>
                </a>
                <a href="${pageContext.request.contextPath}/lostList.board">
                    <li>우리동네</li>
                </a>
                <a href="${pageContext.request.contextPath}/productContent.board">
                    <li>구마차트</li>
                </a>
                <a href="${pageContext.request.contextPath}/productForm.board">
                    <li>공지사항</li>
                </a>
            </ul>
            <%--            </c:if>--%>

            <div id="loginlogout">
            </div>

            <%--login Logout Ajax--%>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
            </script>
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