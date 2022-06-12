<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/views/include/noticeHead.jsp"%>
<title>Happy House</title>
</head>
<!--  
	<script type="text/javascript">
        $(document).ready(function () {
            $("#notice-create").click(function () {
                 $("#writeform").attr("action", "${root}/notice/regist").submit();
            });
        });
    </script>
-->
<body>
	<%@ include file="/WEB-INF/views/include/noticeNav.jsp"%>
	<div class="notice-craete-container">
		<form class="notice-create-form" action="${root}/notice/regist" method="POST" id="writeform">
			<input hidden type="text" value="${userInfo.id}" name="userId" class="form-control" />
			<input hidden type="text" value="${userInfo.name}" name="author"  class="form-control" />
			<label for="title">공지 제목</label> <input
				type="text" id="title" class="form-control" name="title" /> <label
				for="notice-content">공지 내용</label>
			<textarea class="form-control" name="content" id="notice-content"
				cols="30" rows="10"></textarea>
			<a href="list.do" type="button" class="btn btn-outline-danger">작성
				취소</a>
			<button id="notice-create" class="btn btn-outline-primary">작성</button>
		</form>
	</div>
</body>
</html>