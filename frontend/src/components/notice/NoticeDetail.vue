<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listNotice">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="checkUserAuthority">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyNotice"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteNotice"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${this.noticeItem.noticeNo}.
          ${this.noticeItem.title} [${this.noticeItem.hits}]</h3><div><h6>${this.noticeItem.author}</div><div>${this.noticeItem.createdAt}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
const noticeStore = "noticeStore";
const accountStore = "accountStore";
export default {
  data() {
    return {};
  },
  async created() {
    const noticeNo = this.$route.params.noticeNo;
    await this.fetch_noticeItem(noticeNo);
  },
  computed: {
    ...mapState(noticeStore, ["noticeItem"]),
    ...mapGetters(accountStore, ["checkUserAuthority"]),
    message() {
      if (this.noticeItem.content)
        return this.noticeItem.content.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    ...mapActions(noticeStore, ["fetch_noticeItem", "deleteNoticeItem"]),
    listNotice() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyNotice() {
      this.$router.replace({
        name: "noticeModify",
        params: { noticeNo: this.noticeItem.noticeNo },
      });
      // this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    async deleteNotice() {
      if (confirm("정말로 삭제?")) {
        await this.deleteNoticeItem(this.noticeItem.noticeNo);
        this.$router.push({ name: "noticeList" });
      }
    },
  },
};
</script>

<style></style>
