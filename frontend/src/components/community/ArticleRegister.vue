<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
    <!-- <article-input-item type="regist" /> -->
    <article-regist-item></article-regist-item>
  </b-container>
</template>

<script>
// import ArticleInputItem from "@/components/community/item/ArticleInputItem.vue";
import ArticleRegistItem from "@/components/community/item/ArticleRegistItem.vue";

export default {
  components: {
    ArticleRegistItem,
  },
};
</script>

<style></style>
