<template>
  <div class="comment_wrap">
    <div class="comment_edit" v-if="rid === userInfo.id">
      <span id="comment_modify" @click="modifyComment">수정</span>
      <span id="comment_delete" @click="deleteComment">삭제</span>
    </div>
    <div class="comment_edit_pencil" v-if="rid === userInfo.id">
      <i class="fas fa-pencil-alt" @click="modeChange"></i>
    </div>
    <p>
      <input
        type="text"
        v-model="replyContent"
        :class="{ updateMode: updateMode }"
        class="updateComment"
      />
    </p>
    <span>작성자 :{{ rid }}</span>

    <span class="reply_created">({{ rcreatedAt }})</span>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const accountStore = "accountStore";
const commentStore = "commentStore";
export default {
  data() {
    return {
      replyContent: "",
      updateMode: false,
    };
  },
  props: {
    articleno: String,
    rcontent: String,
    rcreatedAt: String,
    rid: String,
    rno: String,
  },
  created() {
    this.replyContent = this.rcontent;
  },
  computed: {
    ...mapState(accountStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(commentStore, ["deleteCommentItem", "updateComment"]),
    async deleteComment() {
      if (confirm("정말로 삭제?")) {
        await this.deleteCommentItem({
          rno: this.rno,
          articleno: this.articleno,
        });
      }
    },
    async modifyComment() {
      if (confirm("수정하시겠습니까??")) {
        await this.updateComment({
          rno: this.rno,
          rcontent: this.replyContent,
          articleno: this.articleno,
        });
        this.modeChange();
      }
    },
    modeChange() {
      this.updateMode = !this.updateMode;
      // const updateComment = document.querySelector(".updateComment");
      // updateComment.readOnly = !updateComment.readOnly;
    },
  },
};
</script>

<style scoped>
.comment_wrap {
  padding: 8px 20px 8px 30px;
  border: 1px solid gray;
  border-radius: 10px;
  position: relative;
}
.reply_created {
  font-size: 14px;
}
.comment_edit {
  position: absolute;
  right: 10px;
}

#comment_modify {
  margin-right: 10px;
}

#comment_modify,
#comment_delete {
  cursor: pointer;
}

.comment_wrap {
  margin-bottom: 10px;
}

.updateComment {
  border: none;
  pointer-events: none;
}

.updateComment.updateMode {
  border: 1px solid;
  pointer-events: auto;
}

.comment_edit_pencil {
  position: absolute;
  left: 10px;
  cursor: pointer;
}
</style>
