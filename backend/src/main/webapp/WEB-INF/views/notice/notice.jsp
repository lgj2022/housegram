<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="root"></c:set>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/noticeHead.jsp"%>
</head>
<body>
	<form name="pageForm" id="pageForm">
		<input type="hidden" name="pg" id="pg" value="" /> <input
			type="hidden" name="key" id="key" value="${key}" /> <input
			type="hidden" name="word" id="word" value="${word}" />
	</form>
	<%@ include file="/WEB-INF/views/include/noticeNav.jsp"%>

	<div class="notice-title">
		<p>공지사항</p>
	</div>
	<form name="pageForm" id="pageForm">
		<input type="hidden" name="act" id="act" value="list" /> <input
			type="hidden" name="pg" id="pg" value="" /> <input type="hidden"
			name="key" id="key" value="${key}" /> <input type="hidden"
			name="word" id="word" value="${word}" />
	</form>
	<div class="container text-center mt-3 searchDiv">
		<div class="m-3 row justify-content-end">
			<form class="form-inline" onsubmit="return false">
				<input type="hidden" name="pg" value="1"> <select name="key"
					class="form-control searchSelect" id= "searchSelect">
					<!-- <option value="userId">아이디 -->
					<option value="title">제목
					<option value="content">글 내용
				</select> <input type="text" class="ml-1 form-control" name="word" id="skeyword">
				<button class="ml-1 btn btn-outline-primary" id = "searchbtn">검색</button>
			</form>
		</div>
	</div>
<script type="text/javascript">
   $(document).ready(function() {
      // 도서 목록
      $.ajax({
         url:'${root}/notice/api/list',  
         type:'GET',
         contentType:'application/json;charset=utf-8',
         dataType:'json',
         success:function(response) {
        	console.log(response);
        	console.log(response.notices);
            makeList(response.notices);
         },
         error:function(xhr, status, error){
            console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
         },
         statusCode: {
            500: function() {
               alert("서버에러.");
               // location.href = "/error/e500";
            },
            404: function() {
               alert("페이지없다.");
               // location.href = "/error/e404";
            }
         }   
      });
      
      $(document).on("click", "#searchbtn", function() {
    	  	let op=$("#searchSelect option:selected").val();
    	  	let word = $("#skeyword").val();
    	  	console.log(op + " "+ word)
    	  	/* let searchinfo = JSON.stringify({ 
    	  		op:word
	        	}); */
	          $.ajax({
	               url:'${root}/notice/api/search',
	               type:'GET',
	               contentType:'application/json;charset=utf-8',
	               dataType:'JSON',
	               data: {"op": op, "word": word},
	               success:function(response) {
	            	   console.log(response);
	            	   makeList(response.notices);
	               },
	               error:function(xhr,status,msg){
	                  console.log("상태값 : " + status + " Http에러메시지 : "+msg);
	               }
	            });
	            
	      });
      
      
   });
      function makeList(notices) {
          $("#noticelist").empty();
          $(notices).each(function(index, notice) {
             let str = `
            	 <tr>
					<th scope="row">${'${notice.noticeNo}'}</th>
					<td><a href="${root}/notice/detail/${'${notice.noticeNo}'}">${'${notice.title}'}</a></td>
					<td>${'${notice.content}'}</td>
					<td>${'${notice.author}'}</td>
					<td>${'${notice.createdAt}'}</td>
				</tr>
                `;
                $("#noticelist").append(str);
             });//each
         }
</script>
	<section class="notice">
		<article class="notice_list">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성자</th>
						<th>작성일자</th>
					</tr>
				</thead>
				<tbody id="noticelist">
				</tbody>

				<%--
					<c:if test="${not empty noticeList}">
						<c:forEach items="${noticeList}" var="notice">
							<tr>
								<th scope="row">${notice.noticeNo}</th>
								<td><a href="detail.do?noticeNo=${notice.noticeNo}">${notice.title}</a></td>
								<td>${notice.content}</td>
								<td>${notice.author}</td>
								<td>${notice.createdAt}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty noticeList}">
						<tr style="text-align: center">
							<td colspan="6">등록된 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
				--%>
			</table>
		</article>
	</section>

	<div class="container"">${navi.navigator}</div>
	<a href="${root}/notice/regist" class="btn btn-outline-primary createBtn">공지사항 작성</a>
	<!--  <script src="../js/notice.js"></script>
    <script src="../js/navber.js"></script>-->
</body>
</html>