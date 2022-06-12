$("#modify").on("click", function () {
    let pwcheck = document.querySelector("#passwordCheck");
    let checklable = document.querySelector("label[for=passwordCheck]");
    let password = document.querySelector("#password");
    let passwordlabel = document.querySelector("label[for=password]");
    
    password.classList.add("active")
    pwcheck.classList.add("active")
    checklable.classList.add("active");
    passwordlabel.classList.add("active");
    var inputs = $("input");
    inputs.each(function (i, item) {
        $(item).attr("readonly", false);
    });
    $("#confirm").attr("disabled", false);
});
let userUpdateForm = document.querySelector("#user-update-form");
$("#delete").on("click", function () {
    if (confirm("정말로 회원 정보를 지우시겠습니까?")) {
    	userUpdateForm.action = "delete";
    	userUpdateForm.submit();
    }
});
// 선호지역 클릭 시 팝업 띄우기
let bookmark = document.querySelector(".addBookmark")
bookmark.addEventListener("click", function(){
    window.open('../html/addBookmark.html', "선호지역 추가", "width=450, height=500, top=150, left=200")
})