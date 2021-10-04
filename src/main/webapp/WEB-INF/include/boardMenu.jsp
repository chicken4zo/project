<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- dropdown -->
<div class="dropdown">
    <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
        <i class="fas fa-bars"></i>
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="productList.board">상품구마</a>
        <a class="dropdown-item" href="dailyList.board">일상구마</a>
        <a class="dropdown-item" href="RestaurantList.board">맛있구마</a>
        <a class="dropdown-item" href="petList.board">반려동물</a>
        <a class="dropdown-item" href="lostList.board">분실·실종</a>
    </div>
</div>
