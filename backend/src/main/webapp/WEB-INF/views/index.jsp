<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				$.ajax({
					url : '${root}/news/api/list',
					type : 'GET',
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					success : function(response) {
						console.log(response);
						console.log(response.news);
						makeList(response.news);
					},
					error : function(xhr, status, error) {
						console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : "
								+ xhr.responseText);
					},
					statusCode : {
						500 : function() {
							alert("서버에러.");
							// location.href = "/error/e500";
						},
						404 : function() {
							alert("페이지없다.");
							// location.href = "/error/e404";
						}
					}
				});
			});
	function makeList(news) {
		$("#news-list").empty();
		$(news)
				.each(
						function(index, newss) {
							let str = `<li><a href=${'${newss.link}'} target="_blank">${'${newss.title}'}</a>${'${newss.publisher}'}</li> `;
							$("#news-list").append(str);
						});//each
	}
</script>
<body>
	<nav>
		<div class="navbar">
			<div class="logo">
				<a href="${root}/"><i class="fas fa-leaf"></i>Happy House</a>
			</div>
			<ul class="menu">
				<!-- servlet 요청으로 수정 해야함 -->
				<li class="menu-item"><a href="${root}/notice/list">공지 사항</a></li>
				<!-- <li class="menu-item"><a href="#news-area">오늘의 뉴스</a></li> -->
				<li class="menu-item addBookmark"><a href="#">관심지역 설정</a></li>
				<li class="menu-item showBookmark"><a href="${root}/real/info">실거래가
						조회하기</a></li>
			</ul>
			<div class="user-account-container">
				<ul class="user-account-btn-list">
					<c:choose>
						<c:when test="${empty userInfo}">
							<li class="user-account-btn active" data-bs-toggle="modal"
								data-bs-target="#signUpModal">회원가입</li>
							<li class="user-account-btn active" data-bs-toggle="modal"
								data-bs-target="#loginModal">로그인</li>
						</c:when>
						<c:otherwise>
							<li class="user-account-btn active logout-btn"><a
								href="${root}/account/logout">로그아웃</a></li>
							<li class="user-account-btn active"><a
								href="${root}/account/detail?userId=${userInfo.id}">유저정보</a></li>
						</c:otherwise>
					</c:choose>
					<li class="search"><i class="fas fa-search"></i></li>
				</ul>
			</div>
			<i class="fas fa-bars"></i>
		</div>
		<div class="searchWrap">
			<form method="POST" id="searchForm" action="/real/info">
				<input type="text" id="keywordSearch" name="keyword" autocomplete='off'/>
				<div id="recommend" class="invisible">
			</form>
		</div>
		</div>
	</nav>
	<%-- <form action="${root}/real/info" class="searchForm">
		<input hidden type="text" class="search-input" name="searchTarget"
			id="searchTarget" value="dongSearch" /> <input type="text"
			class="search-input" name="keyword" id="keyword" />
		<div id="recommend">
			<div class="item">
				<span>안녕하세요.</span>
			</div>
			<div class="item">
				<span>반갑습니다.</span>
			</div>
			<div class="item">
				<span>잘가세요.</span>
			</div>
		</div>
	</form> --%>
	<div class="cover">
		<div class="cover-title">
			<p>Happy House</p>
		</div>
		<img class="cover-img" src="/img/happyhouse_cover.jpg" />
	</div>
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
	<div class="row map-wrap">
		<div class="col">
			<div id="map" style="width: 100%; height: 500px; margin: auto"
				class="mb-3"></div>
		</div>
		<div class="col-4 apt-info" style="display: none">
			<div class="list-group" id="info"></div>
		</div>
	</div>
	<div class="content">
		<ul class="content-list">
			<li>
				<div class="ad-container">
					<div class="ad">
						<p class="ad-title">SSAFY 고지서 신청하고</p>
						<br />
						<p class="ad-title">포인트 받으세요!</p>
						<p class="hash-tag">#100만원 당첨 기회</p>
						<p class="hash-tag">#재산세 #주민세</p>
					</div>
				</div>
			</li>
			<li>
				<div class="tip-container">
					<p>지혜롭게 내집 마련하기</p>
					<ul class="tip-list">
						<li><i class="fas fa-won-sign"></i>&nbsp;가용자금 확인 및 대출 계획</li>
						<li><i class="fas fa-home"></i>&nbsp;집 종류 및 지역 선택</li>
						<li><i class="fas fa-chart-line"></i>&nbsp;정보 수집 & 시세파악</li>
						<li><i class="fas fa-pen"></i>&nbsp;계약 및 잔금 치르기</li>
						<li><i class="fas fa-cog"></i>&nbsp;인테리어 공사</li>
					</ul>
				</div>
			</li>
			<li>
				<div class="news-container">
					<p id="news-area">오늘의 뉴스</p>
					<ul class="news-list" id="news-list">
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<!-- Modal -->
	<%@ include file="/WEB-INF/views/include/signUpModal.jsp"%>
	<%@ include file="/WEB-INF/views/include/loginModal.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a64d28290adc97c74e441e113c6f39e&libraries=services"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a64d28290adc97c74e441e113c6f39e"></script>
	<script src="/js/mapKakao.js"></script>
	<script src="/js/main.js"></script>
</body>
</html>
