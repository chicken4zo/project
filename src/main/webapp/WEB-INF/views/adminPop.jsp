<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!-- sweet alert -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<%--<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>--%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.7/dist/sweetalert2.all.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<%--<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.7/dist/sweetalert2.all.min.js"></script>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="msg" value="${requestScope.msg}"/>
<c:set var="url" value="${requestScope.url}"/>

&nbsp;

<%--관리자 회원탈퇴기능으로 수정--%>
<%--<c:choose>--%>
<%--<c:when test="${url == 'Delete.member'}">--%>
<script>
    <%--swal({--%>
    <%--        icon: 'success',--%>
    <%--        title: '회원탈퇴 성공',--%>
    <%--        text: '${msg}'--%>
    <%--    }).then(function () {--%>
    <%--    window.location.href = '${url}';--%>
    <%--    swal.close();--%>
    <%--});--%>

    Swal.fire({
	    title: "정말 삭제하겠습니까?",
        text: "탙퇴시킨 회원은 복구가 불가능합니다!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                swal("회원 삭제 완료!", {
                    icon: "success",
                });
            } else {
                swal("회원 삭제 취소!");
            }
        });

</script>
<%--</c:when>--%>
<%--    <c:otherwise>--%>
<%--        <script>--%>
<%--                swal({--%>
<%--                    icon: 'error',--%>
<%--                    title: '회원탈퇴 실패',--%>
<%--                    text: '${msg}'--%>
<%--                }).then(function () {--%>
<%--                    window.location.href = '${url}';--%>
<%--                    swal.close();--%>
<%--                });--%>
<%--        </script>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>

