import axios from "axios";
import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import accountStore from "./modules/AccountStore.js";
import noticeStore from "./modules/NoticeStore.js";
import articleStore from "./modules/ArticleStore.js";
import commentStore from "./modules/CommentStore.js";
import newsStore from "./modules/NewsStore.js";
import { fetch_house_keyword_search } from "@/api/index.js";

Vue.use(Vuex);
// import {} from "../api/index.js";
// fetch_Sido,
// fetch_Gugun

export const store = new Vuex.Store({
  modules: {
    accountStore,
    noticeStore,
    articleStore,
    commentStore,
    newsStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        console.log(dong.dongCode, dong.dong);
        state.dongs.push({ value: dong.dongCode, text: dong.dong });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST(state) {
      state.houses = [];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      console.log("Mutations", house);
      state.house = house;
    },
  },
  actions: {
    getSido({ commit }) {
      // fetch_Gugun()
      //   .then(({ data }) => {
      //     // console.log(data);
      //     commit("SET_SIDO_LIST", data);
      //   })
      //   .catch((error) => {
      //     console.log(error);
      //   });
      axios
        .get(`http://localhost:80/happyhouse/api/map/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      axios
        .get(`http://localhost:80/happyhouse/api/map/gugun`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getDong({ commit }, gugunCode) {
      const params = { gugun: gugunCode };
      await axios
        .get(`http://localhost:80/happyhouse/api/map/dong`, { params })
        .then(({ data }) => {
          console.log("donglist", data);
          commit("SET_DONG_LIST", data);
          console.log(this.state.dongs);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getHouseList({ commit }, dongCode) {
      const params = { dong: dongCode };
      await axios
        .get(`http://localhost:80/happyhouse/api/map/apt`, { params })
        .then(({ data }) => {
          console.log("houselist", data);
          commit("SET_HOUSE_LIST", data);
          console.log(this.state.houses);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // getHouseList({ commit }, gugunCode) {
    //   // vue cli enviroment variables 검색
    //   //.env.local file 생성.
    //   // 반드시 VUE_APP으로 시작해야 한다.
    //   // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
    //   const SERVICE_KEY =
    //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
    //   const SERVICE_URL =
    //     "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
    //   const params = {
    //     LAWD_CD: gugunCode,
    //     DEAL_YMD: "202203",
    //     serviceKey: decodeURIComponent(SERVICE_KEY),
    //   };
    //   axios
    //     .get(SERVICE_URL, { params })
    //     .then(({ data }) => {
    //       // console.log(commit, data);
    //       console.log(data);
    //       commit("SET_HOUSE_LIST", data.response.body.items.item);
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      console.log(house);
      commit("SET_DETAIL_HOUSE", house);
    },

    async searchHouseList({ commit }, params) {
      console.log(commit);
      const { data } = await fetch_house_keyword_search(params);
      commit("SET_HOUSE_LIST", data);
    },
  },
});
