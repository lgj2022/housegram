<template>
  <b-row
    class="house_result"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <i class="fas fa-house-user"></i>
      <!-- <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img> -->
    </b-col>
    <b-col cols="10" class="align-self-center">
      {{ house.apartmentname }}
    </b-col>
    <!-- [{{ house.aptCode }}] -->
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(["detailHouse"]),
    selectHouse() {
      console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: rgb(233, 232, 232);
}

.house_result {
  cursor: pointer;
  padding: 8px 20px;
}

.fa-house-user {
  font-size: 30px;
  font-weight: 900;
  border-radius: 100%;
}
</style>
