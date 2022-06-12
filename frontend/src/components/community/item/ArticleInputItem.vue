<template>
  <div class="create_container_background">
    <div class="create_container">
      <form class="form_area" enctype="multipart/form-data" @submit="onSubmit">
        <div class="main_area">
          <div class="image_area">
            <div class="upload_img_box change_box active">
              <img class="my_img" :src="imgSrc" />
              <span class="material-icons delete_image" @click="delete_img"
                ><i class="fas fa-trash"></i>
              </span>
            </div>
            <label class="input_image_btn change_box" for="input_img">
              <div class="upload_contour">
                <div>
                  <span class="material-icons" style="font-size: 2.5rem">
                    <i class="fas fa-image"></i>
                  </span>
                  <p>클릭하여 업로드</p>
                </div>
              </div>
            </label>
            <input
              type="file"
              ref="files"
              name="image"
              accept="image/*"
              id="input_img"
              value=""
              @change="upload_img"
              oninvalid="alert('사진을첨부해주세요!')"
              multiple
            />
          </div>
          <div class="content_area">
            <div class="hashtag_area">
              <hash-tag type="modify"></hash-tag>
            </div>
            <div style="padding-right: 3rem">
              <input
                type="text"
                maxlength="200"
                class="input_title"
                placeholder="제목 추가"
                v-model="article.title"
                required
                id="id_title"
              />
            </div>
            <div style="float: left; margin: 0 0 0 1rem">
              <template>
                <img
                  v-if="articleItem.user_profile_img"
                  :src="articleItem.user_profile_img"
                  alt=""
                  style="
                    `height: 2.5rem;
                    width: 2.5rem;
                    border-radius: 20rem;
                    object-fit: cover;
                  "
                />
                <i
                  v-else
                  class="fas fa-user-circle"
                  style="font-size: 2rem"
                ></i>
              </template>

              <template>
                <span class="writer">
                  {{ articleItem.username }}
                </span>
              </template>
            </div>
            <div style="padding-right: 3rem">
              <textarea
                name="content"
                cols="40"
                rows="1"
                class="input_content"
                placeholder="사진에 대해 설명해 보세요."
                v-model="article.content"
                columns="40"
                required
                id="id_content"
              ></textarea>
            </div>
          </div>
        </div>
        <input
          type="submit"
          class="btn btn-outline-dark rounded-pill col-3 mt-3 mb-3"
          value="수정"
        />
      </form>
    </div>
  </div>
</template>

<script>
import HashTag from "@/components/community/HashTag.vue";
import bus from "@/utils/bus.js";
import { mapState, mapActions } from "vuex";
// import axios from "axios";
const accountStore = "accountStore";
const articleStore = "articleStore";

export default {
  name: "ArticleInputItem",
  components: {
    HashTag,
  },
  props: {
    type: { type: String },
  },
  data() {
    return {
      article: {
        articleNo: 0,
        userid: "",
        title: "",
        content: "",
      },
      fileInfos: [],
      isUserid: false,
      tags: [],
      imgSrc: "",
    };
  },
  computed: {
    ...mapState(articleStore, ["articleItem"]),
    ...mapState(accountStore, ["userInfo"]),
  },

  async created() {
    this.imgSrc = this.articleItem.fileInfos[0].imgpath;
    this.article.articleNo = this.articleItem.articleNo;
    this.article.title = this.articleItem.title;
    this.article.content = this.articleItem.content;
    this.article;
    await bus.$on("response:HashTags", async (response) => {
      await response.forEach((element) => {
        console.log(element.value, "엘리멘트 값");
        this.tags.push(element.value);
      });
    });
  },
  methods: {
    ...mapActions(articleStore, ["updateArticle"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.title &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "articleList" });
    },
    async modifyArticle() {
      await bus.$emit("request:HashTags");
      let formData = new FormData();
      const imageElement = document.querySelector("#input_img");
      if (this.tags.length === 0) {
        formData.append("tag", "");
      } else {
        for (const value of this.tags) {
          console.log(value);
          formData.append("tag", value);
        }
      }
      if (imageElement.files.length === 0) {
        formData.append("upfile", null);
      } else {
        for (let i = 0; i < imageElement.files.length; i++) {
          formData.append("upfile", imageElement.files[i]);
        }
      }

      formData.append("userid", this.userInfo.id);
      formData.append("title", this.article.title);
      formData.append("content", this.article.content);
      let params = {
        articleNo: this.articleItem.articleNo,
        formData: formData,
      };
      this.updateArticle(params);
      alert("게시글 수정 성공");
      this.moveList();
    },
    moveList() {
      this.$router.push({ name: "articleList" });
    },
    upload_img(event) {
      const input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
          const my_img = document.querySelector(".my_img");
          my_img.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      }
      const change_box = document.querySelectorAll(".change_box");
      change_box[0].classList.toggle("active");
      change_box[1].classList.toggle("active");
    },
    delete_img() {
      const imageElement = document.querySelector("#input_img");
      imageElement.value = "";
      const my_img = document.querySelector(".my_img");
      my_img.src = "";
      const change_box = document.querySelectorAll(".change_box");
      change_box[0].classList.toggle("active");
      change_box[1].classList.toggle("active");
    },
  },
};
</script>

<style scoped>
.create_container_background {
  text-align: center;
  background-color: #f2f2f2;
  padding: 1rem 3rem;
}

.create_container {
  border-radius: 1rem;
  max-width: 750px;
  box-shadow: 0 0 4px #ccc;
  margin: 2rem auto;
  background-color: white;
  min-height: 100px;
  overflow: hidden;
}
.input_image_btn {
  width: 100%;
  height: 100%;
  background-color: #f2f2f2;
  cursor: pointer;
  border-radius: 1rem;
  padding: 1rem 1rem;
}

.upload_img_box {
  width: 100%;
  height: 100%;
  border-radius: 1rem;
  display: none;
  position: relative;
}
.main_area {
  width: 100%;
  height: 25rem;
}

.image_area {
  width: 50%;
  height: 100%;
  float: left;
  margin: 2rem 0 1rem 0;
  padding: 0 2rem;
}

#input_img {
  display: none;
}

.my_img {
  max-width: 100%;
  max-height: 100%;
  border-radius: 1rem;
}

.delete_image {
  border-radius: 100%;
  padding: 0.5rem;
  font-size: 18px;
  box-shadow: 0 0 4px #ccc;
  z-index: 1;
  left: 0;
  position: absolute;
  background-color: white;
  cursor: pointer;
  width: 40px;
}

.delete_image:hover {
  background-color: #f2f2f2;
}

.upload_contour {
  width: 100%;
  height: 100%;
  border-radius: 1rem;
  border: 3px dashed #a4a4a4;
  justify-content: center;
  align-items: center;
  display: flex;
}

.content_area {
  width: 50%;
  height: 100%;
  float: right;
}

.project_area {
  text-align: right;
  width: 100%;
  margin: 2rem 0;
}

.input_project {
  margin-right: 1.5rem;
  font-size: 1rem;
  border-radius: 0.5rem;
  padding: 0.5rem;
  box-shadow: 0 0 4px #ccc;
}

.input_project:focus {
  outline: none;
}

.input_title {
  font-size: 2rem;
  font-weight: bold;
  margin: 1rem 0 2rem 1rem;
  width: 100%;
}

.input_content {
  width: 100%;
  margin: 1rem 0 2rem 1rem;
  resize: none;
}

.input_title:focus,
.input_content:focus {
  border-width: 0 0 2px 0px;
  border-color: blue;
}

.input_title,
.input_content {
  outline: 0;
  border-width: 0 0 1px 0;
}
.change_box {
  display: none;
}
.change_box.active {
  display: block;
}

.hashtag_area {
  margin-top: 2rem;
  padding: 8px 30px 8px 13px;
}
</style>
