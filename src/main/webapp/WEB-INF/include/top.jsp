<div class="sel sel--black-panther">
    <select name="select-profession" id="select-profession" onchange="window.open(value, '_self');">
        <option value="" disabled>게시판</option>
        <option value="${pageContext.request.contextPath}/productForm.board">상품</option>
        <option value="2">일상</option>
        <option value="3">분실·실종</option>
        <option value="4">반려동물</option>
    </select>
</div>
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