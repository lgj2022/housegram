<template>
  <div class="cover">
    <div class="cover-title">
      <p>Happy House</p>
    </div>
    <!-- <img class="cover-img" src="@/assets/happyhouse_cover.jpg" /> -->
    <img class="cover-img" src="@/assets/house1.jpg" />
    <!-- <img class="cover-img" src="@/assets/house2.jpg" /> -->
    <!-- <img class="cover-img" src="@/assets/house2.jpg" /> -->
    <accordion-menu></accordion-menu>
  </div>
</template>

<script>
import AccordionMenu from "@/components/base/AccordionMenu.vue";
export default {
  components: {
    AccordionMenu,
  },
};
</script>

<style scoped>
/* cover part css */

.cover {
  position: relative;
}

p {
  animation-name: titleAnimation;
  animation-duration: 0.7s;
}

@keyframes titleAnimation {
  from {
    display: none;
  }

  to {
    display: block;
  }
}

.cover-title {
  color: #949acd;
  opacity: 1;
  position: absolute;
  top: 70px;
  font-family: "Lobster", cursive;
  /* font-family: 'Cookie', cursive; */
  font-size: 100px;
  width: 100%;
  animation-name: titleAnimation;
  animation-duration: 1.5s;
  animation-delay: 0.7s;
  text-align: center;
}
.cover-img {
  width: 100%;
  height: calc(100vw / 2);
  max-height: 512px;
  animation-name: coverImgShow;
  animation-duration: 0.7s;
  transition: all 1s;
}

@keyframes coverImgShow {
  from {
    transform: scaleX(0);
  }
  to {
    transform: scaleX(1);
  }
}

@keyframes titleAnimation {
  from {
    color: white;
    opacity: 0;
    top: 200px;
  }

  to {
    opacity: 1;
    top: 70px;
    color: #949acd;
  }
}

@media screen and (max-width: 660px) {
  .cover-title {
    font-size: 50px;
  }
}
</style>
