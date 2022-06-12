import {
  fetchCommentList,
  registComment,
  updateComment,
  deleteComment,
} from "@/api/Comment.js";
const commentStore = {
  namespaced: true,
  state: {
    commentList: [],
    commentItem: {},
  },
  getters: {},
  mutations: {
    SET_COMMENT_LIST(state, data) {
      state.commentList = data;
    },
    CLEAR_COMMENT_ITEM(state) {
      state.commentItem = {};
    },
  },
  actions: {
    // notice actions
    // async fetch_noticeList({ commit }, pg) {
    //   const { data } = await fetchNoticeList(pg);
    //   commit("SET_NOTICE_LIST", { ...data });
    // },

    async fetch_commentList({ commit }, articleNo) {
      console.log(articleNo, "댓글");
      const { data } = await fetchCommentList(articleNo);
      commit("SET_COMMENT_LIST", data.replys);
    },

    async addComment({ dispatch }, commentItem) {
      const response = await registComment(commentItem);
      if (response.status === 200) {
        console.log("댓글 달기 성공.");
        await dispatch("fetch_commentList", commentItem.articleno);
        return true;
      }
    },

    async updateComment({ commit, dispatch }, { ...commentItem }) {
      const response = await updateComment(commentItem);
      commit("CLEAR_COMMENT_ITEM");
      await dispatch("fetch_commentList", commentItem.articleno);
      return response;
    },

    async deleteCommentItem({ commit, dispatch }, { rno, articleno }) {
      await deleteComment(rno);
      commit("CLEAR_COMMENT_ITEM");
      await dispatch("fetch_commentList", articleno);
    },
  },
};

export default commentStore;
