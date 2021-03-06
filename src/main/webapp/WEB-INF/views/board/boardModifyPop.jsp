<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!-- sweet alert -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<%--<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>--%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.7/dist/sweetalert2.all.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="msg" value="${requestScope.msg}"/>
<c:set var="url" value="${requestScope.url}"/>

&nbsp;

<c:choose>
    <c:when test="${msg == 'success'}">
        <script>
		    Swal.fire({
			    toast: true,
			    icon: 'success',
			    title: '수정 성공',
			    animation: true,
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
		    Swal.fire({
			    toast: true,
			    icon: 'error',
			    title: '수정 실패',
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

