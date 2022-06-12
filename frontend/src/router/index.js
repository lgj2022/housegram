import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import AccountView from "../views/AccountView.vue";
import SignIn from "../components/account/SignIn.vue";
import SignUp from "../components/account/SignUp.vue";
import AccountUpdate from "../components/account/AccountUpdate.vue";
import AccountDetail from "../components/account/AccountDetail.vue";
import AccountProfile from "../components/account/AccountProfile.vue";
import AccountChangePw from "../components/account/AccountChangePw.vue";
import NoticeView from "../views/NoticeView.vue";
import NoticeList from "../components/notice/NoticeList.vue";
import NoticeCreate from "../components/notice/NoticeCreate.vue";
import NoticeDetail from "../components/notice/NoticeDetail.vue";
import NoticeModify from "../components/notice/NoticeModify.vue";
import NewsList from "../components/news/NewsList.vue";
import NewsView from "../views/NewsView.vue";
import { store } from "@/store/index.js";
// import KakaoMap from "../components/kakaomap/KaKaoMap.vue";

Vue.use(VueRouter);
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["accountStore/checkUserInfo"];
  const getUserInfo = store._actions["accountStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  console.log(checkUserInfo);
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};
const routes = [
  {
    path: "/",
    name: "home",
    components: {
      default: HomeView,
    },
  },
  {
    path: "/account",
    name: "account",
    component: AccountView,
    children: [
      // 회원가입
      {
        path: "signUp",
        name: "signUp",
        component: SignUp,
      },
      // 로그인
      {
        path: "signIn",
        name: "signIn",
        component: SignIn,
      },
      {
        path: "update",
        name: "AccountUpdate",
        component: AccountUpdate,
      },
      {
        path: "detail",
        name: "AccountDetail",
        component: AccountDetail,
      },

      {
        path: "profile",
        name: "AccountProfile",
        component: AccountProfile,
      },
      {
        path: "changepw",
        name: "AccountChangePw",
        component: AccountChangePw,
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: NoticeView,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",

        component: NoticeList,
      },
      {
        path: "regist",
        name: "noticeRegist",
        beforeEnter: onlyAuthUser,
        component: NoticeCreate,
      },
      {
        path: "detail/:noticeNo",
        name: "noticeDetail",
        beforeEnter: onlyAuthUser,
        component: NoticeDetail,
      },
      {
        path: "modify/:noticeNo",
        name: "noticeModify",
        beforeEnter: onlyAuthUser,
        component: NoticeModify,
      },
      // {
      //   path: "delete/:notcieId",
      //   name: "delete",
      //   component: NoticeDelete,
      // },
    ],
  },
  {
    path: "/article",
    name: "article",
    component: () => import("@/views/ArticleView.vue"),
    redirect: "/article/list",
    children: [
      {
        path: "list",
        name: "articleList",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/community/ArticleList.vue"),
      },
      {
        path: "write",
        name: "articleRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/community/ArticleRegister.vue"),
      },
      // {
      //   path: "detail/:articleno",
      //   name: "articleDetail",
      //   component: () => import("@/components/community/ArticleDetail.vue"),
      // },
      {
        path: "detail/:articleNo",
        name: "articleDetail",
        component: () => import("@/components/community/ArticleDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "articleModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/community/ArticleModify.vue"),
      },
      // {
      //   path: "delete/:articleno",
      //   name: "articleDelete",
      //   beforeEnter: onlyAuthUser,
      //   component: () => import("@/components/community/BoardDelete.vue"),
      // },
    ],
  },
  {
    path: "/house",
    name: "house",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/HouseView.vue"),
  },
  {
    path: "/news",
    name: "news",
    component: NewsView,
    redirect: "/news/list",
    children: [
      {
        path: "list",
        name: "NewsList",
        component: NewsList,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
