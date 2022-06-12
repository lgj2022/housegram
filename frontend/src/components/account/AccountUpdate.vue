<template>
  <div class="password_change_container">
    <div>
      <h4>유저 정보 변경</h4>
    </div>
    <form action="/accounts/update/" method="post">
      <div>
        <div style="position: relative">
          <label
            class="update_password_label"
            name="label_new_password2"
            for="id_new_password2"
          >
            <i class="fas fa-lock"></i>
            이름
          </label>
          <input
            v-model="userInfo.name"
            name="new_password2"
            autocomplete="new-password"
            class="form-control new_password2"
            required
            id="id_new_password2"
          />
        </div>
      </div>
      <div>
        <div style="position: relative">
          <label
            class="update_password_label"
            name="label_new_password2"
            for="email"
          >
            <i class="fas fa-lock"></i>
            이메일
          </label>
          <input
            v-model="userInfo.email"
            name="new_password2"
            autocomplete="new-password"
            class="form-control userInfo_update"
            required
            id="email"
          />
        </div>
      </div>
      <div>
        <div style="position: relative">
          <label
            class="update_password_label"
            name="label_new_password2"
            for="age"
          >
            <i class="fas fa-lock"></i>
            나이
          </label>
          <input
            v-model="userInfo.age"
            name="new_password2"
            autocomplete="new-password"
            class="form-control userInfo_update"
            required
            id="age"
          />
        </div>
      </div>
      <div>
        <div style="position: relative">
          <label
            class="update_password_label"
            name="label_new_password2"
            for="phone"
          >
            <i class="fas fa-lock"></i>
            휴대전화 번호
          </label>
          <input
            name="new_password2"
            autocomplete="new-password"
            class="form-control userInfo_update"
            v-model="userInfo.phone"
            id="phone"
          />
        </div>
      </div>

      <div style="margin-top: 2rem">
        <router-link
          :to="{ name: 'AccountDetail' }"
          class="btn btn-sm"
          id="back_btn"
          >&nbsp;돌아가기</router-link
        >
        <button @click.prevent="modifyUser" class="btn btn-sm" id="detail_btn">
          &nbsp;변경하기
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import { userUpdate, userDelete } from "@/api/Account.js";
const accountStore = "accountStore";
export default {
  data() {
    return {
      user: {},
    };
  },
  computed: {
    ...mapState(accountStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(accountStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    async modifyUser(event) {
      event.preventDefault();
      const response = await userUpdate(this.userInfo);
      if (response.status === 200) {
        alert("수정되었습니다.");
        this.$router.push("/");
      } else {
        alert("수정 실패 다시 시도해 주세요.");
      }
    },

    async deleteUser(event) {
      event.preventDefault();
      console.log(this.userInfo);
      console.log("삭제 axios 시작");
      const response = await userDelete(this.userInfo.id);
      if (response.status === 200) {
        alert("삭제되었습니다.");
        this.logout();
      } else {
        alert("수정 실패 다시 시도해 주세요.");
      }
    },
  },
};
</script>

<style>
.form-control {
  text-align: center;
  width: 75% !important;
}
.password_change_container {
  text-align: center;
  max-width: 31rem;
  min-height: 31rem;
  margin: 4rem auto;
  border-radius: 1rem;
  box-shadow: 0 0 4px #ccc;
  padding: 3rem;
}
.update_password_label {
  font-size: 0.5rem;
  position: absolute;
  top: -1.5rem;
  left: 3rem;
}
.old_password,
.new_password1,
.new_password2,
.userInfo_update,
.is-invalid {
  width: 20rem;
  margin: 2.5rem auto 1rem auto;
}
</style>
