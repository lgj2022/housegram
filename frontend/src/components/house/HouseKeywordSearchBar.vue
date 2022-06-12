<template>
  <div class="keywordSearchWrap">
    <form method="POST" id="searchForm">
      <b-form-select
        class="keyType_select"
        v-model="keyType"
        :options="[
          { text: '아파트 검색', value: 'apt' },
          { text: '동 검색', value: 'dong' },
        ]"
      ></b-form-select>
      <b-form-input
        v-on:keydown.enter.prevent="onSearch()"
        id="inline-form-input-name"
        class="mb-2 mr-sm-2 mb-sm-0 house_search_input"
        type="text"
        v-model="keyword"
        autocomplete="off"
        v-on:keyup="search()"
      ></b-form-input>
      <!-- <template>
        <div id="recommend" v-if="recommendList.length">
          <div
            v-for="(recommend, index) in recommendList"
            :key="index"
            class="recomment_list"
          >
            {{ recommend }}
          </div>
        </div>
        <div id="recommend" v-else-if="keyword.length >= 2">
          <p class="search_result_none">일치하는 검색결과가 없습니다.</p>
        </div>
      </template> -->

      <!-- <div id="recommend" :class="{ invisible: invisible }">
        <div
          v-for="(recommend, index) in recommendList"
          :key="index"
          class="recomment_list"
        >
          {{ recommend }}
        </div>
      </div> -->
      <b-button variant="outline-primary" @click.prevent="onSearch()"
        >검색</b-button
      >
    </form>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions } from "vuex";
export default {
  data() {
    return {
      keyType: "apt",
      keyword: "",
      invisible: true,
      recommendList: [],
      options: [],
    };
  },
  methods: {
    ...mapActions(["searchHouseList"]),
    async onSearch() {
      if (this.keyword.length === 0) {
        alert("검색어를 입력해주세요.");
      } else {
        const response = await this.searchHouseList({
          type: this.keyType,
          keyword: this.keyword,
        });
        console.log(response);
      }
    },
    async search() {
      //   let recommend = document.querySelector("#recommend");
      console.log(this.keyword);
      if (this.keyword.length < 2) {
        this.recommendList = [];
      }
      if (this.keyword.length >= 2) {
        if (this.keyType === "apt") {
          const { data } = await axios.post(
            `http://localhost/happyhouse/api/map/search/${this.keyType}/${this.keyword}`
          );
          console.log(data);
          if (data.length != 0) {
            this.recommendList = [];
            data.map((item) => {
              this.recommendList.push(item.apartmentname);
            });
          } else {
            this.recommendList = [];
          }
        }
      }
    },
  },
};
</script>

<style scoped>
#searchForm {
  z-index: 2;
  display: flex;
  position: relative;
}

.searchForm.active {
  visibility: visible;
}

.searchForm input {
  box-shadow: 0px 0px 0px 2px #aea8a8;
  border: 0px;
  border-radius: 10px;
  padding: 0 10px;
  height: 100%;
  width: 100%;
}

.searchForm input:focus {
  outline: none;
}

.search {
  line-height: inherit;
  background-color: rgb(218, 218, 218);
  padding: 5px 10px;
  border-radius: 20px;
  cursor: pointer;
}

.searchForm input:focus {
  outline: none;
}

.searchWrap {
  width: 310px;
  position: absolute;
  right: 50px;
  position: absolute;
}

#keywordSearch {
  width: 100%;
  padding: 8px;
  font-size: 18px;
  border-radius: 10px;
  border: 1px solid gray;
  outline: none;
}

#recommend {
  position: absolute;
  width: 310px;
  top: 40px;
  margin-top: 10px;
  padding-top: 6px;
  border: 1px solid rgb(240, 240, 240);
  border-radius: 8px;
  background: rgb(255, 255, 255);
  overflow: hidden;
  opacity: 1;
  transition: opacity 0.2s ease-in-out 0s;
  box-shadow: rgb(0 0 0/ 8%) 5px 5px 10px;
  z-index: 8000;
  text-align: left;
  padding: 8px 20px;
}

#recommend.invisible {
  display: none;
}

.item {
  font-size: 20px;
  padding: 8px 20px;
}

.keywordSelect {
  background: #c7c6c6;
}

.emphasize {
  font-weight: bold;
  color: cornflowerblue;
}

.menu-item:hover,
.user-account-btn:hover {
  color: cornflowerblue;
}

.recomment_list:first-child {
  background: #d1d0d0;
}

.keywordSearchWrap {
  margin-top: 20px;
}

.keyType_select {
  width: 140px;
}

.house_search_input {
  margin-left: 10px;
  width: 220px !important;
}
.search_result_none {
  margin-bottom: 0px !important;
}
</style>
