<template>
  <b-container
    v-if="houses && houses.length != 0"
    class="bv-example-row house_list"
  >
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row house_list">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState } from "vuex";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style scoped>
.house_list {
  margin-top: 40px;
}
</style>
