let bar = document.querySelector(".fa-bars");
let menu = document.querySelector(".menu");
let accountContainer = document.querySelector(".user-account-container");
bar.addEventListener("click", () =>{
    menu.classList.toggle("active");
    accountContainer.classList.toggle("active");
})