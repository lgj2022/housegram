<template>
  <nav>
    <div class="navbar">
      <div class="logo">
        <router-link to="/"><i class="fas fa-leaf"></i>Happy House</router-link>
      </div>
      <ul class="menu">
        <li class="menu-item">
          <router-link :to="{ name: 'house' }">실거래가 조회</router-link>
        </li>
        <li class="menu-item">
          <router-link :to="{ name: 'article' }">커뮤니티</router-link>
        </li>
        <li class="menu-item">
          <router-link :to="{ name: 'notice' }">공지 사항</router-link>
        </li>
        <li class="menu-item">
          <router-link :to="{ name: 'news' }">오늘의 뉴스</router-link>
        </li>
      </ul>
      <ul class="user-account-btn-list">
        <li>
          <router-link
            :to="{ name: 'signIn' }"
            class="user-account-btn"
            :class="{ active: !isLogin }"
          >
            로그인
          </router-link>
        </li>
        <li class="user-account-btn logout-btn" :class="{ active: isLogin }">
          <a href="#" @click.prevent="logout">로그아웃</a>
        </li>
        <li class="user-account-btn" :class="{ active: isLogin }">
          <i
            v-show="!getProfileImg"
            class="fas fa-user-circle"
            @click="userDetail()"
          ></i>
          <img
            v-if="isLogin && userInfo.profile_img"
            id="profile_img"
            @click="userDetail()"
            :src="this.userInfo.profile_img"
          />
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { mapMutations, mapState, mapGetters } from "vuex";
const accountStore = "accountStore";
export default {
  components: {},
  data() {
    return {};
  },
  computed: {
    ...mapState(accountStore, ["isLogin", "userInfo"]),
    ...mapGetters(accountStore, ["checkUserProfile"]),
    getProfileImg() {
      if (this.userInfo === null) {
        return false;
      } else if (this.userInfo.profile_img === null) {
        return false;
      }
      this.imgSrcChange();
      return true;
    },
  },
  methods: {
    async imgSrcChange() {
      let img = document.querySelector("#profile_img");
      if (img != null && this.userInfo != null) {
        console.log(img);
        // img.src = `http://127.0.0.1:8887/${this.userInfo.profile_img}`;
        img.classList.add("active");
      }
      // img.src = `http://127.0.0.1:8887/${this.userInfo.profile_img}`;
    },
    ...mapMutations(accountStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);

      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
      alert("로그아웃 되었습니다.");
    },
    userDetail() {
      this.$router.push({ name: "AccountDetail" });
    },
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: gray;
  cursor: pointer;
}
a:hover {
  color: cornflowerblue;
  text-decoration: none;
}
ul {
  margin: 0px;
}

/* font awesome icon css */
.fa-search {
  /* line-height: 24px; */
}

.fa-leaf {
  color: green;
  margin-right: 5px;
}

@keyframes titleAnimation {
  from {
    top: 200px;
  }

  to {
    top: 70px;
  }
}

/*  navbar css */

nav {
  font-weight: bold;
  /* width: 100vw; */
  width: calc(100% - 40px);
  margin: 0 auto;
  /* background-color: rgb(151, 151, 237); */
  position: relative;
  /* box-shadow: 0px 5px 10px 2px rgb(0 0 0 / 10%); */
}

nav.active {
  background-color: #afb3d9;
  opacity: 0.8;
}

.navbar {
  color: black;
  display: flex;
  /* height: 40px; */
  line-height: 40px;
  padding: 20px 30px;
  justify-content: space-between;
}

/* .logo {
  height: 26px;
} */
.logo a {
  font-family: "Lobster", cursive;
  font-size: 20px;
}
.menu,
.user-account-btn-list {
  display: flex;
}

.menu.active,
.user-account-container.active {
  display: flex;
}

.menu-item {
  opacity: 1;
  color: balck;
  list-style: none;
  /* line-height: 34px; */
  margin-right: 10px;
  margin-left: 20px;
}

.menu-item:first-child {
  margin-left: 0px;
}

.menu-item:last-child {
  margin-right: 0;
}

.user-account-container {
  /* padding: 8px 20px; */
}

.user-account-btn {
  /* line-height: 34px; */
  display: none;
  margin-right: 15px;
  cursor: pointer;
  position: relative;
}

.user-account-btn.active {
  display: inline-block;
}

.menu-item:hover,
.user-account-btn:hover {
  color: cornflowerblue;
}

/* profile css */
.fa-user-circle {
  font-size: 28px;
  position: relative;
  top: 6px;
  color: gray;
  right: -10px;
  /* display: none; */
  border-radius: 100%;
}

.profile-btn {
  background-color: transparent;
  border: 0px;
  outline: none;
  position: relative;
}
#profile_img {
  top: 3px;
  position: absolute;
  right: -40px;
  /* display: none; */
  height: 35px;
  width: 35px;
  border-radius: 100%;
}

#profile_img.active {
  display: block;
}

profile .fa-bars {
  visibility: hidden;
  font-size: 25px;
  position: absolute;
  top: 25px;
  right: 25px;
  cursor: pointer;
}

/* dropdown css */
.form-select {
  margin-right: 20px;
  width: 150px;
}

.form-select:last-child {
  margin-right: 0px;
}

/* Map css */

.map {
  width: 100vw;
  height: 300px;
  border: 1px solid black;
}

/* info css -> js로 추가 */
.col-4 {
  height: 500px;
  overflow: auto;
}

.list-group-item {
}

/* 반응형 */
@media screen and (max-width: 775px) {
  .content-list {
    display: flex;
    flex-direction: column;
  }

  .ad-container {
    width: 95%;
  }

  .tip-container,
  .news-container {
    padding-top: 10px;
    padding-left: 10px;
    margin-top: 10px;
    border-top: 1px solid #eeeeee;
  }

  .tip-list {
    margin: 0 0 0 2px;
  }
}

@media screen and (max-width: 660px) {
  .navbar {
    align-items: flex-start;
    flex-direction: column;
    padding: 16px 20px 10px 20px;
  }

  .user-account-container {
    right: 0px;
    position: absolute;
    top: 34px;
  }

  .fa-bars {
    visibility: visible;
  }

  .menu,
  .user-account-container {
    display: none;
    margin-top: 10px;
  }

  .searchForm {
    right: 18px;
    top: 97px;
  }

  @media screen and (max-width: 520px) {
    .menu-item,
    .user-account-btn {
      font-size: 14px;
    }
  }
}
</style>
