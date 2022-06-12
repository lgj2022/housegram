<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
		<div class="modal-body">
            <div class="loginFormWrap">
				<div>간편 로그인</div>
					<form class="loginForm" method="POST" action="${root}/account/login">
                		<ul>
                  			<li>
                    			<input type="text" name="id" id="id" value="" required />
                    			<label for="id">ID</label>
                  			</li>
                  			<li>
                    			<input type="password" name="password" id="password" value="" required/>
                    			<label for="password">비밀번호</label>
                  			</li>
                  			<div class="btnWrap">
                    			<button class="loginBtn" href="#">확인</button>
                    			<button class="loginCancle" href="#" data-bs-dismiss="modal">취소</button>
                  			</div>
                  			<li>
                    			<a class="createLink" href="#" data-bs-toggle="modal" data-bs-target="#signUpModal">계정이 존재하지 않으신가요?</a>
                 			 </li>
						</ul>
              		</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- let loginBtn = document.querySelector(".loginBtn");
loginBtn.addEventListener("click", e=>{
	e.preventDefault();
	alert("dd");
	let loginForm = document.querySelector(".loginForm");
	loginForm.action = "account/login.do";
	loginForm.submit();
}); -->