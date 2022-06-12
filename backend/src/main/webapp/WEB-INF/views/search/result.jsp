<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
<link rel="stylesheet" href="./css/reset.css" />
<link rel="stylesheet" href="./css/secondNav.css" />
<!-- Google Font font-family: 'Lobster', cursive;-->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet" />
<title>Happy House</title>
</head>
<body>
	<nav>
		<div class="navbar">
			<div class="logo">
				<a href="initPage.do"><i class="fas fa-leaf"></i>Happy House</a>
			</div>
			<ul class="menu">
				<li class="menu-item"><a href="notice/list.do?&pg=1&key=&word=">공지 사항</a></li>
				<li class="menu-item addBookmark"><a href="#">관심 지역 설정</a></li>
				<li class="menu-item"><a href="#">관심 지역
						둘러보기</a></li>
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
	<div class="container text-center mt-3">
		<form class="row g-3 " action="search.do"
			style="justify-content: center">
			<div class="col-auto">
				<select name="searchTarget" class="form-select"
					aria-label="Default select example">
					<option value="dongSearch" selected>동 검색</option>
					<option value="aptSearch">아파트 검색</option>
				</select>
			</div>
			<div class="col-auto">
				<label for="keyword" class="visually-hidden">Password</label> <input
					type="text" class="form-control" id="keyword" name="keyword"
					placeholder="검색어 입력" value="${param.keyword}">
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-3">검색하기</button>
			</div>
		</form>
	</div>
	<table class="table table-hover container">
		<thead>
			<tr>
				<td>아파트명</td>
				<td>동</td>
				<td>거래가</td>
				<td>위도</td>
				<td>경도</td>
				<td>거래일</td>
			</tr>
		</thead>
		<c:if test="${not empty searchList}">
			<c:forEach items="${searchList}" var="search">
				<tbody>
					<tr>
						<td>${search.aptName}</td>
						<td>${search.dongName}</td>
						<td>${search.dealAmount}</td>
						<td>${search.lat}</td>
						<td>${search.lng}</td>
						<td>${search.year}.${search.month}.${search.day}</td>
					</tr>
				</tbody>
			</c:forEach>
		</c:if>
		<c:if test="${empty searchList}">
			<tbody>
				<tr style="text-align:center">
					<td colspan="6">등록된 정보가 없습니다.</td>
				</tr>
			</tbody>
		</c:if>

	</table>
</body>
</html>