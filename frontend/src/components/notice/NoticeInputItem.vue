<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userId"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userId"
            :disabled="isUserid"
            v-model="notice.author"
            type="text"
            ref="userId"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="notice.title"
            type="text"
            ref="title"
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notice.content"
            placeholder="내용 입력..."
            rows="10"
            ref="content"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'regist'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1" @click="moveList()"
          >목록으로</b-button
        >
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions } from "vuex";
const accountStore = "accountStore";
const noticeStore = "noticeStore";
export default {
  data() {
    return {
      notice: {
        noticeNo: 0,
        author: "",
        title: "",
        content: "",
        hits: "",
      },
      isUserid: false,
    };
  },
  computed: {
    ...mapState(accountStore, ["userInfo"]),
    ...mapState(noticeStore, ["noticeItem"]),
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      this.isUserid = true;
      this.notice = this.noticeItem;
    } else {
      this.notice.author = this.userInfo.id;
    }
  },
  methods: {
    ...mapActions(noticeStore, ["updateNotice", "addNotice"]),
    // fillAuthor() {
    //   this.notice.author = this.signInUser.id;
    // },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.notice.author &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.notice.title &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.notice.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else this.type === "regist" ? this.registNotice() : this.modifyNotice();
    },
    onReset(event) {
      event.preventDefault();
      //   this.article.articleno = 0;
      //   this.article.subject = "";
      //   this.article.content = "";
      //   this.$router.push({ name: "noticeList" });
    },
    async registNotice() {
      let formData = new FormData();
      formData.append("userid", this.notice.author);
      formData.append("author", this.userInfo.name);
      formData.append("title", this.notice.title);
      formData.append("content", this.notice.content);
      const response = await this.addNotice(formData);
      if (response) {
        alert("글작성 성공");
        this.$router.push({ name: "noticeList" });
      } else {
        alert("작성 실패 잠시 후 다시 시도해 주세요.");
      }
    },
    async modifyNotice() {
      let noticeItem = {
        noticeNo: this.noticeItem.noticeNo,
        title: this.notice.title,
        content: this.notice.content,
        hits: this.notice.hits,
      };
      const { status } = await this.updateNotice(noticeItem);
      if (status === 200) {
        alert("수정되었습니다.");
        this.$router.push({ name: "noticeList" });
      } else {
        alert("수정 실패 다시 시도해 주세요.");
      }
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
};
</script>

<style></style>
