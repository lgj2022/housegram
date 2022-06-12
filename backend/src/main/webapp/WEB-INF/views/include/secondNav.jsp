<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<nav>
      <div class="navbar">
        <div class="logo">
          <a href="${root}/"><i class="fas fa-leaf"></i>Happy House</a>
        </div>
        <ul class="menu">
          <li class="menu-item"><a href="${root}/notice/list">공지 사항</a></li>
          <li class="menu-item addBookmark"><a href="#">관심 지역 설정</a></li>
          <li class="menu-item">
            <a href="${root}/real/info">실거래가 조회하기</a>
          </li>
        </ul>
        <div class="user-account-container">
          <ul class="user-account-btn-list">
            <!-- <li class="user-account-btn active logout-btn">
              <a href="">로그아웃</a>
            </li> -->
            <li class="user-account-btn active">
              <a href="${root}/account/detail?userId=${userInfo.id}">유저정보</a>
            </li>
          </ul>
        </div>
        <i class="fas fa-bars"></i>
      </div>
</nav>