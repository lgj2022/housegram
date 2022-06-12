<template>
  <div class="accordionWrap">
    <ul class="gallery">
      <li>
        <div class="content">
          <h3>원하는 아파트를 검색해보세요</h3>
          <router-link :to="{ name: 'house' }">실거래가 조회</router-link>
        </div>
      </li>
      <li>
        <div class="content">
          <h3>여러 사람들과 정보를 공유해요</h3>
          <router-link :to="{ name: 'article' }">커뮤니티</router-link>
        </div>
      </li>
      <li>
        <div class="content">
          <h3>사이트에 대한 주요 소식을 접해보세요</h3>
          <router-link :to="{ name: 'notice' }">공지사항</router-link>
        </div>
      </li>
      <li>
        <div class="content">
          <h3>최신 부동산에 대한 뉴스를 찾아보세요</h3>
          <router-link to="/">오늘의 뉴스</router-link>
        </div>
      </li>
      <!-- <li><img class="menu-img" src="@/assets/menu_cover1.png" /></li>
      <li><img class="menu-img" src="@/assets/menu_cover2.jpg" /></li>
      <li><img class="menu-img" src="@/assets/menu_cover3.jpg" /></li> -->
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
a {
  text-decoration: none !important;
}

.gallery {
  list-style: none !important;
  padding: 0 !important;
  margin: 0 !important;
  display: flex;
  height: 50vh;
}

.gallery li {
  /* border-right: 2px solid #000; */
  flex: 1;
  transition: 0.5s;
  filter: grayscale(0.4);
  position: relative;
  overflow: hidden;
}

.gallery li:last-child {
  border-right: none;
}

.gallery li:nth-child(1) {
  background: url("@/assets/menu_cover7.jpg") no-repeat center center;
}

.gallery li:nth-child(2) {
  background: url("@/assets/menu_cover4.jpg") no-repeat center center;
}

.gallery li:nth-child(3) {
  background: url("@/assets/menu_cover6.jpg") no-repeat center center;
}

.gallery li:nth-child(4) {
  background: url("@/assets/menu_cover5.jpg") no-repeat center center;
}

.gallery:hover li {
  text-align: center;
  flex: 1;
}

.gallery li:hover {
  flex: 3;
  filter: grayscale(0);
}

.content {
  text-align: center;
  transition: 0.5s;
  padding: 20px;
  opacity: 0;
  box-sizing: border-box;
  margin-top: 100px;
}

.gallery li:hover .content {
  transition-delay: 0.5s;
  opacity: 1;
}

.gallery li:nth-child(1) div h3 {
  color: rgb(66, 65, 65);
}
.gallery li:nth-child(2) div h3 {
  color: white;
}
</style>
