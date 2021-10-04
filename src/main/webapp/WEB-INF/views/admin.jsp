<%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/27
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:set var="memberlist" value="${requestScope.memberList}"/>--%>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagecount" value="${requestScope.pagecount}"/>
<c:set var="pager" value="${requestScope.pager}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
    <!-- favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <!-- bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- fontawesome  -->
    <script src="https://kit.fontawesome.com/a959489452.js" crossorigin="anonymous"></script>
    <!--font-->
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <!--weather icon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/weather-icons/1.2/css/weather-icons.min.css">
</head>
<body>

<div id="body_wrap">
    <div class="wrapper">
        <!--header-->
        <jsp:include page="/WEB-INF/include/top.jsp"/>
        <!-- weather -->
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <div class="board-logo">
            <h3>관리구마</h3>
        </div>
        <form class="form" id="deleteForm">
            <div class="list">
                <table>
                    <thead>
                    <tr>
                        <th width="4%">
                            <div class="checks small"><input type="checkbox" onclick='selectAll(this)' id="check"><label
                                    for="check"></label></div>
                        </th>
                        <th width="32%">아이디</th>
                        <th width="32%">이름</th>
                </tr>
                <c:set var="memberlist" value="${requestScope.memberList}"/>
                <c:choose>
                    <c:when test="${not empty memberlist}">
                    <c:forEach var="member" items="${memberlist}" varStatus="status">
                    <tr>
                        <td>
                            <div class="checks small"><input type="checkbox" class="checkbox" id="check${status.count}"
                                                             name="id" value="${member.id}"><label
                                    for="check${status.count}"></label></div>
                        </td>
                        <td>${member.id}</td>
                        <td>${member.name}</td>
                    </tr>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                    <td></td>
                    <td>등록되 회원이 없습니다</td>
                    </c:otherwise>
                </c:choose>
                </table>
            </div>
            <button class="write-btn" id="deleteBtn">회원삭제</button>
        </form>
        <div class="modal detailModal">
            <div class="modal-content detailModalContent">
            </div>
        </div>
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                ${pager}
            </ul>
        </nav>
    </div>
</div>
<jsp:include page="/WEB-INF/include/footer.jsp"/>

</body>
<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/bootstrap-material-design.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
</body>
<!--bootstrp js-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.7/dist/sweetalert2.all.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>

<!--image js-->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css"></script>

<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>
<script type="text/javascript">

	const formData = $('.deleteForm').serialize();

	$('#deleteBtn').click(function (e) {
		e.preventDefault();
		Swal.fire({
			title: "정말 삭제하겠습니까?",
			text: "탙퇴시킨 회원은 복구가 불가능합니다!",
			icon: "warning",
			showCancelButton: true,
			confirmButtonText: '삭제',
			cancelButtonText: '취소'
		}).then((willDelete) => {
			if (willDelete.isConfirmed) {
				console.log("delete");
				// let lists = [];
				// $('input[name="subject"]:checked').each(function (i) {
				//     lists.push($(this).val());
				// });
				// jQuery.ajaxSettings.traditional = true;
				let formData = $('#deleteForm').serialize();
				$.ajax({
					url: "Delete.member",
					type: "POST",
					data: formData,
					success: function (data) {
						Swal.fire("회원 삭제 완료!", {
							icon: "success",
						});
						location.href = "Admin.member";
					}
				});
			} else {
				Swal.fire("회원 삭제 취소");
			}
		});
	});
</script>
</html>
