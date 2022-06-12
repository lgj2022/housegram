<template>
  <div class="article_container">
    <div class="article_image_container">
      <b-carousel
        id="carousel-1"
        :interval="0"
        controls
        indicators
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333"
      >
        <b-carousel-slide v-for="(file, index) in fileInfos" :key="index">
          <template #img>
            <img class="article_image" :src="file.imgpath" />
          </template>
        </b-carousel-slide>
      </b-carousel>
    </div>
    <div class="article_content_container">
      <div class="article_content">
        <div style="margin: 1rem; height: 10%">
          <ul v-if="articleItem.userid === userInfo.id">
            <li>
              <router-link
                :to="{
                  name: 'articleModify',
                  params: { articleno: articleItem.articleNo },
                }"
                class="updateBtn"
                >수정</router-link
              >
            </li>
            <li>
              <a href="#" class="deleteBtn" @click.prevent="deleteArticle">
                삭제
              </a>
            </li>
          </ul>
          <article-like :articleNo="articleItem.articleNo"></article-like>
        </div>
        <div class="hashTag_area">
          <div v-for="(tag, index) in articleItem.tagInfos" :key="index">
            #{{ tag.tagname }}
          </div>
        </div>
        <div style="margin-left: 2rem">
          <h4 class="article_title" style="margin-bottom: 0.8rem">
            {{ articleItem.title }}
          </h4>
          <div class="content" style="text-align: left; margin-bottom: 0.8rem">
            {{ articleItem.content }}
          </div>
          <div style="margin-bottom: 2rem; position: relative">
            <!-- {% if target_article.writer.profile %} -->
            <template>
              <img
                v-if="articleItem.user_profile_img"
                :src="articleItem.user_profile_img"
                alt=""
                class="writer_img"
              />
              <i v-else class="fas fa-user-circle" style="font-size: 2rem"></i>
            </template>

            <span
              class="writer"
              :class="{ existImg: articleItem.user_profile_img }"
              style="margin-bottom: 0.8rem"
            >
              {{ articleItem.username }}
            </span>
          </div>
        </div>
        <div style="margin: 0 0 2rem 2rem; width: 100%">
          <h3 style="float: left">댓글</h3>

          <button class="btn material-icons comment_btn more">
            <i class="fas fa-chevron-circle-down"></i>
          </button>
          <button class="btn material-icons comment_btn" style="display: none">
            <i class="fas fa-chevron-circle-down"></i>
          </button>
        </div>
        <div class="comments">
          <comment-list></comment-list>
        </div>
        <div class="comment_write">
          <comment-write></comment-write>
        </div>
        <div class="backtoList">
          <router-link :to="{ name: 'articleList' }">목록으로</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import ArticleLike from "@/components/community/item/ArticleLike.vue";
import CommentList from "@/components/comment/CommentList.vue";
import CommentWrite from "@/components/comment/CommentWrite.vue";
import { mapActions, mapState } from "vuex";
import ArticleLike from "./item/ArticleLike.vue";
const articleStore = "articleStore";
const accountStore = "accountStore";
// import moment from "moment";
// import http from "@/api/http";

export default {
  components: {
    CommentList,
    CommentWrite,
    ArticleLike,
    // ArticleLike,
  },
  name: "ArticleDetail",
  data() {
    return {
      article: {},
      fileInfos: [],
    };
  },
  computed: {
    ...mapState(articleStore, ["articleItem"]),
    ...mapState(accountStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  async created() {
    const articleNo = this.$route.params.articleNo;
    await this.fetch_articleItem(articleNo);
    this.fileInfos = this.articleItem.fileInfos;
  },
  methods: {
    ...mapActions(articleStore, ["fetch_articleItem", "deleteArticleItem"]),
    // ...mapMutations(articleStore, ["CLEAR_ARTICLE_ITEM"]),
    listArticle() {
      this.$router.push({ name: "articleList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "articleModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    async deleteArticle() {
      if (confirm("정말로 삭제?")) {
        await this.deleteArticleItem(this.articleItem.articleNo);
        alert("삭제되었습니다.");
        this.$router.push({ name: "articleList" });
      }
    },
  },
};
</script>

<style scoped>
/* 여기는 추후 css 분리 */
.article_container {
  border-radius: 1rem;
  max-width: 1000px;
  box-shadow: 0 0 4px #ccc;
  margin: 1.5rem auto;
  min-height: 100px;
  overflow: hidden;
}

.article_image_container {
  width: 50%;
  height: 100%;
  margin: 0rem;
  float: left;
  border-radius: 1rem 0rem 0rem 1rem;
}

.article_image {
  width: 100%;
  height: 100%;
  margin: 0rem;
  border-radius: 1rem 0rem 0rem 1rem;
}

.article_content_container {
  width: 50%;
  margin: 0rem;
  float: right;
  border-radius: 0rem 1rem 1rem 0rem;
}

.article_content {
  position: relative !important;
  text-align: left !important;
  width: 100%;
  height: 100%;
  margin: 0rem;
  border-radius: 0rem 1rem 1rem 0rem;
}

.writer {
  margin-bottom: 0.8rem;
  position: absolute;
  top: 4px;
  margin-bottom: 0.8rem;
  left: 3rem;
}

.writer.existImg {
  top: 10px;
  left: 3.5rem;
}

.comments {
  margin-left: 2rem;
  width: 90%;
  /* display: none; */
}

.comment_btn.more {
  font-size: 20px;
  color: gray;
}
ul {
  list-style: none;
  display: flex;
}

.updateLink,
.deleteLink {
  font-size: 16px;
  padding: 8px 20px;
  outline: none;
  border: 1px solid transparent;
  color: #fff;
}

.updateBtn {
  text-decoration: none;
  border: 1px solid cornflowerblue;
  padding: 6px 10px;
  color: cornflowerblue;
  border-radius: 21px;
}

.updateBtn:hover {
  color: white;
  background-color: cornflowerblue;
}
.deleteBtn {
  margin-left: 10px;
  text-decoration: none;
  border: 1px solid red;
  padding: 6px 10px;
  color: red;
  border-radius: 21px;
}

.deleteBtn:hover {
  color: white;
  background-color: red;
}

.hashTag_area {
  margin-left: 2rem;
}

.backtoList {
  margin-left: 2rem;
  margin-bottom: 2rem;
}
.writer_img {
  height: 2.5rem;
  width: 2.5rem;
  border-radius: 20rem;
  object-fit: cover;
}

@media screen and (max-width: 1000px) {
  html {
    font-size: 14px;
  }

  .article_container {
    max-width: 500px;
    min-height: 100px;
    overflow: hidden;
  }

  .article_image_container {
    float: none;
    width: 100%;
    height: 50%;
  }

  .article_content_container {
    float: none;
    width: 100%;
    height: 50%;
  }

  .article_image {
    border-radius: 1rem 1rem 0rem 0rem;
    width: 100%;
    height: 100%;
  }

  .article_content {
    border-radius: 0rem 0rem 1rem 1rem;
    width: 100%;
    height: 100%;
  }
  .comment-modal-content {
    width: 500px;
    height: 250px;
  }
}

@media screen and (max-width: 600px) {
  html {
    font-size: 12px;
  }

  .article_container {
    max-width: 400px;
    min-height: 100px;
    overflow: hidden;
  }

  .article_image_container {
    float: none;
    width: 100%;
    height: 50%;
  }

  .article_content_container {
    float: none;
    width: 100%;
    height: 50%;
  }

  .article_image {
    border-radius: 1rem 1rem 0rem 0rem;
    width: 100%;
    height: 100%;
  }

  .article_content {
    border-radius: 0rem 0rem 1rem 1rem;
    width: 100%;
    height: 100%;
  }
  .comment-modal-content {
    width: 400px;
    height: 200px;
  }
}
</style>
