import { userSignIn, findById } from "@/api/Account.js";
import jwt_decode from "jwt-decode";
const accountStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkUserProfile: function (state) {
      if (state.userInfo === null) {
        console.log("유저정보 없음");
        return false;
      }
      if (state.userInfo.profile_img === null) {
        console.log("프로필 이미지없음");
        return false;
      }
      return true;
    },
    checkUserAuthority: function (state) {
      if (state.userInfo === null || !state.userInfo.authority) {
        return false;
      }
      return true;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      // state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    // Account
    async accountSignIn({ commit }, { id, password }) {
      const response = await userSignIn(id, password);
      if (response.data.message === "success") {
        // commit("SET_SIGNIN_USER", response.data);
        let token = response.data["access-token"];
        commit("SET_IS_LOGIN", true);
        commit("SET_IS_LOGIN_ERROR", false);
        sessionStorage.setItem("access-token", token);
        return "성공";
      } else {
        commit("SET_IS_LOGIN", false);
        commit("SET_IS_LOGIN_ERROR", true);
        return "실패";
      }
    },

    async getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      const { data } = await findById(decode_token.userInfo.id);
      if (data.message === "success") {
        commit("SET_USER_INFO", data.userInfo);
      } else {
        console.log("유저 정보 없음!!");
      }
      // (error) => {
      //   console.log(error);
      // };
    },
  },
};

export default accountStore;
