<template>
  <div style="text-align: center; max-width: 500px; margin: 4rem auto">
    <div class="mb-4">
      <h4>프로필 수정</h4>
    </div>
    <form method="post" enctype="multipart/form-data">
      <div class="image_area">
        <div class="update_img_box">
          <!-- v-if="userInfo.profile_img" -->
          <span
            v-show="!userInfo.profile_img"
            style="font-size: 12rem"
            class="defaultUserProfile active"
          >
            <i class="fas fa-user-circle"></i>
          </span>
          <img
            v-show="userInfo.profile_img"
            id="my_img"
            :src="this.userInfo.profile_img"
          />
          <label class="update_img_btn" for="input_myimg">
            <span class="material-icons update_image">
              <i class="fas fa-sync-alt"></i>
            </span>
          </label>
          <input
            src=""
            type="file"
            name="image"
            accept="image/*"
            class="form-control"
            id="input_myimg"
            ref="uploadimage"
            @change="upload_img"
          />
        </div>
      </div>
      <button
        type="submit"
        @click="upload"
        class="btn btn-dark rounded-pill col-3 mt-3"
      >
        프로필 수정
      </button>

      <button
        type="submit"
        @click="moveDetailpage"
        class="btn btn-dark rounded-pill col-3 mt-3"
      >
        돌아가기
      </button>
    </form>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import { profileUpload } from "@/api/Account.js";
const accountStore = "accountStore";
export default {
  data() {
    return {
      image: "",
    };
  },
  computed: {
    ...mapState(accountStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(accountStore, ["getUserInfo"]),
    onInputImage() {
      this.image = this.$refs.uploadimage.files[0];
      console.log("oninputImage");
      console.log(this.image);
    },
    async upload(event) {
      event.preventDefault();
      this.onInputImage();
      console.log("업로드 시작");
      const response = await profileUpload(this.userInfo.id, this.image);
      console.log(response);
      let token = sessionStorage.getItem("access-token");
      const response1 = await this.getUserInfo(token);
      console.log(response1);
    },

    async moveDetailpage(event) {
      event.preventDefault();
      this.$router.push({ name: "AccountDetail" });
    },

    upload_img(event) {
      const input = event.target;
      console.log(input.files && input.files[0]);
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
          const my_img = document.querySelector("#my_img");
          const defaultImg = document.querySelector(".defaultUserProfile");
          my_img.classList.add("active");
          defaultImg.classList.remove("active");

          my_img.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      }
    },
    // functionss () { //document ready call
    //     $("#id_image").change(function () {
    //     upload_img(this);
    // });
    // });
  },
};
</script>
<style scoped>
.test {
  display: block;
}
#input_myimg {
  display: none;
}

.image_area {
  margin-bottom: 2rem;
}

.defaultUserProfile.active {
  display: block;
  position: relative;
  font-size: 12rem;
  top: -50px;
}

.defaultUserProfile {
  display: none;
}
.update_image {
  cursor: pointer;
  font-size: 2.5rem;
}

.nickname_area,
.message_area {
  margin: 0 auto 1rem auto;
  width: 60%;
}

.input_nickname,
.input_message {
  text-align: center;
}
.update_img_box {
  position: relative;
  margin: 0 auto;
  height: 12rem;
  width: 12rem;
}

#my_img {
  height: 12rem;
  width: 12rem;
  border-radius: 20rem;
  margin-bottom: 2rem;
  object-fit: cover;
}

#my_img.active {
  display: block !important;
}

.update_img_btn {
  position: absolute;
  z-index: 1;
  top: 90%;
  right: 0;
}
</style>
