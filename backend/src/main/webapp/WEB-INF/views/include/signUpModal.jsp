<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal fade" id="signUpModal" tabindex="-1"
	aria-labelledby="signUpModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="createWrap">
					<form class="accountCreatForm" method="POST" action="${root}/account/register">
						<ul>
							<li><label for="signUpId">ID</label></li>
							<li><input type="text" name="signUpId" id="signUpId"
								value="" required /></li>
							<li><label for="name">이름</label></li>
							<li><input type="text" name="name" id="name" value=""
								required /></li>
							<li><label for="password1">비밀번호</label></li>
							<li><input type="password" name="password1" id="password1"
								value="" required /></li>
							<li><label for="password2">비밀번호 확인</label></li>
							<li><input type="password" name="password2" id="password2"
								value="" required /></li>
							<li><label for="phone">전화번호('-'포함)</label></li>
							<li><input type="text" name="phone" id="phone" value=""
								required /></li>
							<li><a class="loginLink" href="" data-bs-toggle="modal"
								data-bs-target="#loginModal">이미 계정이 있으신가요?</a></li>
						</ul>
						<div class="btnWrap">
							<button class="accountCreatBtn" href="#" data-bs-dismiss="modal">확인</button>
							<button class="accountCreatCancle" href="#"
								data-bs-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

/* let accountCreatBtn = document.querySelector(".accountCreatBtn");
accountCreatBtn.addEventListener("click", e=>{
	e.preventDefault();
	let accountCreatForm = document.querySelector(".accountCreatForm");
	accountCreatForm.action = "account/create.do";
	console.dir(accountCreatForm);
	accountCreatForm.submit();
}); */

</script>