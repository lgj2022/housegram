<template>
  <div class="container">
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>오늘의 뉴스</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col v-if="newsList.length">
          <b-table-simple hover responsive>
            <b-thead head-variant="dark">
              <b-tr>
                <b-th>뉴스 번호</b-th>
                <b-th class="news_img">이미지</b-th>
                <b-th>뉴스 제목</b-th>
                <b-th>언론사</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <!-- 하위 component인 ListRow에 데이터 전달(props) -->
              <news-list-item
                v-for="news in newsList"
                :key="news.newsNo"
                v-bind="news"
              />
            </tbody>
          </b-table-simple>
        </b-col>
        <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
      </b-row>
    </b-container>
  </div>
</template>

<script>
import NewsListItem from "@/components/news/NewsListItem.vue";
import { mapActions, mapState } from "vuex";
const newsStore = "newsStore";
export default {
  components: {
    NewsListItem,
  },
  data() {
    return {};
  },
  async created() {
    await this.fetch_newsList();
    console.log("check");
    console.log(this.newsList);
    console.log(this.newsList.length);
  },
  methods: {
    ...mapActions(newsStore, ["fetch_newsList"]),
    moveHome() {
      this.$router.push({ name: "home" });
    },
  },
  computed: {
    ...mapState(newsStore, ["newsList"]),
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
.news_img {
  text-align: center !important;
}
</style>
