<template>
  <div id="app">
    <tool-bar></tool-bar>
    <router-view />
    <foot-bar></foot-bar>
  </div>
</template>
<script>
import ToolBar from "@/components/base/ToolBar.vue";
import FootBar from "@/components/base/FootBar.vue";

export default {
  components: {
    ToolBar,
    FootBar,
  },
};
</script>
<style>
html,
body,
div,
span,
applet,
object,
iframe,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
a,
abbr,
acronym,
address,
big,
cite,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
s,
samp,
small,
strike,
strong,
sub,
sup,
tt,
var,
b,
u,
i,
center,
dl,
dt,
dd,
ol,
ul,
li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
embed,
figure,
figcaption,
footer,
header,
hgroup,
menu,
nav,
output,
ruby,
section,
summary,
time,
mark,
audio,
video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */
article,
aside,
details,
figcaption,
figure,
footer,
header,
hgroup,
menu,
nav,
section {
  display: block;
}
body {
  line-height: 1;
}
ol,
ul {
  list-style: none;
}
blockquote,
q {
  quotes: none;
}
blockquote:before,
blockquote:after,
q:before,
q:after {
  content: "";
  content: none;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
}

#app {
  position: relative;
  min-height: 100vh;
  max-width: 1250px;
  margin: auto;
}

/* 반응형 */
@media screen and (max-width: 835px) {
  .cover-title {
    font-size: 80px;
  }

  .menu-item,
  .user-account-btn {
    font-size: 14px;
  }

  .map-wrap {
    flex-direction: column;
  }

  .map-wrap div:first-child {
    width: 100%;
  }

  .apt-info {
    width: 100%;
    height: 300px;
  }
}

@media screen and (max-width: 775px) {
  .menu-item,
  .user-account-btn {
    font-size: 12px;
  }
  .content-list {
    display: flex;
    flex-direction: column;
  }

  .ad-container {
    width: 95%;
  }

  .tip-container,
  .news-container {
    padding-top: 10px;
    padding-left: 10px;
    margin-top: 10px;
    border-top: 1px solid #eeeeee;
  }

  .tip-list {
    margin: 0 0 0 2px;
  }
}

@media screen and (max-width: 720px) {
  .menu-item,
  .user-account-btn {
    font-size: 10px;
  }
}
@media screen and (max-width: 660px) {
  .navbar {
    align-items: flex-start;
    flex-direction: column;
    padding: 16px 20px 10px 20px;
  }

  .user-account-container {
    right: 0px;
    position: absolute;
    top: 34px;
  }

  .fa-bars {
    visibility: visible;
  }

  .menu,
  .user-account-container {
    display: none;
    margin-top: 10px;
  }

  .searchForm {
    right: 18px;
    top: 97px;
  }
}
</style>
