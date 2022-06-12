<template>
  <div
    class="rounded-pill"
    style="text-align: center; margin: 2rem auto; width: 100%"
  >
    <form method="post" @submit="commentRegist">
      <b-form-input
        class="comment_input"
        id="input-1"
        v-model="comment_content"
        type="text"
        placeholder="여러분의 생각을 남겨보세요."
        required
      ></b-form-input>

      <button
        class="btn btn-outline-primary rounded-pill col-2 mt-3 mb-3 add_comment_btn"
        type="submit"
        value="작성"
        style="margin-left: 1rem"
      >
        작성
      </button>
      <button
        @click="onReset"
        class="btn btn-outline-danger rounded-pill col-2 mt-3 mb-3 cancel_btn"
      >
        취소
      </button>
    </form>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const commentStore = "commentStore";
const accountStore = "accountStore";
export default {
  data() {
    return {
      comment_content: "",
    };
  },
  methods: {
    ...mapActions(commentStore, ["addComment"]),
    onReset(event) {
      event.preventDefault();
      this.comment_content = "";
    },
    async commentRegist(event) {
      event.preventDefault();
      if (this.comment_content === "") {
        alert("댓글을 입력해주세요.");
      }
      const articleNo = this.$route.params.articleNo;
      let commentItem = {
        rid: this.userInfo.id,
        articleno: articleNo,
        rcontent: this.comment_content,
      };
      this.addComment(commentItem);
    },
  },
  computed: {
    ...mapState(accountStore, ["userInfo"]),
  },
};
</script>

<style scoped>
form {
  width: 80%;
  margin: auto;
}
.comment_input {
  margin: auto !important;
}
.btn-outline-primary {
  margin: 0px 1rem !important;
}
</style>
