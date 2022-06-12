<template>
  <div class="createWrap">
    <form class="accountCreatForm" method="POST">
      <ul>
        <li>
          <label for="signUpId">ID</label>
        </li>
        <li>
          <input
            type="text"
            name="signUpId"
            id="signUpId"
            v-model.lazy="user.userId"
          />
        </li>
        <p class="password_rule" :class="{ danger: !this.ableId }">
          {{ idRuleCheck }}
        </p>
        <li>
          <label for="name">이름</label>
        </li>
        <li>
          <input
            type="text"
            name="name"
            id="name"
            v-model="user.name"
            required
          />
        </li>

        <li>
          <label for="password1">비밀번호</label>
        </li>
        <li>
          <input
            type="password"
            name="password1"
            id="password1"
            v-model.lazy="user.password"
            required
          />
        </li>
        <p class="password_rule" :class="{ danger: !this.passwordRule }">
          {{ passwordRuleCheck }}
        </p>
        <li>
          <label for="password2">비밀번호 확인</label>
        </li>
        <li>
          <input
            type="password"
            name="password2"
            id="password2"
            v-model.lazy="user.password2"
            required
          />
        </li>
        <p class="password_rule" :class="{ danger: !this.passwordSame }">
          {{ passwordSameCheck }}
        </p>
        <li>
          <label for="email">이메일</label>
        </li>
        <li>
          <input
            type="text"
            name="email"
            id="email"
            v-model="user.email"
            required
          />
        </li>
        <li>
          <label for="age">나이</label>
        </li>
        <li>
          <input type="text" name="age" id="age" v-model="user.age" required />
        </li>
        <li>
          <label for="phone">휴대전화</label>
        </li>
        <li>
          <input
            type="text"
            name="phone"
            id="phone"
            v-model="user.phone"
            required
          />
        </li>
        <li>
          <router-link class="loginLink" :to="{ name: 'signIn' }">
            이미 계정이 있으신가요?
          </router-link>
        </li>
      </ul>
      <div class="btnWrap">
        <button class="accountCreatBtn" @click="regist">확인</button>
        <!-- <button class="accountCreatCancle" @click="cancle">취소</button> -->
      </div>
    </form>
  </div>
</template>

<script>
import { userIdCheck } from "@/api/Account.js";
import { userSignUp } from "@/api/Account.js";
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
      passwordRule: null,
      passwordSame: null,
      passwordAble: false,
      ableId: null,
    };
  },
  methods: {
    // 비동기 통신으로 값이 준비될때까지 대기
    async regist(event) {
      event.preventDefault();
      if (
        this.user.userId === "" ||
        this.user.password === "" ||
        this.user.password2 === "" ||
        this.user.name === "" ||
        this.user.email === "" ||
        this.user.phone === "" ||
        this.user.age === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let user = {
        id: this.user.userId,
        pw: this.user.password,
        name: this.user.name,
        email: this.user.email,
        phone: this.user.phone,
        age: this.user.age,
        img: "#",
      };
      const response = await userSignUp(user);
      if (response.status === 200) {
        alert("회원가입 성공");
        this.$router.push("/");
      } else {
        alert("회원가입시 문제가 발생했습니다.");
      }
    },
  },
  watch: {
    "user.userId": async function () {
      console.log("useridcheck 시작");
      console.log(this.user.userId);
      const response = await userIdCheck(this.user.userId);
      console.log(response);
      if (response.data.ckid === "possible") {
        this.ableId = true;
      } else {
        this.ableId = false;
      }
      console.log(this.ableId);
    },
    "user.password": function () {
      console.log("password룰 체크 시작");
      const regex =
        /^.*(?=.{8,10})(?=.*[a-zA-Z])(?=.*?[A-Z])(?=.*\d)(?=.+?[\W|_])[a-zA-Z0-9!@#$%^&*()-_+={}\\]+$/g;
      if (regex.test(this.user.password)) {
        this.passwordRule = true;
      } else {
        this.passwordRule = false;
      }
    },

    "user.password2": function () {
      console.log("password 2룰 체크 시작");
      if (this.user.password === this.user.password2) {
        console.log("같다");
        this.passwordSame = true;
      } else {
        console.log("다르다");
        this.passwordSame = false;
      }
      console.log(this.passwordSame);
    },
  },

  computed: {
    idRuleCheck: function () {
      /* eslint-disable no-useless-escape */
      if (this.ableId === true) {
        return "사용 가능한 id 입니다";
      } else if (this.ableId === false) {
        return "이미 존재하는 아이디입니다";
      } else {
        return "";
      }
    },

    passwordRuleCheck: function () {
      if (this.passwordRule === true) {
        return "사용 가능한 pw 입니다";
      } else if (this.passwordRule === false) {
        return "8~16자 대,소문자 특수문자를 입력해주세요 ";
      } else {
        return "";
      }
    },

    passwordSameCheck: function () {
      /* eslint-disable no-useless-escape */
      if (this.passwordSame === true) {
        return "패스워드와 일치 합니다";
      } else if (this.passwordSame === false) {
        return "패스워드와 일치 하지 않습니다";
      } else {
        return "";
      }
    },
  },
};
</script>

<style scoped>
.createWrap {
  display: flex;
  justify-content: center;
  padding: 0px 0 0px 0;
}

.createWrap .accountCreatForm {
  text-align: center;
  width: 500px;
  padding: 25px;
}

.createWrap .accountCreatForm ul li {
  margin-bottom: 20px;
}

.createWrap .accountCreatForm ul li input {
  border: none;
  outline: none;
  text-align: center;
  border-radius: 7px;
  padding: 5px;
  font-size: 18px;
  width: 63%;
  height: 30px;
  box-shadow: 0 0 5px #ccc;
}

.accountCreatForm .loginLink {
  color: cornflowerblue;
}

.error_message {
  text-align: center;
  color: red;
  font-weight: bold;
}

.btnWrap {
  margin-top: 20px;
  text-align: center;
}

.btnWrap .accountCreatBtn,
.btnWrap .accountCreatCancle {
  font-size: 16px;
  padding: 8px 20px;
  outline: none;
  border: 1px solid transparent;
  color: #fff;
}

.btnWrap .accountCreatBtn {
  background: cornflowerblue;
}

.btnWrap .accountCreatCancle {
  background-color: #ff5757;
}

.password_rule,
.password_same {
  color: blue;
  font-size: 13px;
}

.password_rule.danger,
.password_same .danger {
  color: red;
}
</style>
