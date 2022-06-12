<template>
  <div style="text-align: center; margin: 1rem 0">
    <template>
      <a
        v-if="pageObj.currentPage > 1"
        class="btn clickable-btn"
        @click.prevent="changePage(pageObj.currentPage - 1)"
      >
        <i class="fas fa-chevron-left"></i>
      </a>
      <a v-else class="btn">
        <i class="fas fa-chevron-left"></i>
      </a>
    </template>
    <template v-for="index in (pageObj.startPage, pageObj.currentEndPage)">
      <a
        href="#"
        v-if="index === pageObj.currentPage"
        class="btn active-btn active"
        :key="index"
        @click.prevent="changePage(index)"
      >
        {{ index }}
      </a>
      <a
        v-else-if="index >= pageObj.startPage"
        class="btn clickable-btn"
        :key="index"
        @click.prevent="changePage(index)"
      >
        {{ index }}
      </a>
    </template>
    <template>
      <a
        v-if="pageObj.currentPage < pageObj.currentEndPage"
        class="btn clickable-btn"
        @click.prevent="changePage(pageObj.currentPage + 1)"
        ><i class="fas fa-chevron-right"></i
      ></a>
      <a
        v-else-if="pageObj.currentEndPage < pageObj.lastPage"
        class="btn clickable-btn"
        @click="changePage(pageObj.currentEndPage + 1)"
        ><i class="fas fa-chevron-right"></i
      ></a>
      <a v-else class="btn">
        <i class="fas fa-chevron-right"></i>
      </a>
    </template>
    <!-- <a class="btn clickable-btn">
      <i class="fas fa-chevron-right"></i>
    </a> -->
  </div>
</template>

<script>
import { mapActions } from "vuex";
const noticeStore = "noticeStore";
const articleStore = "articleStore";
export default {
  data() {
    return {
      prevAble: false,
      nextAble: false,
    };
  },
  props: {
    keyType: String,
    keyword: String,
    pageObj: Object,
    linkType: String,
  },
  created() {
    // this.pageObj = this.$store.state.pageObj;
    // console.log(this.pageObj);
  },
  methods: {
    ...mapActions(noticeStore, ["fetch_noticeList"]),
    ...mapActions(articleStore, ["fetch_articleList"]),

    async changePage(page) {
      if (this.linkType === "notice") {
        await this.fetch_noticeList({
          type: this.keyType,
          keyword: this.keyword,
          page: page,
        });
      } else {
        await this.fetch_articleList({
          keyType: this.keyType,
          keyword: this.keyword,
          page: page,
        });
      }
    },
  },
};
</script>

<style scoped>
a {
  border: 2px solid transparent;
  font-weight: 2px;
  border-radius: 1.5rem;
  padding: 4px 12px;
  margin: 0px 5px;
}
a.active {
  border: 2px solid cornflowerblue;
  padding: 4px 12px;
  cursor: default;
  pointer-events: none;
  color: cornflowerblue;
}

.clickable-btn:hover {
  border: 2px solid cornflowerblue;
  padding: 4px 12px;

  color: cornflowerblue;
}
</style>
