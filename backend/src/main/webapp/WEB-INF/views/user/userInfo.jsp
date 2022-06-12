<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- FontAwesome Link -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
	integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk"
	crossorigin="anonymous" />
<!-- Bootstrap Link -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<!-- CSS Link -->
<link rel="stylesheet" href="/css/reset.css" />
<link rel="stylesheet" href="/css/userInfo.css" />
<link rel="stylesheet" href="/css/secondNav.css" />
<!-- Google Font font-family: 'Lobster', cursive;-->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet" />
<!-- JS link -->
<!-- <title>유저정보 확인</title> -->
</head>
<body>
	<nav>
		<div class="navbar">
			<div class="logo">
				<a href="${root}/"><i class="fas fa-leaf"></i>Happy House</a>
			</div>
			<ul class="menu">
				<li class="menu-item"><a href="${root}/notice/list">공지 사항</a></li>
				<li class="menu-item addBookmark"><a href="#">관심 지역 설정</a></li>
				<li class="menu-item"><a href="${root}/real/info">관심 지역
						둘러보기</a></li>
			</ul>
			<div class="user-account-container">
				<ul class="user-account-btn-list">
					<li class="user-account-btn active"><a href="${root}/account/detail?userId=${userInfo.id}">유저정보</a>
					</li>
				</ul>
			</div>
			<i class="fas fa-bars"></i>
		</div>
	</nav>
	<div id="user-info">
		<form id="user-update-form" method="POST" action="${root}/account/update">
			<ul>
				<li><label for="id">아이디</label> <input class="form-control"
					type="text" id="id" name="id" value="${detailInfo.id}" readonly /></li>
				<li><label for="password">비밀번호</label> <input
					class="form-control" type="text" id="password" name="pw"
					value="" readonly required/></li>
				<li><label for="passwordCheck">비밀번호 확인</label> <input
					class="form-control" type="text" name="pw2" id="passwordCheck" required/></li>
				<li><label for="name">사용자 이름</label> <input
					class="form-control" type="text" id="name" name="name"
					value="${detailInfo.name}" readonly /></li>
				<li><label for="phone">전화번호</label> <input class="form-control"
					type="text" id="phone" value="${detailInfo.phone}" name="phone" readonly /></li>
				<div>
					<button id="confirm" disabled class="btn btn-outline-dark">
						확인</button>
					<a id="modify" class="btn btn-outline-primary">수정</a>
					<button id="delete" class="btn btn-outline-danger">삭제</button>
				</div>
				</li>
			</ul>
		</form>
	</div>
	<!-- <div>
            <button id="confirm" disabled class="btn btn-outline-primary">확인</button>
            <button id="modify" class="btn btn-outline-dark">수정</button>
            <button id="delete" class="btn btn-outline-danger">삭제</button>
        </div> -->
	<!-- jQuery link -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="/js/userInfo.js"></script>
	<script src="/js/secondNav.js"></script>
</body>
</html>
