import { fetchNewsList } from "@/api/News.js";
const newsStore = {
  namespaced: true,
  state: {
    newsList: [],
    newsItem: {},
  },
  getters: {},
  mutations: {
    SET_News(state, data) {
      state.newsList = data;
    },

    SET_NOTICE_ITEM(state, newsItem) {
      state.newsItem = newsItem;
    },

    CLEAR_NOTICE_ITEM(state) {
      state.newsItem = {};
    },
    CLEAR_NOTICE(state) {
      state.newsList = [];
    },
  },
  actions: {
    async fetch_newsList({ commit }) {
      // const { data } = await fetchNoticeList({ ...query });
      // console.log(query);
      const response = await fetchNewsList();
      console.log("response", response.data.news);
      commit("SET_News", response.data.news);
    },
  },
};

export default newsStore;
