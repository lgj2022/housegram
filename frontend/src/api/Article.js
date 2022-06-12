import axios from "axios";
import { config } from "./index.js";

async function fetchArticleList({ keyType, keyword, page }) {
  console.log(keyType, keyword, page, "여기값");
  return axios.get(
    `${config.baseUrl}/article?key=${keyType}&word=${keyword}&pg=${page}`
  );
}

async function fetchArticleItem(articleNo) {
  return await axios.get(`${config.baseUrl}/article/detail/${articleNo}`);
}

async function registArticle(formData) {
  return await axios.post(`${config.baseUrl}/article/regist`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

async function updateArticle({ articleNo, formData }) {
  for (let key of formData.keys()) {
    console.log(key, ":", formData.get(key));
  }
  return await axios.put(`${config.baseUrl}/article/${articleNo}`, formData);
}

function deleteArticle(articleNo) {
  return axios.delete(`${config.baseUrl}/article/${articleNo}`);
}

async function searchArticle(type, keyword) {
  return await axios.get(
    `${config.baseUrl}/article?key=${type}&word=${keyword}`
  );
}

async function recomendTag() {
  return await axios.get(`${config.baseUrl}/article/recomend`);
}
async function chLike(articleno, userid) {
  console.log("chlike 보내기전 axios 확인");
  console.log(articleno);
  console.log(userid);

  //const params = { articleno, userid };
  //http://localhost/happyhouse/api/article/checklike?articleno=64&userid=admin
  return await axios.get(
    `${config.baseUrl}/article/checklike?articleno=${articleno}&userid=${userid}`
  );
}

async function getLikecnt(articleno) {
  console.log("getLikecnt 보내기전 axios 확인");
  return await axios.get(
    `${config.baseUrl}/article/likecnt?articleno=${articleno}`
  );
}

async function createLike(articleno, userid) {
  console.log("createLike 보내기전 axios 확인");
  console.log(articleno);
  console.log(userid);
  return await axios.post(
    `${config.baseUrl}/article/like?articleno=${articleno}&userid=${userid}`
  );
}

async function deleteLike(articleno, userid) {
  console.log("deleteLike 보내기전 axios 확인");
  console.log(articleno);
  console.log(userid);
  return await axios.delete(
    `${config.baseUrl}/article/like?articleno=${articleno}&userid=${userid}`
  );
}
export {
  fetchArticleList,
  fetchArticleItem,
  registArticle,
  updateArticle,
  deleteArticle,
  searchArticle,
  recomendTag,
  chLike,
  getLikecnt,
  createLike,
  deleteLike,
};
