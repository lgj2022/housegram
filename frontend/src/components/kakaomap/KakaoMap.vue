<template>
  <div class="row map-wrap">
    <div class="col">
      <div id="map" style="width: 100%; height: 500px; margin: auto"></div>
      <div class="category">
        <ul>
          <li id="coffeeMenu" @click="changeMarker('coffee')">
            <img
              src="http://localhost/static/marker/coffee-cup.png"
              alt=""
              style="width: 30px"
            />
          </li>
          <li id="storeMenu" @click="changeMarker('store')">
            <img
              src="http://localhost/static/marker/24-hours.png"
              alt=""
              style="width: 30px"
            />
          </li>
          <li id="subwayMenu" @click="changeMarker('subway')">
            <img
              src="http://localhost/static/marker/train.png"
              alt=""
              style="width: 30px"
            />
          </li>
        </ul>
      </div>
    </div>
    <div class="col-4 apt-info" style="display: none">
      <div class="list-group" id="info"></div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      markers: [],
      coffeePositions: [],
      storePositions: [],
      subwayPositions: [],
      markerImageSrc:
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png",
      coffeeMarkers: [],
      storeMarkers: [],
      subwayMarkers: [],
    };
  },
  watch: {
    house: function () {
      // this.gethouse();
      //console.log("안바뀜");
      this.makeMarker();
    },
  },
  computed: {
    ...mapState(["house"]),
    ...mapGetters(["gethouse"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  beforeMount() {
    this.initMap();
  },
  mounted() {
    this.initMap();
  },
  methods: {
    initMap() {
      /* global kakao */
      if (!window.kakao || !window.kakao.maps) {
        const script = document.createElement("script");
        // script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e6756d35bad09a04e639727ae6e51a0e&libraries=services`;
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e6756d35bad09a04e639727ae6e51a0e&libraries=services`;
        script.onload = () => {
          kakao.maps.load(this.initMap);
        };
        document.head.appendChild(script);
      } else {
        console.log("이미로딩됨", window.kakao);
      }
      this.makeMarker();
    },
    makeMarker() {
      console.log("마커 확인~~~~~~~~");
      console.log(this.house);
      const mapContainer = document.querySelector("#map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.house.lat, this.house.lng), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };
      this.map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
      // 마커가 표시될 위치입니다
      let markerPosition = new kakao.maps.LatLng(
        this.house.lat,
        this.house.lng
      );

      var ps = new kakao.maps.services.Places(this.map);
      ps.categorySearch("CE7", this.placesCoffee, { useMapBounds: true });
      ps.categorySearch("CS2", this.placesStore, { useMapBounds: true });
      ps.categorySearch("SW8", this.placesSubWay, { useMapBounds: true });
      // 마커를 생성합니다
      let marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      this.markers.push(marker);

      console.log("markers 확인");
      console.log(this.markers);
      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);
    },
    placesCoffee(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          //displayMarker(data[i]);
          let markerPosition = new kakao.maps.LatLng(data[i].y, data[i].x);
          this.coffeePositions.push(markerPosition);
        }
      }
      console.log(pagination);
    },
    placesStore(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          //displayMarker(data[i]);
          let markerPosition = new kakao.maps.LatLng(data[i].y, data[i].x);
          this.storePositions.push(markerPosition);
        }
      }
      console.log(pagination);
    },

    placesSubWay(data, status, pagination) {
      console.log(status);
      if (status === kakao.maps.services.Status.OK) {
        console.log("지하철데이터 개수: ", data.length);
        for (var i = 0; i < data.length; i++) {
          let markerPosition = new kakao.maps.LatLng(data[i].y, data[i].x);
          this.subwayPositions.push(markerPosition);
        }
      }
      console.log(pagination);
    },

    createMarkerImage(src, size, options) {
      var markerImage = new kakao.maps.MarkerImage(src, size, options);
      return markerImage;
    },
    createMarker(position, image) {
      var marker = new kakao.maps.Marker({
        position: position,
        image: image,
      });

      return marker;
    },
    createCoffeeMarkers(coffeePositions) {
      for (var i = 0; i < coffeePositions.length; i++) {
        var imageSize = new kakao.maps.Size(35, 35),
          imageOptions = {
            spriteOrigin: new kakao.maps.Point(0, 0),
            spriteSize: new kakao.maps.Size(36, 36),
          };

        // 마커이미지와 마커를 생성합니다
        var markerImage = this.createMarkerImage(
            "http://localhost/static/marker/coffee-marker.png",
            imageSize,
            imageOptions
          ),
          marker = this.createMarker(coffeePositions[i], markerImage);

        // 생성된 마커를 커피숍 마커 배열에 추가합니다
        this.coffeeMarkers.push(marker);
      }
    },

    setCoffeeMarkers(coffeeMarkers, map) {
      for (var i = 0; i < coffeeMarkers.length; i++) {
        coffeeMarkers[i].setMap(map);
      }
    },

    createStoreMarkers(storePositions) {
      for (var i = 0; i < storePositions.length; i++) {
        var imageSize = new kakao.maps.Size(35, 35),
          imageOptions = {
            spriteOrigin: new kakao.maps.Point(0, 0),
            spriteSize: new kakao.maps.Size(36, 36),
          };

        // 마커이미지와 마커를 생성합니다
        var markerImage = this.createMarkerImage(
            "http://localhost/static/marker/24-hours_marker.png",
            imageSize,
            imageOptions
          ),
          marker = this.createMarker(storePositions[i], markerImage);

        // 생성된 마커를 편의점 마커 배열에 추가합니다
        this.storeMarkers.push(marker);
      }
    },
    setStoreMarkers(storeMarkers, map) {
      for (var i = 0; i < storeMarkers.length; i++) {
        storeMarkers[i].setMap(map);
      }
    },

    createsubwayMarkers(subwayPositions) {
      console.log("지하철역 확인", subwayPositions);
      for (var i = 0; i < subwayPositions.length; i++) {
        var imageSize = new kakao.maps.Size(35, 35),
          imageOptions = {
            spriteOrigin: new kakao.maps.Point(0, 0),
            spriteSize: new kakao.maps.Size(36, 36),
          };

        // 마커이미지와 마커를 생성합니다
        var markerImage = this.createMarkerImage(
            "http://localhost/static/marker/train_marker.png",
            imageSize,
            imageOptions
          ),
          marker = this.createMarker(subwayPositions[i], markerImage);

        // 생성된 마커를 주차장 마커 배열에 추가합니다
        this.subwayMarkers.push(marker);
      }
    },
    setsubwayMarkers(subwayMarkers, map) {
      for (var i = 0; i < subwayMarkers.length; i++) {
        subwayMarkers[i].setMap(map);
      }
    },

    changeMarker(type) {
      console.log("type 확인" + type);
      this.createCoffeeMarkers(this.coffeePositions);
      this.createStoreMarkers(this.storePositions);
      this.createsubwayMarkers(this.subwayPositions);
      var coffeeMenu = document.getElementById("coffeeMenu");
      var storeMenu = document.getElementById("storeMenu");
      var subwayMenu = document.getElementById("subwayMenu");

      // 커피숍 카테고리가 클릭됐을 때
      if (type === "coffee") {
        // 커피숍 카테고리를 선택된 스타일로 변경하고
        console.log("coffee 마커확인 ");
        console.log(this.coffeeMarkers);
        coffeeMenu.className = "menu_selected";

        // 편의점과 주차장 카테고리는 선택되지 않은 스타일로 바꿉니다
        storeMenu.className = "";
        subwayMenu.className = "";

        // 커피숍 마커들만 지도에 표시하도록 설정합니다
        this.setCoffeeMarkers(this.coffeeMarkers, this.map);
        this.setStoreMarkers(this.storeMarkers, null);
        this.setsubwayMarkers(this.subwayMarkers, null);
      } else if (type === "store") {
        // 편의점 카테고리가 클릭됐을 때
        console.log("store 마커확인 ");
        console.log(this.coffeeMarkers);
        // 편의점 카테고리를 선택된 스타일로 변경하고
        coffeeMenu.className = "";
        storeMenu.className = "menu_selected";
        subwayMenu.className = "";

        // 편의점 마커들만 지도에 표시하도록 설정합니다
        this.setCoffeeMarkers(this.coffeeMarkers, null);
        this.setStoreMarkers(this.storeMarkers, this.map);
        this.setsubwayMarkers(this.subwayMarkers, null);
      } else if (type === "subway") {
        // 주차장 카테고리가 클릭됐을 때
        console.log("subway 마커확인 ");
        console.log(this.createsubwayMarkers);
        // 주차장 카테고리를 선택된 스타일로 변경하고
        coffeeMenu.className = "";
        storeMenu.className = "";
        subwayMenu.className = "menu_selected";

        // 주차장 마커들만 지도에 표시하도록 설정합니다
        this.setCoffeeMarkers(this.coffeeMarkers, null);
        this.setStoreMarkers(this.storeMarkers, null);
        this.setsubwayMarkers(this.subwayMarkers, this.map);
      }
    },
  },
};
</script>

<style>
/*
#mapwrap {
  position: relative;
  overflow: hidden;
}
.category,
.category * {
  margin: 0;
  padding: 0;
  color: #000;
}
.category {
  position: absolute;
  overflow: hidden;
  top: 10px;
  left: 10px;
  width: 150px;
  height: 50px;
  z-index: 10;
  border: 1px solid black;
  font-family: "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 12px;
  text-align: center;
  background-color: #fff;
}*/
/*
.category .menu_selected {
  background: #ff5f4a;
  color: #fff;
  border-left: 1px solid #915b2f;
  border-right: 1px solid #915b2f;
  margin: 0 -1px;
}*/
.category li {
  list-style: none;
  float: left;
  width: 50px;
  height: 45px;
  padding-top: 5px;
  cursor: pointer;
}
.category .ico_comm {
  display: block;
  margin: 0 auto 2px;
  width: 22px;
  height: 26px;
}
.category .ico_coffee {
  background-position: -10px 0;
}
.category .ico_store {
  background-position: -10px -36px;
}
.category .ico_subway {
  background-position: -10px -72px;
}
</style>
