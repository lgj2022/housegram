<template>
  <div style="display: inline-block" class="like_container">
    <form class="like_form" action="" method="POST">
      <template>
        <span
          v-show="isLiked"
          class="like material-icons"
          name=""
          value="Like"
          style="color: red"
          @click="likedChange"
        >
          <i class="fas fa-heart"></i>
        </span>
        <span
          v-show="!isLiked"
          class="like material-icons"
          name=""
          value="Like"
          @click="likedChange"
        >
          <i class="far fa-heart"></i>
        </span>
      </template>
    </form>
    <p id="" class="article_count">{{ like_cnt }}개</p>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { chLike, createLike, deleteLike } from "@/api/Article.js";
const articleStore = "articleStore";
const accountStore = "accountStore";
export default {
  data() {
    return {
      isLiked: true,
      like_cnt: null,
    };
  },
  computed: {
    ...mapState(accountStore, ["userInfo"]),
    ...mapState(articleStore, ["articleItem", "pageLink", "recomendTagList"]),
  },
  async created() {
    const articleNo = this.$route.params.articleNo;
    const { data } = await chLike(articleNo, this.userInfo.id);
    console.log(data);
    this.like_cnt = data.likecnt;
    console.log(data.likecnt);
    if (data.res === "unchecked") {
      this.isLiked = false;
    } else {
      this.isLiked = true;
    }
  },
  methods: {
    ...mapActions(articleStore, [
      "fetch_articleList",
      "searchArticleList",
      "checkArticleLike",
    ]),
    async likedChange() {
      this.isLiked = !this.isLiked;
      const articleNo = this.$route.params.articleNo;
      if (this.isLiked === true) {
        console.log("true 였을때");
        const { data } = await createLike(articleNo, this.userInfo.id);
        console.log(data);
        this.like_cnt = data.likecnt;
      } else {
        console.log("false 였을때");
        const { data } = await deleteLike(articleNo, this.userInfo.id);
        console.log(data);
        this.like_cnt = data.likecnt;
      }
    },
  },
};
</script>

<style scoped>
.like_container {
  position: absolute;
  top: -5px;
  right: 20px;
}
.fa-heart {
  font-size: 20px;
  cursor: pointer;
}
</style>
