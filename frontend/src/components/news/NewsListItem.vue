<template>
  <b-tr>
    <b-td>{{ newsNo }}</b-td>
    <b-td><img :src="img" /></b-td>
    <b-th class="text-left">
      <a :href="link" target="_blank">{{ title }}</a>
    </b-th>
    <!-- <b-td>{{ link }}</b-td> -->
    <b-td>{{ publisher }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";
export default {
  name: "NoticeListItem",
  props: {
    newsNo: String,
    title: String,
    link: String,
    publisher: String,
    createdAt: String,
    img: String,
  },
  computed: {},
};
</script>
