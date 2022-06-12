<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="/css/notice.css" />
<link rel="stylesheet" href="/css/secondNav.css" />
<link rel="stylesheet" href="/css/noticeDetail.css" />
<!-- Google Font font-family: 'Lobster', cursive;-->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Cookie&family=Great+Vibes&family=Lobster&display=swap"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<style>
.pagination {
	justify-content: center
}

.form-inline {
	display: flex;
}

.searchSelect {
	margin-right: 10px;
	width: 100px;
	text-align: center;
	width: 100px;
}

.form-control[name="word"] {
	margin-right: 10px;
	width: 250px;
}

.searchDiv {
	margin-left: 0px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(".page-item").click(function() {
			$("#pg").val($(this).attr("data-pg"));
			$("#pageForm").attr("action", "list.do").submit();
		});
	});

	function deleteArticle(no) {
		if (confirm("정말로 삭제??")) {
			location.href = "${root}/article?act=delete&articleno=" + no;
		}
	}
</script>