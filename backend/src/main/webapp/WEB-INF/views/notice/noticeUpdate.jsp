<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/views/include/noticeHead.jsp"%>
<title>공지사항 작성</title>
</head>
<script type="text/javascript">
   $(document).ready(function() {
	   $(document).on("click", "#notice-update", function() {
 	    	 let updateinfo = JSON.stringify({ 
	         	"title" : $("#update-title").val(), 
	        	"content" : $("#update-content").val()
	        	});
	    	 console.log(updateinfo);
	            $.ajax({
	               url:'${root}/notice/api/update/${title}',
	               type:'PUT',
	               contentType:'application/json;charset=utf-8',
	               dataType:'JSON',
	               data:updateinfo,
	               success:function(response) {
	            	   console.log(response);
	            	   location.href ="${root}/notice/list";
	               },
	               error:function(xhr,status,msg){
	                  console.log("상태값 : " + status + " Http에러메시지 : "+msg);
	               }
	            });
	      });
   });
</script>
<body>
	<%@ include file="/WEB-INF/views/include/noticeNav.jsp"%>
	<div class="notice-craete-container">
		<form class="notice-update-form">
			<input hidden type="text" value="${updateTarget.noticeNo}" name="noticeNo">
			<input hidden type="text" value="${updateTarget.userId}" name="userId"> 
			<input hidden type="text" value="${updateTarget.author}" name="author">
			<label for="title">제목</label> <input type="text" id="update-title"
				class="form-control" value="${updateTarget.title}" name="title">
			<label for="update-content">내용</label>
			<textarea class="form-control" name="content"
				id="update-content" cols="30" rows="10">${updateTarget.content}</textarea>
			<button id="notice-update" class="btn btn-outline-primary">수정</button>
			<a href="${root}/notice/list" type="button" class="btn btn-outline-danger">취소</a>
		</form>
	</div>
	<!--  <script src="/js/noticeUpdate.js"></script>-->
</body>
</html>