<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../css/secondNav.css" />
<link rel="stylesheet" href="../css/addBookmark.css" />
<!-- Google Font font-family: 'Lobster', cursive;-->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet" />
<!-- JS link -->

<title>유저정보 확인</title>
</head>
<body>
	<nav>
		<div class="navbar">
			<div class="logo">
				<a href="initPage.do"><i class="fas fa-leaf"></i>Happy House</a>
			</div>
			<ul class="menu">
				<li class="menu-item"><a href="./notice.html">공지 사항</a></li>
				<li class="menu-item addBookmark"><a href="#">관심 지역 설정</a></li>
				<li class="menu-item"><a href="showInterest.jsp">관심 지역 둘러보기</a></li>
			</ul>
			<div class="user-account-container">
				<ul class="user-account-btn-list">
					<li class="user-account-btn active"><a href="detail.do?userId=${userInfo.id}">유저정보</a>
					</li>
				</ul>
			</div>
			<i class="fas fa-bars"></i>
		</div>
	</nav>
	<div id="notice">
		<p>선호 지역을 선택하세요!</p>
	</div>
	<div id="bookmark">
		<!--local storage로 북마크 저장-->
		<div class="dropdown-container">
			<select class="form-select" id="sido"
				aria-label="Default select example">
				<option selected>도/광역시</option>
			</select> <select class="form-select" id="gugun"
				aria-label="Default select example">
				<option selected>시/구/군</option>
			</select> <select class="form-select" id="dong"
				aria-label="Default select example">
				<option selected>동/읍/면</option>
			</select>
		</div>
		<div style="display: flex; justify-content: center;">
			<button type="button" class="btn btn-primary btn-lg btn-block">확인</button>
		</div>
		<!-- jQuery link -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="./js/secondNav.js"></script>
		<script src="./js/addBookmark.js"></script>
</body>
</html>
