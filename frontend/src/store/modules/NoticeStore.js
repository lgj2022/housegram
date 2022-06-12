import {
  registNotice,
  updateNotice,
  deleteNotice,
  fetchNoticeList,
  fetchNoticeItem,
  searchNotice,
} from "@/api/Notice.js";
const noticeStore = {
  namespaced: true,
  state: {
    noticeList: [],
    noticeItem: {},
    pageLink: {
      startPage: "",
      currentEndPage: "",
      lastPage: "",
      currentPage: "",
    },
  },
  getters: {
    getNoticeList: function (state) {
      return state.noticeList;
    },
  },
  mutations: {
    SET_NOTICE(state, data) {
      state.noticeList = data;
    },
    SET_NOTICE_LIST(
      state,
      { notices, startPage, currentEndPage, lastPage, currentPage }
    ) {
      state.noticeList = notices;
      state.pageLink = { startPage, currentEndPage, lastPage, currentPage };
    },
    SET_NOTICE_ITEM(state, noticeItem) {
      state.noticeItem = noticeItem;
    },

    CLEAR_NOTICE_ITEM(state) {
      state.noticeItem = {};
    },
  },
  actions: {
    // notice actions
    // async fetch_noticeList({ commit }, pg) {
    //   const { data } = await fetchNoticeList(pg);
    //   commit("SET_NOTICE_LIST", { ...data });
    // },

    async fetch_noticeList({ commit }, { ...query }) {
      // const { data } = await fetchNoticeList({ ...query });
      // console.log(query);
      const { data } = await fetchNoticeList(query);
      commit("SET_NOTICE_LIST", { ...data });
    },

    async fetch_noticeItem({ commit }, noticeNo) {
      const { data } = await fetchNoticeItem(noticeNo);
      commit("SET_NOTICE_ITEM", data.notice);
    },
    async addNotice(_, noticeItem) {
      const response = await registNotice(noticeItem);
      if (response.status === 200) {
        return true;
      }
    },

    async updateNotice(_, { noticeNo, ...noticeItem }) {
      const response = await updateNotice(noticeNo, noticeItem);
      return response;
    },

    async deleteNoticeItem({ commit }, noticeNo) {
      const response = await deleteNotice(noticeNo);
      console.log(response);
      commit("CLEAR_NOTICE_ITEM");
    },

    async searchNoticeList({ commit }, { type, keyword }) {
      const { data } = await searchNotice(type, keyword);
      console.log(data, "찍어봐");
      commit("SET_NOTICE_LIST", data);
    },
  },
};

export default noticeStore;
