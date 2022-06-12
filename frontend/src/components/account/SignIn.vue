<template>
  <div class="loginFormWrap">
    <div>간편 로그인</div>
    <form class="loginForm" method="POST">
      <b-alert show variant="danger" v-if="isLoginError"
        >아이디 또는 비밀번호를 확인하세요.</b-alert
      >
      <ul>
        <li>
          <input type="text" name="id" id="id" v-model="user.userId" />
          <label for="id">ID</label>
        </li>
        <li>
          <input
            type="password"
            name="password"
            id="password"
            v-model="user.userPassword"
          />
          <label for="password">비밀번호</label>
        </li>

        <div class="btnWrap">
          <button class="loginBtn" @click="signIn">확인</button>
          <button class="loginCancle" @click="signInCancle">취소</button>
        </div>

        <a id="custom-login-btn" @click="loginWithKakao">
          <img
            src="//k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
            width="222"
            alt="카카오 로그인 버튼"
          />
        </a>
        <li>
          <router-link class="createLink" :to="{ name: 'signUp' }">
            계정이 존재하지 않으신가요?
          </router-link>
        </li>
      </ul>
    </form>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";
import { userSignUp, userIdCheck } from "@/api/Account.js";
const accountStore = "accountStore";
export default {
  data() {
    return {
      user: {
        userId: "",
        password: "",
        password2: "",
        name: "",
        email: "",
        phone: "",
        age: 0,
        img: "",
      },
      ableId: null,

      kakaouser: {
        age: "",
        authority: "false",
        email: "",
        id: "",
        name: "",
        phone: "",
        profile_img: "",
        pw: "",
        salt: "",
      },
    };
  },
  watch: {
    "kakaouser.id": async function () {
      if (this.kakaouser.id != null) {
        await this.SET_USER_INFO(this.kakaouser);
        await this.SET_IS_LOGIN(true);

        const response = await userIdCheck(this.userInfo.id);
        if (response.data.ckid === "possible") {
          this.ableId = true;
        } else {
          this.ableId = false;
        }

        if (this.ableId === true) {
          await userSignUp(this.userInfo);
        }
        this.$router.push({ name: "home" });
      }
    },
  },
  mounted() {},
  computed: {
    ...mapState(accountStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapMutations(accountStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    ...mapActions(accountStore, ["accountSignIn", "getUserInfo"]),
    async signIn(event) {
      event.preventDefault();
      if (
        this.user.userId.length === 0 ||
        this.user.userPassword.length === 0
      ) {
        alert("모두 입력해주세요.");
      } else {
        await this.accountSignIn({
          id: this.user.userId,
          password: this.user.userPassword,
        });
        let token = sessionStorage.getItem("access-token");
        if (this.isLogin) {
          await this.getUserInfo(token);
          this.$router.push({ name: "home" });
        } else {
          alert("로그인에 실패하였습니다.");
        }
      }
    },
    signInCancle() {
      this.$router.push({ name: "home" });
    },
    async loginWithKakao() {
      let kakaouser = {
        age: "",
        email: "",
        id: "",
        name: "",
        phone: "",
        profile_img: "",
        pw: Math.random().toString(36),
        salt: "",
      };

      window.Kakao.Auth.login({
        success: function (response) {
          console.log("성공" + response);
          window.Kakao.API.request({
            url: "/v2/user/me",
            success: function (response) {
              kakaouser.id = response.kakao_account.email;
              kakaouser.email = response.kakao_account.email;
              kakaouser.profile_img =
                response.kakao_account.profile.profile_image_url;
              kakaouser.name = response.kakao_account.profile.nickname;
            },
            fail: function (error) {
              console.log(error);
            },
          });
        },
        fail: function (error) {
          console.log(error);
        },
      });
      this.kakaouser = kakaouser;
    },
    movePage() {
      this.$router.push({ name: "SignUp" });
    },
  },
};
</script>

<style scoped>
.loginFormWrap {
  padding: 80px 20px;
  text-align: center;
}

.loginFormWrap .loginForm {
  margin: 0px auto;
  max-width: 500px;
  padding: 25px;
}

.loginForm ul {
  text-align: center;
}

.loginForm ul li {
  margin: 0 auto;
  margin-bottom: 20px;
  width: 350px;
  position: relative;
}

.loginForm ul li input {
  width: 350px;
  height: 40px;
  outline: none;
  border: none;
  border-bottom: 1px solid;
  background-color: #fafafa;
  position: relative;
}

.loginForm ul li label {
  top: 15px;
  left: 0;
  position: absolute;
  transition: all 0.3s;
}

input[id="id"]:focus + label,
input[id="id"]:valid + label,
input[type="password"]:focus + label,
input[type="password"]:valid + label {
  top: -10px;
  font-size: 12px;
}

.error_message {
  height: 16px;
  text-align: center;
  color: red;
  font-weight: bold;
}

.btnWrap {
  margin-top: 40px;
  text-align: center;
}

.loginErrorWrap {
  margin-top: 20px;
  color: red;
}

.createLink {
  color: cornflowerblue;
}

.btnWrap .loginBtn,
.btnWrap .loginCancle {
  font-size: 16px;
  padding: 8px 20px;
  outline: none;
  border: 1px solid transparent;
  color: #fff;
}

.btnWrap {
  margin-bottom: 20px;
}

.btnWrap .loginBtn {
  background: cornflowerblue;
}

.loginBtn {
  margin-right: 30px;
}

.btnWrap .loginCancle {
  background-color: #ff5757;
}
</style>
