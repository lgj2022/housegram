<template>
  <div>
    <router-link
      :to="{ name: 'articleDetail', params: { articleNo: articleNo } }"
      ><img class="articleImg" :src="fileInfos[0].imgpath"
    /></router-link>
  </div>
</template>

<script>
// import moment from "moment";

export default {
  name: "ArticleListItem",
  props: {
    articleNo: Number,
    content: String,
    fileInfos: Array,
  },
  filters: {
    // dateFormat(regtime) {
    //   return moment(new Date(regtime)).format("YY.MM.DD");
    // },
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
</style>
