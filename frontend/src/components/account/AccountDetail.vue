<template>
  <div style="text-align: center; max-width: 500px; margin: 4rem auto">
    <!-- {% if target_user.profile %} -->
    <template>
      <img
        v-if="userInfo.profile_img"
        :src="this.userInfo.profile_img"
        alt=""
        style="
          height: 12rem;
          width: 12rem;
          border-radius: 20rem;
          margin-bottom: 2rem;
          object-fit: cover;
        "
      />
      <span v-else class="material-icons" style="font-size: 12rem">
        <i v-show="!getProfileImg" class="fas fa-user-circle"></i>
      </span>
    </template>

    <h2 style="margin-left: 2.5rem">
      {{ userInfo.name }}
      <span
        ><router-link :to="{ name: 'AccountProfile' }" class="profileEdit">
          <i class="fas fa-edit"></i> </router-link
      ></span>
    </h2>
    <div class="profile_func_btn">
      <router-link :to="{ name: 'AccountChangePw' }">
        <i class="fas fa-lock"></i>
      </router-link>
      <router-link :to="{ name: 'AccountUpdate' }" id="userInfoEdit">
        <i class="fas fa-cog"></i>
      </router-link>
      <a href="#" id="userDelete" @click.prevent="deleteUser">
        <i class="fas fa-trash-alt"></i>
      </a>
    </div>
    <div class="profile_func_btn">
      <router-link :to="{ name: 'home' }" id=""> 뒤로가기 </router-link>
    </div>
  </div>
</template>
<script>
import { mapState, mapMutations } from "vuex";
import { userDelete } from "@/api/Account.js";
const accountStore = "accountStore";
export default {
  data() {
    return {
      image: "",
    };
  },
  computed: {
    ...mapState(accountStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(accountStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    moveProfile(event) {
      event.preventDefault();
      this.$router.push({ name: "AccountProfile" });
    },
    moveUpdatePage(event) {
      event.preventDefault();
      this.$router.push({ name: "AccountUpdate" });
    },
    async deleteUser(event) {
      event.preventDefault();
      if (confirm("삭제하시겠습니까?")) {
        const response = await userDelete(this.userInfo.id);
        if (response.status === 200) {
          alert("삭제되었습니다.");
          this.logout();
        } else {
          alert("수정 실패 다시 시도해 주세요.");
        }
      }
    },
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);

      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>
<style scoped>
a {
  text-decoration: none !important;
  color: black;
}
.test {
  display: block;
}
.profileEdit {
  /* box-shadow: 0 0 4px #ccc; */
  border-radius: 10rem;
  /* padding: 0.3rem; */
  top: -4px;
  left: 10px;
  position: relative;
}

.profileEdit .fa-edit {
  position: relative;
  color: black;
  font-size: 22px;
}
.fa-edit {
  position: relative;
}
#userInfoEdit {
  margin-right: 15px;
  box-shadow: 0 0 4px #ccc;
  border-radius: 10rem;
  padding: 0.3rem;
  font-size: 20px;
}
#userDelete {
  box-shadow: 0 0 4px #fcc;
  border-radius: 10rem;
  padding: 0.3rem;
  font-size: 20px;
}
.fa-lock {
  margin-right: 15px;
  box-shadow: 0 0 4px #ccc;
  border-radius: 10rem;
  padding: 0.3rem;
  font-size: 20px;
}
.profile_func_btn {
  margin-top: 20px;
}
</style>
