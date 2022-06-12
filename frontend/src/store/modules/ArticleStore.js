import {
  //   registArticle,
  updateArticle,
  deleteArticle,
  fetchArticleList,
  fetchArticleItem,
  searchArticle,
  recomendTag,
} from "@/api/Article.js";
const articleStore = {
  namespaced: true,
  state: {
    articleList: [],
    articleItem: {},
    recomendTagList: [],
    pageLink: {
      startPage: "",
      currentEndPage: "",
      lastPage: "",
      currentPage: "",
    },
  },
  getters: {
    getArticleList: function (state) {
      return state.noticeList;
    },
  },
  mutations: {
    SET_ARTICLE(state, data) {
      state.noticeList = data;
    },
    SET_ARTICLE_LIST(
      state,
      { articles, startPage, currentEndPage, lastPage, currentPage }
    ) {
      state.articleList = articles;
      state.pageLink = { startPage, currentEndPage, lastPage, currentPage };
    },
    SET_ARTICLE_ITEM(state, articleItem) {
      state.articleItem = articleItem;
    },

    CLEAR_ARTICLE_ITEM(state) {
      state.articleItem = {};
    },
    SET_TAGLIST(state, data) {
      state.recomendTagList = data;
      console.log(state.recomendTagList);
    },
  },
  actions: {
    async fetch_articleList({ commit }, { ...query }) {
      console.log(query, "쿼리값");
      const { data } = await fetchArticleList(query);
      commit("SET_ARTICLE_LIST", { ...data });
    },

    async fetch_articleItem({ commit }, noticeNo) {
      const { data } = await fetchArticleItem(noticeNo);
      commit("SET_ARTICLE_ITEM", data.article);
    },

    async updateArticle(_, params) {
      console.log(params, "스토어");
      const response = await updateArticle(params);
      return response;
    },

    async deleteArticleItem({ commit }, articleNo) {
      const response = await deleteArticle(articleNo);
      console.log(response);
      commit("CLEAR_ARTICLE_ITEM");
    },

    async searchArticleList({ commit }, { type, keyword }) {
      const { data } = await searchArticle(type, keyword);
      commit("SET_ARTICLE_LIST", data);
    },
    async recomendArticleList({ commit }) {
      const response = await recomendTag();
      //console.log(response.data.recomends);
      commit("SET_TAGLIST", response.data.recomends);
    },
  },
};

export default articleStore;
