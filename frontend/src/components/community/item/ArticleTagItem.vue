<template>
  <div>
    <button @click="searchEvent" class="tagItem_btn">#{{ recomend }}</button>
  </div>
</template>

<script>
// import moment from "moment";
import { mapActions } from "vuex";
const articleStore = "articleStore";
export default {
  name: "ArticleListItem",
  props: {
    recomend: String,
  },
  created() {
    console.log(this.recomend);
  },
  mounted() {
    const tagItemBtns = document.querySelectorAll(".tagItem_btn");
    console.log(tagItemBtns);
    for (let i = 0; i < tagItemBtns.length; i++) {
      tagItemBtns[i].style.background =
        "#" + ((Math.random() * 0xffffff) << 0).toString(16);
    }
  },
  methods: {
    ...mapActions(articleStore, ["fetch_articleList", "searchArticleList"]),
    async searchEvent(event) {
      event.preventDefault();
      await this.fetch_articleList({
        keyType: "hash",
        keyword: this.recomend,
        page: 1,
      });
    },
  },
};
</script>

<style scoped>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.container {
  padding: 0;
  margin: 0 auto;
}
.container a {
  max-width: 200px;
}
.container div {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 1rem;
}
.container img {
  width: 100%;
  border-radius: 1rem;
}
.tagItem_btn {
  color: white;
  margin-right: 10px;
  outline: none;
  padding: 4px 10px;
  border-radius: 10px;
  border: 1px solid transparent;
}
</style>
