<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글수정</h3></b-alert>
      </b-col>
    </b-row>
    <article-input-item :tag-infos="articleItem.tagInfos" />
  </b-container>
</template>

<script>
import ArticleInputItem from "@/components/community/item/ArticleInputItem.vue";
import { mapState } from "vuex";
const articleStore = "articleStore";
export default {
  name: "ArticleModify",
  components: {
    ArticleInputItem,
  },
  computed: {
    ...mapState(articleStore, ["articleItem"]),
  },
};
</script>

<style></style>
