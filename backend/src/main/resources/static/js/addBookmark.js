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

// 확인 버튼 클릭 시 local storage에 선호지역 추가하기.
let confirm = document.querySelector(".btn-primary");
confirm.addEventListener("click",function(){
    var areaList = JSON.parse(localStorage.getItem("interestArea"));
    if(!areaList){
        areaList = [];
    }
    var sido = $("#sido").find("option:selected").text();
    var gugun = $("#gugun").find("option:selected").text();
    var dong = $("#dong").find("option:selected").text();
    if(sido != "시도선택" && gugun != "시/구/군" && dong != "동/읍/면" && gugun != "구군선택" && dong != "동선택"){
        // local storage의 키로 지역구 코드 넣기.
        var key = $("#gugun").find("option:selected").attr("value");
        var address = {
            "sido" : sido,
            "gugun" : gugun,
            "dong" : dong,
            "key" : key
        }
        areaList.push(address);
        localStorage.setItem("interestArea", JSON.stringify(areaList));
        alert("등록 완료!")
        window.close();
    }else{
        alert("입력이 잘못되었습니다!")
    }
})