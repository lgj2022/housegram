function userCheck(){
    let userAccountBtn = document.querySelectorAll(".user-account-btn");
    // 로컬스토리지에 "currentUser" 키로 저장된 item 가져와서 json 객체로 만들기
    const currentUser = JSON.parse(localStorage.getItem("currentUser"));
    if(currentUser != null){
        for(let i=0; i<userAccountBtn.length; i++){
            userAccountBtn[i].classList.toggle("active");
        }
    }
} 
userCheck();

let searchForm = document.querySelector(".searchForm");
let recommend = document.querySelector("#recommend");
let keywordSearch = document.querySelector("#keywordSearch");
let body = document.querySelector("body");
let openFlag = false;
body.addEventListener("click", e => {
    if(e.target.className =="fas fa-search"){
        searchForm.classList.toggle("active");
        openFlag = !openFlag;
    } // 내가 클릭한 요소(target)를 기준으로 상위요소에 .share-pop이 없으면 (갯수가 0이라면)
    else{
        if(e.target.className != "search-input" && openFlag){
            openFlag = false;
            searchForm.classList.toggle("active");
        }
    }
    // searchForm.classList.remove("active");
})


/*
 * searchForm.addEventListener("keydown", e=>{ if(e.keyCode == 13){
 * e.preventDefault(); } }); searchForm.addEventListener("keyup", e =>{
 * e.preventDefault(); if(e.keyCode == 13){ console.log(e.target.value);
 * alert(`${e.target.value}에 대한 검색을 시작합니다.`); searchForm.submit(); } })
 */
keywordSearch.addEventListener("keyup", e=>{
	/* e.preventDefault(); */
	let keyword = e.target.value;
	if(keyword.length >= 2){
    	recommend.classList.remove("invisible")
    	if(e.keyCode==13){
    		let searchKeyword;
    		if(selectCheck()){
    			searchKeyword = document.querySelector(".keywordSelect").innerText;
    		}else{
    			searchKeyword = e.target.value;
    		}
    		let searchForm = document.querySelector("#searchForm");
    		
    		let keywordSearch = document.querySelector("#keywordSearch");
    		keywordSearch.value = searchKeyword;
    		searchForm.submit();
    		
    	}
    	if(e.keyCode === 40){
    		if(!selectCheck()){
    			let item = document.querySelectorAll(".item")[0];
    			item.classList.add("keywordSelect");
    		}else{
    			changeSelect("down");
    		}
    		
    	}else if(e.keyCode === 38){
    		if(!selectCheck()){
    			let item = document.querySelectorAll(".item");
    			item[item.length].classList.add("keywordSelect");
    		}else{
    			changeSelect("up");
    		}
    	}
    	else{
    		fetch(`/recommend/keyword/${keyword}`)
        	.then(response => response.json())
        	.then(datas=>{
        		recommend.innerHTML = "";
        		if(datas.length != 0){
        			datas.map(data =>{
        				let itemDiv = document.createElement("div");
        				itemDiv.classList.add("item");
        				let startIdx = emphasizeKeyword(data, keyword);
        				let front = data.substring(0,startIdx);
        				let empha = `<span class="emphasize">${data.substring(startIdx, startIdx + keyword.length)}</span>`;
        				let end = data.substring(startIdx + keyword.length);
        				let content = `${front}${empha}${end}`;
        				itemDiv.innerHTML = content;
        				recommend.appendChild(itemDiv);
            		})
        		}
        		else{
        			let itemDiv = document.createElement("div");
    				itemDiv.classList.add("item");
    				itemDiv.innerHTML = "일치하는 검색어가 없습니다.";
    				recommend.appendChild(itemDiv);
        		}
        		
        	})
    	}
    	
    }else{
    	recommend.classList.add("invisible")
    }
})


keywordSearch.addEventListener("keydown", e=>{
	if(e.keyCode==13){
		let searchKeyword;
		if(selectCheck()){
			searchKeyword = document.querySelector(".keywordSelect").innerText;
		}else{
			searchKeyword = e.target.value;
		}
		let searchForm = document.querySelector("#searchForm");
		
		let keywordSearch = document.querySelector("#keywordSearch");
		/* keywordSearch.setAttribute("value", searchKeyword); */
		keywordSearch.value = searchKeyword;
		/* searchForm.action(`map/search/apt/dong/${searchKeyword}`); */
		searchForm.submit();
	}
	
})
 
let emphasizeKeyword = (data, keyword) =>{
	let dataArr = data.split('');
	let keywordArr = keyword.split('');
	let dataLength = data.length;
	let kLength = keyword.length;
	let failure = [];
	failure.length = keywordArr.length;
	failure.fill(-1); // failure 배열 세팅
	for(let i = 1; i < kLength; i++){
		let idx = (1 + failure[i-1]);
		if(keywordArr[i] === keywordArr[idx]){
			failure[i] = idx;
		} 
		else{ 
			failure[i] = -1
		}
	} // 비교 시작
	let n = 0, m = 0;
	while(m < kLength && n < dataLength){
		if(dataArr[n] === keywordArr[m]){
			n++;
			m++;
		}else if(m === 0){
			n++;
		}else{ m = 1 + failure[m-1]
		}
	}
	return m === kLength? (n - kLength) : -1
	
}
// searchForm.classList.toggle("active");
// openFlag = !openFlag;
// searchForm.children[0].value=""
    

let selectCheck = () =>{
	let keywordSelect = document.querySelector(".keywordSelect");
	if(keywordSelect == null){
		return false;
	}else{
		return true;
	}
}

let changeSelect = (action) =>{
	let item = document.querySelectorAll(".item");
	for(let i=0; i<item.length; i++){
		if(item[i].classList[1] != undefined){
			item[i].classList.remove("keywordSelect");
			if(action === "down"){
				item[(i+1)%item.length].classList.add("keywordSelect");
			}else{
				let idx = (i-1) >=0? i-1: item.length-1;
				item[idx].classList.add("keywordSelect");
			}
			return;
			
		}
	}
}










// 선호지역 클릭 시 팝업 띄우기
let bookmark = document.querySelector(".addBookmark")
bookmark.addEventListener("click", function(){
    window.open('./html/addBookmark.html', "선호지역 추가", "width=450, height=500, top=150, left=200")
})

// 관심 지역 둘러보기 시 새로운 페이지로 이동
let showBookmark = document.querySelector(".showBookmark")
showBookmark.addEventListener("click", function(){
    // window.open('./html/showBookmark.html', "선호지역 둘러보기", "width=600,
	// height=800, top=150, left=200")
    location.replace('./html/showBookmark.html');
})


// 도시 지역구 받아오기
let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
let regcode = "*00000000";
// 전국 특별/광역시, 도
// https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000
$.ajax({
    url: url,
    type: "GET",
    data: {
        regcode_pattern: regcode,
    },
    dataType: "json",
    success: function (response) {
        let code = ``;
        $.each(response.regcodes, function (i, regcode) {
            code += `
            <option value="${regcode.code}">${regcode.name}</option>
            `;
        });
        $("#sido").empty().append('<option value="">시도선택</option>').append(code);
    },
    error: function (xhr, status, msg) {
        console.log("상태값 : " + status + " Http에러메시지 : " + msg);
    },
});

$(document).on("change", "#sido", function () {
    regcode = $(this).val().substr(0, 2) + "*00000";
    $.ajax({
        url: url,
        type: "GET",
        data: {
            regcode_pattern: regcode,
            is_ignore_zero: true,
        },
        dataType: "json",
        success: function (response) {
            let code = ``;
            $.each(response.regcodes, function (i, regcode) {
                code += `
            <option value="${regcode.code}">${regcode.name.split(" ")[1]}</option>
            `;
            });
            $("#gugun").empty().append('<option value="">구군선택</option>').append(code);
        },
        error: function (xhr, status, msg) {
            console.log("상태값 : " + status + " Http에러메시지 : " + msg);
        },
    });
});

$(document).on("change", "#gugun", function () {
    regcode = $(this).val().substr(0, 4) + "*";
    console.log(regcode);
    $.ajax({
        url: url,
        type: "GET",
        data: {
            regcode_pattern: regcode,
            is_ignore_zero: true,
        },
        dataType: "json",
        success: function (response) {
            let code = ``;
            $.each(response.regcodes, function (i, regcode) {
                code += `
            <option value="${regcode.code}">${regcode.name.split(" ")[2]}</option>
            `;
            });
            $("#dong").empty().append('<option value="">동선택</option>').append(code);
        },
        error: function (xhr, status, msg) {
            console.log("상태값 : " + status + " Http에러메시지 : " + msg);
        },
    });
});

window.addEventListener("scroll", () => {
    let navbar = document.querySelector("nav");
    let height = window.window.pageYOffset;
    if(height == 0){
        navbar.classList.remove("active");
    }
    if(height >= 80){
        navbar.classList.add("active");
    }
})

let barBtn = document.querySelector(".fa-bars");
let menu = document.querySelector(".menu");
let userAccountContainer = document.querySelector(".user-account-container");
barBtn.addEventListener("click", ()=>{
    menu.classList.toggle("active");
    userAccountContainer.classList.toggle("active");
})
