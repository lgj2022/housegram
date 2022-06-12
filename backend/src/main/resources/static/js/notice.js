// 공지사항 정보 local storage 에서 가져옴 수정 필요
let noticeData = JSON.parse(localStorage.getItem("notice"));
if (noticeData) {
  if (location.pathname == "/html/notice.html") {
    for (let i = 0; i < noticeData.length; i++) {
      console.log(noticeData[i]);
      let tr = document.createElement("tr");
      let num = document.createElement("td");
      let title = document.createElement("td");
      let author = document.createElement("td");
      let createAt = document.createElement("td");
      tr.setAttribute("data-id", `${i}`);
      num.innerText = `${i + 1}`;
      author.innerText = `${noticeData[i].user}`;
      createAt.innerText = `${noticeData[i].date}`;
      title.innerText = `${noticeData[i].title}`;
      tr.appendChild(num);
      tr.appendChild(title);
      tr.appendChild(author);
      tr.appendChild(createAt);
      let tbody = document.querySelector("tbody");
      tbody.appendChild(tr);
    }
  }
}

let noticeList = document.querySelectorAll("tbody tr");

for (let i = 0; i < noticeList.length; i++) {
  noticeList[i].addEventListener("click", () => {
    console.log(noticeList[i].dataset.id);
    sessionStorage.setItem("noticeId", `${noticeList[i].dataset.id}`);
    location.href = "./noticeDetail.html";
  });
}

// 선호지역 클릭 시 팝업 띄우기
let bookmark = document.querySelector(".addBookmark");
bookmark.addEventListener("click", function () {
  window.open(
    "../html/addBookmark.html",
    "선호지역 추가",
    "width=450, height=500, top=150, left=200"
  );
});
