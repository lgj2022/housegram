<template>
  <b-tr>
    <b-td>{{ noticeNo }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'noticeDetail', params: { noticeNo: noticeNo } }"
        >{{ title }}</router-link
      >
    </b-th>
    <b-td>{{ hits }}</b-td>
    <b-td>{{ author }}</b-td>
    <b-td>{{ createdAt }}</b-td>

    <!-- <b-td>{{ regtime | dateFormat }}</b-td> -->
  </b-tr>
</template>

<script>
// import moment from "moment";
export default {
  name: "NoticeListItem",
  props: {
    noticeNo: Number,
    author: String,
    title: String,
    hits: String,
    createdAt: String,
  },
  computed: {
    // ...mapState(noticeStore, ["noticeList", "pageLink"]),
  },
  //   filters: {
  //     formatDate(regtime) {
  //       return moment(new Date(regtime)).format("YY.MM.DD");
  //     },
  //   },
};
</script>

<style></style>
