<template>
  <div>
    <b-button v-b-toggle.sidebar-right class="sideBtn">
      <i class="fas fa-bars" id="hambuger"></i>
    </b-button>
    <b-sidebar id="sidebar-right" right shadow>
      <b-img
        class="profile_img"
        src="https://picsum.photos/200/200/?image=54"
        fluid
        thumbnail
        center
      ></b-img>
      <div class="px-3 py-2">
        <p>
          Cras mattis consectetur purus sit amet fermentum. Cras justo odio,
          dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac
          consectetur ac, vestibulum at eros.
        </p>
      </div>
    </b-sidebar>
  </div>
</template>
<style scoped>
.btn-secondary.sideBtn {
  color: transparent;
  background-color: transparent;
  border: 0px;
}

.btn-secondary.sideBtn:hover {
  background-color: transparent;
  border: 0px;
}
#hambuger {
  color: gray;
  font-size: 22px;
  background: transparent;
}

#hambuger:hover {
  color: cornflowerblue;
}
.b-sidebar .b-sidebar-header {
  margin-top: 30px;
}

.close.text-dark {
  font-size: 2rem;
  margin-top: 10px;
}

.profile_img {
  border-radius: 100%;
  margin-top: 80px;
}
</style>
