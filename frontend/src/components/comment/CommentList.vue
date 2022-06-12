<template>
  <div>
    <template>
      <comment-list-item
        v-for="comment in commentList"
        :key="comment.rno"
        v-bind="comment"
      ></comment-list-item>
    </template>
  </div>
</template>

<script>
import CommentListItem from "@/components/comment/CommentListItem.vue";
import { mapActions, mapState } from "vuex";
const commentStore = "commentStore";
export default {
  data() {
    return {
      replys: [],
    };
  },
  components: {
    CommentListItem,
  },
  async created() {
    const articleNo = this.$route.params.articleNo;
    await this.fetch_commentList(articleNo);
    this.replys = this.commentList;
  },
  methods: {
    ...mapActions(commentStore, ["fetch_commentList"]),
  },
  watch: {
    // commentList: async function () {
    //   const articleNo = this.$route.params.articleNo;
    //   await this.fetch_commentList(articleNo);
    // },
  },
  computed: {
    ...mapState(commentStore, ["commentList"]),
  },
};
</script>

<style></style>
