<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!-- sweet alert -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<%--<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>--%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="msg" value="${requestScope.msg}"/>
<c:set var="url" value="${requestScope.url}"/>

&nbsp;

<c:choose>
    <c:when test="${msg == '삭제가 완료되었습니다.'}">
        <script>
            swal.mixin({
                toast: true,
                icon: 'success',
                title: '게시글 삭제 성공',
                animation: true,
                position: 'top-right',
                showConfirmButton: false,
                // title: '',
                <%--text: '${msg}'--%>
            }).then(function () {
                window.location.href = '${url}';
                swal.close();
            });
        </script>
    </c:when>
    <c:otherwise>
        <script>
            swal.mixin({
                toast: true,
                icon: 'error',
                title: '게시글 삭제 실패',
                animation: true,
                position: 'top-right',
                showConfirmButton: false,
                <%--text: '${msg}'--%>
            }).then(function () {
                window.location.href = '${url}';
                swal.close();
            });
        </script>
    </c:otherwise>
</c:choose>

