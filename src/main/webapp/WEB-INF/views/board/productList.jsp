<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="productBoardList" value="${requestScope.productBoardList}"/>
<c:set var="pagesize" value="${requestScope.pagesize}"/>
<c:set var="cpage" value="${requestScope.cpage}"/>
<c:set var="pagecount" value="${requestScope.pagecount}"/>
<c:set var="pager" value="${requestScope.pager}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>고민하지말구, 고구마켓</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon-16x16.png">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
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

        <!--sub_nav-->
        <section class="navbar navbar-icon-top navbar-expand-lg sub_nav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active nav_li">

                    <a class="nav-link" href="#">
                        <i class="fa fa-tshirt sub_icon"></i>
                        <span>의류 & 신발</span>
                    </a>

                </li>
                <li class="nav-item">

                    <a class="nav-link" href="#">
                        <i class="fa fa-bone sub_icon"></i>
                        <span>반려동물</span>
                    </a>

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-utensils sub_icon"></i>
                        <span>주방용품</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-dumbbell sub_icon"></i>
                        <span>스포츠 & 생활</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-laptop sub_icon"></i>
                        <span>전자제품</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-baby-carriage sub_icon"></i>
                        <span>육아용품</span>
                    </a>
                </li>
            </ul>
        </section>
        <%--weather--%>
        <jsp:include page="/WEB-INF/include/weather.jsp"/>

        <!--content-->
        <section id="content">
            <c:forEach var="product" items="${productBoardList}">
                <div class="content_wrap">
                    <a href="productContent.board?idx=${product.idx}&id=${product.id}&cp=${cpage}&ps=${pagesize}">
                        <div class="product_wrap">
                            <div class="product_img_wrap">
                                <img src="${pageContext.request.contextPath}/assets/upload/${product.fileName1}">
                                <ul class="product_detail">
                                    <li><i class="fas fa-user"></i>${product.id}</li>
                                    <li><i class="far fa-calendar-alt"></i>${product.writeDate}</li>
                                    <li><i class="fas fa-map-marker-alt"></i>${product.address}</li>
                                </ul>
                            </div>
                            <div class="product_description">
                                <h1>${product.title}</h1>
                                <h2>${product.price}원</h2>
                                <div class="bar"></div>
                                <p>
                                    <c:choose>
                                        <c:when test="${product.content.length() > 100}">
                                            ${product.content.substring(0,60)}...
                                        </c:when>
                                        <c:otherwise>
                                            ${product.content}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <div class="deal">거래중</div>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>


            <!-- 지우기 ..-->

            <%--<div class="content_wrap">
                <a href="#">
                    <div class="product_wrap">
                        <div class="product_img_wrap">
                            <img src="${pageContext.request.contextPath}/assets/images/ex_img.jpg">
                            <ul class="product_detail">
                                <li><i class="fas fa-user"></i> ID</li>
                                <li><i class="far fa-calendar-alt"></i> 2021. 10. 11</li>
                            </ul>
                        </div>
                        <div class="product_description">
                            <h1>제목</h1>
                            <h2>50,000원</h2>
                            <div class="bar"></div>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad eum dolorum architecto
                                obcaecati enim dicta praesentium, quam nobis!
                            </p>
                            <div class="deal">거래중</div>
                        </div>
                    </div>
                </a>
            </div>


            <div class="content_wrap">
                <a href="#">
                    <div class="product_wrap">
                        <div class="product_img_wrap">
                            <img src="${pageContext.request.contextPath}/assets/images/ex_img.jpg">
                            <ul class="product_detail">
                                <li><i class="fas fa-user"></i> ID</li>
                                <li><i class="far fa-calendar-alt"></i> 2021. 10. 11</li>
                            </ul>
                        </div>
                        <div class="product_description">
                            <h1>제목</h1>
                            <h2>50,000원</h2>
                            <div class="bar"></div>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad eum dolorum architecto
                                obcaecati enim dicta praesentium, quam nobis!
                            </p>
                            <div class="deal">거래중</div>
                        </div>
                    </div>
                </a>
            </div>--%>
        </section>
        <!-- 지우기 ..-->
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                ${pager}
            </ul>
        </nav>
    </div>


</div>
<jsp:include page="/WEB-INF/include/footer.jsp"/>

</body>
<!--bootstrp js-->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/index.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>