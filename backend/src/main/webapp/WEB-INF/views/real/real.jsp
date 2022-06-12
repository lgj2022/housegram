<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Framework Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/img/favicon.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/apt.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/reset.css" />
<link rel="stylesheet" href="/css/secondNav.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
	integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk"
	crossorigin="anonymous" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Cookie&family=Great+Vibes&family=Lobster&display=swap"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/secondNav.jsp" />
	<div class="container">
		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">
					<div class="form-group form-inline justify-content-center">
						<!-- <select name="searchTarget" class="form-control"
							aria-label="Default select example">
							<option value="dongSearch" selected>동 검색</option>
							<option value="aptSearch">아파트 검색</option>
						</select> -->
						<select name="searchTarget" class="form-control"
							aria-label="Default select example" id="searchSelect">
							<option value="apt">아파트 검색</option>
							<option value="dong">동 검색</option>

						</select>
						<div class="col-auto">
							<input type="text" class="form-control" id="keyword"
								name="keyword" placeholder="검색어 입력" value="${param.keyword}">
						</div>
						<div class="col-auto">
							<button class="btn btn-primary searchBtn">검색하기</button>
						</div>
						<label class="mr-2" for="sido">시도 : </label> <select
							class="form-control" id="sido">
							<option value="0">선택</option>
						</select> <label class="mr-2 ml-3" for="gugun">구군 : </label> <select
							class="form-control" id="gugun">
							<option value="0">선택</option>
						</select> <label class="mr-2 ml-3" for="dong">읍면동 : </label> <select
							class="form-control" id="dong">
							<option value="0">선택</option>
						</select>
						<!-- <button type="button" id="aptSearchBtn">검색</button> -->
					</div>
					<table class="table mt-2">
						<colgroup>
							<col width="100">
							<col width="150">
							<col width="*">
							<col width="120">
							<col width="120">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>아파트이름</th>
								<th class="text-center">주소</th>
								<th>건축연도</th>
								<th>최근거래금액</th>
							</tr>
						</thead>
						<tbody id="searchResult"></tbody>
					</table>
					<div id="map" style="width: 100%; height: 500px;"></div>
					<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=  *****   Kakao map API KEY ***** &libraries=services"></script> -->
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e6756d35bad09a04e639727ae6e51a0e&libraries=services"></script>
					<!-- <script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=FBcNOTDKCffi884b%2FbAJHUswz0w5sQ92oQ9hhwGXtVm0khubm14kdtIWrP7VRKkJNal6Nh0w8hROdDLQbbH3QQ%3D%3D&libraries=services"></script> -->
					<script type="text/javascript" src="/js/map.js"></script>
					<script type="text/javascript">
				let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){
					$.get(root + "/map/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});
						}
						, "json"
					);
				});
				$(document).on("change", "#sido", function() {
					$.get(root + "/map/gugun"
							,{sido: $("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#gugun", function() {
					$.get(root + "/map/dong"
							,{gugun: $("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#dong", function() {
					$.get(root + "/map/apt"
							,{dong: $("#dong").val()}
							,function(data, status){
								$("tbody").empty();
								$.each(data, function(index, vo) {
									let str = `
										<tr class="${colorArr[index%3]}">
										<td>${"${vo.aptCode}"}</td>
										<td>${"${vo.aptName}"}</td>
										<td>${"${vo.sidoName}"} ${"${vo.gugunName}"} ${"${vo.dongName}"} ${"${vo.jibun}"}</td>
										<td>${"${vo.buildYear}"}</td>
										<td>${"${vo.recentPrice}"}</td>
									`;
									$("tbody").append(str);
								});
								if(data.length != 0){
									displayMarkers(data);	
								}else{
									removeMarker();
								}
							}
							, "json"
					);
				});
				const searchBtn = document.querySelector(".searchBtn");
				let searchKeyword = '<c:out value="${keyword}"/>'
				if(searchKeyword != ''){
					searchData(searchKeyword, "apt")
				}
				
				searchBtn.addEventListener("click", e=>{
					let type = document.querySelector("#searchSelect").value;
					let keyword = document.querySelector("#keyword").value;
					searchData(keyword, type);
				})
				function searchData(keyword, type){
					$.post(`${root}/map/search/${"${type}"}`,{
						"keyword": keyword
					}
							,function(data, status){
								$("tbody").empty();
								$.each(data, function(index, vo) {
									let str = `
										<tr class="${colorArr[index%3]}">
										<td>${"${vo.aptCode}"}</td>
										<td>${"${vo.aptName}"}</td>
										<td>${"${vo.sidoName}"} ${"${vo.gugunName}"} ${"${vo.dongName}"} ${"${vo.jibun}"}</td>
									
										<td>${"${vo.buildYear}"}</td>
										<td>${"${vo.recentPrice}"}</td>
									`;
									$("tbody").append(str);
								});
								if(data.length != 0){
									displayMarkers(data);
								}else{
									removeMarker();
								}
									
							}
							, "json"
					);
				}
				
				/*
				$(document).on("click", "#aptSearchBtn", function() {
					alert("검색");
					var param = {
							serviceKey:' *** APT 상세 매매 정보 API KEY *** ',
							pageNo:encodeURIComponent('1'),
							numOfRows:encodeURIComponent('10'),
							LAWD_CD:encodeURIComponent($("#gugun").val()),
							DEAL_YMD:encodeURIComponent('202110')
					};
					$.get('http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev'
							,param
							,function(data, status){
						console.log(data);
								var items = $(data).find('item');
								var jsonArray = new Array();
								items.each(function() {
									var jsonObj	= new Object();
									jsonObj.aptCode = $(this).find('일련번호').text();
									jsonObj.aptName = $(this).find('아파트').text();
									jsonObj.dongCode = $(this).find('법정동읍면동코드').text();
									//jsonObj.dongName = $(this).find('').text();
									//jsonObj.sidoName = $(this).find('').text();
									//jsonObj.gugunName = $(this).find('').text();
									jsonObj.buildYear = $(this).find('건축년도').text();
									jsonObj.jibun = $(this).find('지번').text();
									jsonObj.recentPirce = $(this).find('거래금액').text();
										
									jsonObj = JSON.stringify(jsonObj);
									//String 형태로 파싱한 객체를 다시 json으로 변환
									jsonArray.push(JSON.parse(jsonObj));
								});
								console.log(jsonArray);
								//displayMarkers(jsonArray);
							}
							, "xml"
					);
					*/
					
					/* var xhr = new XMLHttpRequest();
					var url = 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';
					var queryParams = '?' + encodeURIComponent('serviceKey') + '='+encodeURIComponent('***  API KEY  ***');
					queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); 
					queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); 
					queryParams += '&' + encodeURIComponent('LAWD_CD') + '=' + encodeURIComponent($("#gugun").val()); 
					queryParams += '&' + encodeURIComponent('DEAL_YMD') + '=' + encodeURIComponent('202110'); 
					xhr.open('GET', url + queryParams);
					xhr.onreadystatechange = function () {
					    if (this.readyState == 4) {
					    	console.log(this.responseXML);
					        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
					    }
					};

					xhr.send(''); */
				//});
				</script>
				</div>
			</div>
		</section>
	</div>
</body>
</html>