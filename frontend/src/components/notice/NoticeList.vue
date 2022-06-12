<template>
  <div class="container">
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>글목록</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-form inline>
            <b-form-select
              v-model="keyType"
              id="inline-form-custom-select-pref"
              class="mb-2 mr-sm-2 mb-sm-0"
              :options="[
                { text: '선택하기', value: null },
                { text: '제목', value: 'title' },
                { text: '내용', value: 'content' },
              ]"
            >
            </b-form-select>
            <label class="sr-only" for="inline-form-input-name">Name</label>
            <b-form-input
              v-on:keydown.enter.prevent="onSearch()"
              id="inline-form-input-name"
              class="mb-2 mr-sm-2 mb-sm-0 notice_search_input"
              v-model="keyword"
            ></b-form-input>
            <b-button variant="outline-primary" @click.prevent="onSearch()"
              >검색</b-button
            >
          </b-form>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <b-button
            variant="outline-primary"
            @click="moveRegist()"
            v-if="checkUserAuthority"
            >글쓰기</b-button
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col v-if="noticeList.length">
          <b-table-simple hover responsive>
            <b-thead head-variant="dark">
              <b-tr>
                <b-th>글번호</b-th>
                <b-th>제목</b-th>
                <b-th>조회수</b-th>
                <b-th>작성자</b-th>
                <b-th>작성일</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <!-- 하위 component인 ListRow에 데이터 전달(props) -->
              <notice-list-item
                v-for="notice in noticeList"
                :key="notice.noticeNo"
                v-bind="notice"
              />
            </tbody>
          </b-table-simple>
        </b-col>
        <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
      </b-row>
    </b-container>
    <img id="profileImg" />
    <page-bar
      linkType="notice"
      :pageObj="pageLink"
      :keyType="keyType"
      :keyword="keyword"
    ></page-bar>

    <!-- <div class="notice-title">
      <p>공지사항</p>
    </div>
    <section class="notice">
      <article class="notice_list">
        <div v-if="notices.length">
          <table id="notice-list">
            <colgroup>
              <col style="width: 5%" />
              <col style="width: 45%" />
              <col style="width: 25%" />
              <col style="width: 25%" />
            </colgroup>
            <thead>
              <tr>
                <th>글번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
              </tr>
            </thead>
            <tbody>
              <b-table striped hover :notices="notices"></b-table>
              <notice-list-item
                v-for="notice in notices"
                :key="notice.noticeId"
                :notice="notice"
              ></notice-list-item>
            </tbody>
          </table>
        </div>
        <div v-else class="text-center">등록된 공지사항이 없습니다.</div>
      </article>
    </section> -->
  </div>
</template>

<script>
import NoticeListItem from "@/components/notice/NoticeListItem.vue";
import PageBar from "@/components/base/PageBar.vue";
import { mapActions, mapGetters, mapState } from "vuex";
// import axios from "axios";
const noticeStore = "noticeStore";
const accountStore = "accountStore";
export default {
  components: {
    NoticeListItem,
    PageBar,
  },
  data() {
    return {
      keyType: null,
      keyword: "",
      pageObj: {},
      image: "",
    };
  },
  async created() {
    await this.fetch_noticeList({
      keyType: this.keyType,
      keyword: this.keyword,
      page: 1,
    });
    this.pageObj = this.pageLink;
  },
  methods: {
    ...mapActions(noticeStore, ["fetch_noticeList", "searchNoticeList"]),
    moveRegist() {
      this.$router.push({ name: "noticeRegist" });
    },
    async onSearch() {
      if (this.type === null || this.keyword === "") {
        alert("검색 조건 및 검색어를 확인해주세요");
        return;
      } else {
        // await this.searchNoticeList({ type: this.type, keyword: this.keyword });
        await this.fetch_noticeList({
          type: this.keyType,
          keyword: this.keyword,
          page: 1,
        });
      }
    },
  },
  // watch: {
  //   "$route.query": async function () {
  //     await this.fetch_noticeList(this.$route.query.pg);
  //   },
  // },
  computed: {
    ...mapGetters(accountStore, ["checkUserAuthority", "checkUserInfo"]),
    ...mapState(noticeStore, ["noticeList", "pageLink"]),
  },
};
</script>

<style scoped>
#notice-list thead tr {
  height: 60px;
  background: cornflowerblue;
}

#notice-list thead th {
  font-size: 18px;
  line-height: 1.2;
}

.notice_search_input {
  width: 30% !important;
}
</style>
