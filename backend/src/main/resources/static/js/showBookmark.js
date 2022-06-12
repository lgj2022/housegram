var map, marker;
var markers = [];
var geocoder = new kakao.maps.services.Geocoder();
var bounds = new kakao.maps.LatLngBounds();

$(document).ready(function () {
  // local storage에서 선호 지역 불러오기
  var interestArea = JSON.parse(localStorage.getItem("interestArea"));
  if (interestArea) {
    $.each(interestArea, function (index, Element) {
      $(".form-select").append(
        `
                <option value="${Element.key}">${Element.sido} ${Element.gugun} ${Element.dong}</option>
                `
      );
    });
  }

  initMap();
});

// address search를 위한 콜백 함수
var callback = function (result, status) {
  //console.log(status);
  if (status === kakao.maps.services.Status.OK) {
    //console.log(result);
    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    bounds.extend(coords);

    var imageSrc = "/img/house.png", // 마커이미지의 주소입니다
      imageSize = new kakao.maps.Size(50, 70), // 마커이미지의 크기입니다
      imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(
      imageSrc,
      imageSize,
      imageOption
    );

    // 마커를 생성합니다
    marker = new kakao.maps.Marker({
      map: map,
      position: coords,
      image: markerImage,
    });

    marker.setMap(map);
    markers.push(marker);
    map.setBounds(bounds);
  }
};

// select에서 값 읽어서 조회하기
$(".form-select").on("change", function () {
  var address = $("#address").find("option:selected").text();

  if (address != "선호 지역을 선택하십시오") {
    // dong 분리
    var dong = address.split(" ")[2];
    // 마커 지우기
    var centerCoord = { x: 0, y: 0 };
    if (markers) {
      markers.forEach((element) => {
        bounds = new kakao.maps.LatLngBounds();
        element.setMap(null);
      });
    }
    // 구 코드 찾기
    var guCode = $("#address")
      .find("option:selected")
      .attr("value")
      .substring(0, 5);

    /* Javascript 샘플 코드 */
    var xhr = new XMLHttpRequest();
    var base_url =
      "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"; /*URL*/
    var queryParams =
      "?" +
      encodeURIComponent("serviceKey") +
      "=" +
      "FBcNOTDKCffi884b%2FbAJHUswz0w5sQ92oQ9hhwGXtVm0khubm14kdtIWrP7VRKkJNal6Nh0w8hROdDLQbbH3QQ%3D%3D"; /*Service Key*/
    // 법정동 코드 보내기 -> 구정보만 보내기 때문에 여기서 동 주소를 기준으로 필터링 해야 할 필요가 있음
    queryParams +=
      "&" + encodeURIComponent("LAWD_CD") + "=" + encodeURIComponent(guCode);
    // 시간 보내기 -> 현재는 2015년 12월
    queryParams +=
      "&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent("201512");
    xhr.open("GET", base_url + queryParams);

    fetch(base_url + queryParams, {
      headers: {
        Accept: "application/json",
      },
    })
      .then((response) => response.json())
      .then((jsonRes) => jsonRes.response.body.items.item)
      .then((resList) => {
        // 검색 개수를 세기 위한 변수
        var searchCnt = 0;
        // info 파트 만들기
        $("#info").empty();
        for (let i = 0; i < resList.length; i++) {
          // console.log(resList[i]["법정동"])
          // console.log(dong);
          if (resList[i]["법정동"] == " " + dong) {
            // 검색 개수를 세기 위한 변수
            searchCnt++;
            // info div (indexKakao.html:68) 정보 넣기
            $("#info").append(
              `<button type="button" class="list-group-item list-group-item-action" data-cost="${resList[i]["거래금액"]}" data-date="${resList[i]["년"]}년 ${resList[i]["월"]}월 ${resList[i]["일"]}일" data-aptname="${resList[i]["아파트"]}">
                                <p> 거래 금액 : ${resList[i]["거래금액"]}</p>
                                <p> 거래 일시 : ${resList[i]["년"]}년 ${resList[i]["월"]}월 ${resList[i]["일"]}일</p>  
                                <p> 아파트 이름 : ${resList[i]["아파트"]}</p>
                            </button>`
            );
            $("#info").css("padding", "auto");
            let townCode = resList[i]["법정동"];
            let townNum = resList[i]["지번"];
            // console.log(`서울특별시 ${gugun} ${townCode} ${townNum}`)
            // 서울특별시 종로구 청운동 9
            let mapAddress = `${address} ${townNum}`;

            // console.log(mapAddress)
            geocoder.addressSearch(
              mapAddress,
              callback,
              (analyze_type = "EXACT")
            );
          }
        }
        if (searchCnt == 0) {
          $("#info").css("visibility", "hidden");
          //alert('검색 결과가 없습니다.');
          return;
        } else {
          if ($("#info").css("visibility") == "hidden") {
            $("#info").css("visibility", "visible");
          }
        }
      });
  } else {
    // 정보가 없다면 정보 파트 가리기.
    $("#info").css("visibility", "visible");
  }
});

var map;

function initMap() {
  // 초기 kakao map 설정 start
  var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
  // var lat = 37.5012743;
  // var lng = 127.039585;
  var locPosition = new kakao.maps.LatLng(37.5666805, 126.9784147); // (서울 시청)
  var options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: locPosition, //지도의 중심좌표.
    level: 7, //지도의 레벨(확대, 축소 정도)
  };

  map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
}

// 선호지역 클릭 시 팝업 띄우기
let bookmark = document.querySelector(".addBookmark")
bookmark.addEventListener("click", function(){
    window.open('../html/addBookmark.html', "선호지역 추가", "width=450, height=500, top=150, left=200")
})