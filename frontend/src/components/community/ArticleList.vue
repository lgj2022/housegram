<template>
  <div class="mt-3">
    <div class="articleSearchWrap">
      <div>
        <input type="text" v-model="keyword" class="hashtag_input" />
        <button @click="searchEvent" class="hashtag_search_btn">
          <i class="fas fa-search"></i>
        </button>
        <div class="tagItems_container">
          <article-tag-item
            v-for="recomend in recomendTagList"
            :key="recomend"
            :recomend="recomend"
          />
        </div>
      </div>
    </div>
    <b-col class="text-right">
      <b-button variant="outline-primary" @click="moveWrite()">글쓰기</b-button>
    </b-col>
    <div class="container">
      <article-list-item
        v-for="article in articleList"
        :key="article.articleNo"
        v-bind="article"
      />
    </div>
    <page-bar
      linkType="article"
      :pageObj="pageLink"
      :keyType="keyType"
      :keyword="keyword"
      class="pagebar"
    ></page-bar>
  </div>
</template>

<script>
// import http from "@/api/http";
//ArticleTagItem
import ArticleListItem from "@/components/community/item/ArticleListItem";
import ArticleTagItem from "@/components/community/item/ArticleTagItem";
import PageBar from "@/components/base/PageBar.vue";
import { mapActions, mapState } from "vuex";
const articleStore = "articleStore";
// import axios from "axios";

export default {
  name: "ArticleList",
  components: {
    ArticleListItem,
    PageBar,
    ArticleTagItem,
  },
  data() {
    return {
      // articles: [],
      keyType: null,
      keyword: "",
      pageObj: {},
    };
  },
  computed: {
    ...mapState(articleStore, ["articleList", "pageLink", "recomendTagList"]),
  },
  async created() {
    await this.fetch_articleList({
      keyType: this.keyType,
      keyword: this.keyword,
      page: 1,
    });
    await this.recomendArticleList();
    console.log("list 부분 해쉬태그 리스트");
    console.log(this.recomendTagList);
    this.pageObj = this.pageLink;
  },
  methods: {
    ...mapActions(articleStore, [
      "fetch_articleList",
      "searchArticleList",
      "recomendArticleList",
    ]),
    moveWrite() {
      this.$router.push({ name: "articleRegister" });
    },
    moveDetail() {
      this.$router.push({ name: "articleDetail" });
    },
    async searchEvent(event) {
      event.preventDefault();
      this.keyType = "hash";
      await this.fetch_articleList({
        keyType: this.keyType,
        keyword: this.keyword,
        page: 1,
      });
    },
  },

  updated() {
    var checkParams = function (config) {
      var DEFAULT_GUTTER = 25;
      var booleanProps = ["useTransform", "center"];

      if (!config) {
        throw new Error("No config object has been provided.");
      }

      for (var prop of booleanProps) {
        if (typeof config[prop] !== "boolean") {
          config[prop] = true;
        }
      }

      if (typeof config.gutter !== "number") {
        config.gutter = DEFAULT_GUTTER;
      }

      if (!config.container) {
        error("container");
      }
      if (!config.items && !config.static) {
        error("items or static");
      }
    };

    /**
     * Handles invalid configuration object
     * errors.
     *
     * @param prop - a property with a missing value
     */
    var error = function (prop) {
      throw new Error("Missing property '" + prop + "' in MagicGrid config");
    };

    /**
     * Finds the shortest column in
     * a column list.
     *
     * @param cols - list of columns
     *
     * @return shortest column
     */
    var getMin = function (cols) {
      var min = cols[0];

      for (var col of cols) {
        if (col.height < min.height) {
          min = col;
        }
      }

      return min;
    };

    /**
     * @author emmanuelolaojo
     * @since 11/10/18
     *
     * The MagicGrid class is an
     * implementation of a flexible
     * grid layout.
     */

    var MagicGrid = function MagicGrid(config) {
      checkParams(config);

      if (config.container instanceof HTMLElement) {
        this.container = config.container;
        this.containerClass = config.container.className;
      } else {
        this.containerClass = config.container;
        this.container = document.querySelector(config.container);
      }

      this.static = config.static || false;
      this.size = config.items;
      this.gutter = config.gutter;
      this.maxColumns = config.maxColumns || false;
      this.useMin = config.useMin || false;
      this.useTransform = config.useTransform;
      this.animate = config.animate || false;
      this.center = config.center;
      this.styledItems = new Set();
    };

    /**
     * Initializes styles
     *
     * @private
     */
    MagicGrid.prototype.initStyles = function initStyles() {
      if (!this.ready()) {
        return;
      }

      this.container.style.position = "relative";
      var items = this.items();

      for (var i = 0; i < items.length; i++) {
        if (this.styledItems.has(items[i])) {
          continue;
        }

        var style = items[i].style;

        style.position = "absolute";

        if (this.animate) {
          style.transition =
            (this.useTransform ? "transform" : "top, left") + " 0.2s ease";
        }

        this.styledItems.add(items[i]);
      }
    };

    /**
     * Gets a collection of all items in a grid.
     *
     * @return {HTMLCollection}
     * @private
     */
    MagicGrid.prototype.items = function items() {
      return this.container.children;
    };

    /**
     * Calculates the width of a column.
     *
     * @return width of a column in the grid
     * @private
     */
    MagicGrid.prototype.colWidth = function colWidth() {
      return this.items()[0].getBoundingClientRect().width + this.gutter;
    };

    /**
     * Initializes an array of empty columns
     * and calculates the leftover whitespace.
     *
     * @return {{cols: Array, wSpace: number}}
     * @private
     */
    MagicGrid.prototype.setup = function setup() {
      var width = this.container.getBoundingClientRect().width;
      var colWidth = this.colWidth();
      var numCols = Math.floor(width / colWidth) || 1;
      var cols = [];

      if (this.maxColumns && numCols > this.maxColumns) {
        numCols = this.maxColumns;
      }

      for (var i = 0; i < numCols; i++) {
        cols[i] = { height: 0, index: i };
      }

      var wSpace = width - numCols * colWidth + this.gutter;

      return { cols: cols, wSpace: wSpace };
    };

    /**
     * Gets the next available column.
     *
     * @param cols list of columns
     * @param i index of dom element
     *
     * @return {*} next available column
     * @private
     */
    MagicGrid.prototype.nextCol = function nextCol(cols, i) {
      if (this.useMin) {
        return getMin(cols);
      }

      return cols[i % cols.length];
    };

    /**
     * Positions each item in the grid, based
     * on their corresponding column's height
     * and index then stretches the container to
     * the height of the grid.
     */
    MagicGrid.prototype.positionItems = function positionItems() {
      var ref = this.setup();
      var cols = ref.cols;
      var wSpace = ref.wSpace;
      var maxHeight = 0;
      var colWidth = this.colWidth();
      var items = this.items();

      wSpace = this.center ? Math.floor(wSpace / 2) : 0;

      this.initStyles();

      for (var i = 0; i < items.length; i++) {
        var col = this.nextCol(cols, i);
        var item = items[i];
        var topGutter = col.height ? this.gutter : 0;
        var left = col.index * colWidth + wSpace + "px";
        var top = col.height + topGutter + "px";

        if (this.useTransform) {
          item.style.transform = "translate(" + left + ", " + top + ")";
        } else {
          item.style.top = top;
          item.style.left = left;
        }

        col.height += item.getBoundingClientRect().height + topGutter;

        if (col.height > maxHeight) {
          maxHeight = col.height;
        }
      }

      this.container.style.height = maxHeight + this.gutter + "px";
    };

    /**
     * Checks if every item has been loaded
     * in the dom.
     *
     * @return {Boolean} true if every item is present
     */
    MagicGrid.prototype.ready = function ready() {
      if (this.static) {
        return true;
      }
      return this.items().length >= this.size;
    };

    /**
     * Periodically checks that all items
     * have been loaded in the dom. Calls
     * this.listen() once all the items are
     * present.
     *
     * @private
     */
    MagicGrid.prototype.getReady = function getReady() {
      var this$1 = this;

      var interval = setInterval(function () {
        this$1.container = document.querySelector(this$1.containerClass);

        if (this$1.ready()) {
          clearInterval(interval);

          this$1.listen();
        }
      }, 100);
    };

    /**
     * Positions all the items and
     * repositions them whenever the
     * window size changes.
     */
    MagicGrid.prototype.listen = function listen() {
      var this$1 = this;

      if (this.ready()) {
        var timeout;

        window.addEventListener("resize", function () {
          if (!timeout) {
            timeout = setTimeout(function () {
              this$1.positionItems();
              timeout = null;
            }, 200);
          }
        });

        this.positionItems();
      } else {
        this.getReady();
      }
    };
    let magicGrid = new MagicGrid({
      container: ".container",
      animate: true,
      gutter: 30,
      static: true,
      useMin: true,
    });

    var masonrys = document.querySelectorAll(".articleImg");
    for (let i = 0; i < masonrys.length; i++) {
      masonrys[i].addEventListener(
        "load",
        function () {
          magicGrid.positionItems();
        },
        false
      );
    }

    magicGrid.listen();
  },
  mounted() {
    var checkParams = function (config) {
      console.log(config, "페이지 바꼈을때");
      var DEFAULT_GUTTER = 25;
      var booleanProps = ["useTransform", "center"];

      if (!config) {
        throw new Error("No config object has been provided.");
      }

      for (var prop of booleanProps) {
        if (typeof config[prop] !== "boolean") {
          config[prop] = true;
        }
      }

      if (typeof config.gutter !== "number") {
        config.gutter = DEFAULT_GUTTER;
      }

      if (!config.container) {
        error("container");
      }
      if (!config.items && !config.static) {
        error("items or static");
      }
    };

    /**
     * Handles invalid configuration object
     * errors.
     *
     * @param prop - a property with a missing value
     */
    var error = function (prop) {
      throw new Error("Missing property '" + prop + "' in MagicGrid config");
    };

    /**
     * Finds the shortest column in
     * a column list.
     *
     * @param cols - list of columns
     *
     * @return shortest column
     */
    var getMin = function (cols) {
      var min = cols[0];

      for (var col of cols) {
        if (col.height < min.height) {
          min = col;
        }
      }

      return min;
    };

    /**
     * @author emmanuelolaojo
     * @since 11/10/18
     *
     * The MagicGrid class is an
     * implementation of a flexible
     * grid layout.
     */

    var MagicGrid = function MagicGrid(config) {
      checkParams(config);

      if (config.container instanceof HTMLElement) {
        this.container = config.container;
        this.containerClass = config.container.className;
      } else {
        this.containerClass = config.container;
        this.container = document.querySelector(config.container);
      }

      this.static = config.static || false;
      this.size = config.items;
      this.gutter = config.gutter;
      this.maxColumns = config.maxColumns || false;
      this.useMin = config.useMin || false;
      this.useTransform = config.useTransform;
      this.animate = config.animate || false;
      this.center = config.center;
      this.styledItems = new Set();
    };

    /**
     * Initializes styles
     *
     * @private
     */
    MagicGrid.prototype.initStyles = function initStyles() {
      if (!this.ready()) {
        return;
      }

      this.container.style.position = "relative";
      var items = this.items();

      for (var i = 0; i < items.length; i++) {
        if (this.styledItems.has(items[i])) {
          continue;
        }

        var style = items[i].style;

        style.position = "absolute";

        if (this.animate) {
          style.transition =
            (this.useTransform ? "transform" : "top, left") + " 0.2s ease";
        }

        this.styledItems.add(items[i]);
      }
    };

    /**
     * Gets a collection of all items in a grid.
     *
     * @return {HTMLCollection}
     * @private
     */
    MagicGrid.prototype.items = function items() {
      return this.container.children;
    };

    /**
     * Calculates the width of a column.
     *
     * @return width of a column in the grid
     * @private
     */
    MagicGrid.prototype.colWidth = function colWidth() {
      return this.items()[0].getBoundingClientRect().width + this.gutter;
    };

    /**
     * Initializes an array of empty columns
     * and calculates the leftover whitespace.
     *
     * @return {{cols: Array, wSpace: number}}
     * @private
     */
    MagicGrid.prototype.setup = function setup() {
      var width = this.container.getBoundingClientRect().width;
      var colWidth = this.colWidth();
      var numCols = Math.floor(width / colWidth) || 1;
      var cols = [];

      if (this.maxColumns && numCols > this.maxColumns) {
        numCols = this.maxColumns;
      }

      for (var i = 0; i < numCols; i++) {
        cols[i] = { height: 0, index: i };
      }

      var wSpace = width - numCols * colWidth + this.gutter;

      return { cols: cols, wSpace: wSpace };
    };

    /**
     * Gets the next available column.
     *
     * @param cols list of columns
     * @param i index of dom element
     *
     * @return {*} next available column
     * @private
     */
    MagicGrid.prototype.nextCol = function nextCol(cols, i) {
      if (this.useMin) {
        return getMin(cols);
      }

      return cols[i % cols.length];
    };

    /**
     * Positions each item in the grid, based
     * on their corresponding column's height
     * and index then stretches the container to
     * the height of the grid.
     */
    MagicGrid.prototype.positionItems = function positionItems() {
      var ref = this.setup();
      var cols = ref.cols;
      var wSpace = ref.wSpace;
      var maxHeight = 0;
      var colWidth = this.colWidth();
      var items = this.items();

      wSpace = this.center ? Math.floor(wSpace / 2) : 0;

      this.initStyles();

      for (var i = 0; i < items.length; i++) {
        var col = this.nextCol(cols, i);
        var item = items[i];
        var topGutter = col.height ? this.gutter : 0;
        var left = col.index * colWidth + wSpace + "px";
        var top = col.height + topGutter + "px";

        if (this.useTransform) {
          item.style.transform = "translate(" + left + ", " + top + ")";
        } else {
          item.style.top = top;
          item.style.left = left;
        }

        col.height += item.getBoundingClientRect().height + topGutter;

        if (col.height > maxHeight) {
          maxHeight = col.height;
        }
      }

      this.container.style.height = maxHeight + this.gutter + "px";
    };

    /**
     * Checks if every item has been loaded
     * in the dom.
     *
     * @return {Boolean} true if every item is present
     */
    MagicGrid.prototype.ready = function ready() {
      if (this.static) {
        return true;
      }
      return this.items().length >= this.size;
    };

    /**
     * Periodically checks that all items
     * have been loaded in the dom. Calls
     * this.listen() once all the items are
     * present.
     *
     * @private
     */
    MagicGrid.prototype.getReady = function getReady() {
      var this$1 = this;

      var interval = setInterval(function () {
        this$1.container = document.querySelector(this$1.containerClass);

        if (this$1.ready()) {
          clearInterval(interval);

          this$1.listen();
        }
      }, 100);
    };

    /**
     * Positions all the items and
     * repositions them whenever the
     * window size changes.
     */
    MagicGrid.prototype.listen = function listen() {
      var this$1 = this;

      if (this.ready()) {
        var timeout;

        window.addEventListener("resize", function () {
          if (!timeout) {
            timeout = setTimeout(function () {
              this$1.positionItems();
              timeout = null;
            }, 200);
          }
        });

        this.positionItems();
      } else {
        this.getReady();
      }
    };
    let magicGrid = new MagicGrid({
      container: ".container",
      animate: true,
      gutter: 30,
      static: true,
      useMin: true,
    });

    var masonrys = document.querySelectorAll(".articleImg");
    for (let i = 0; i < masonrys.length; i++) {
      masonrys[i].addEventListener(
        "load",
        function () {
          magicGrid.positionItems();
        },
        false
      );
    }

    magicGrid.listen();
  },
};
</script>

<style scoped>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.container {
  padding: 0;
  margin: 0 auto;
}
.container a {
  max-width: 200px;
}
.container div {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 1rem;
}
.container img {
  width: 100%;
  border-radius: 1rem;
}

.tagItems_container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.hashtag_input {
  padding: 4px 10px;
  border-radius: 10px;
  outline: none;
  border: 1px solid gray;
  margin-right: 10px;
}

.hashtag_search_btn {
  padding: 2px 6px;
  box-shadow: 0px 0px 6px grey;
  border: 0px;
  background: transparent;
  border-radius: 100%;
}
.pagebar {
  height: 120px;
}
</style>
