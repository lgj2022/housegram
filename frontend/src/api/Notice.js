import axios from "axios";
import { config } from "./index.js";

// async function fetchNoticeList(page) {
//   if (page === undefined) {
//     page = 1;
//   }
//   const params = { pg: page };
//   return axios.get(`${config.baseUrl}/notice?pg=${page}`, params);

//   // return axios.get(`${config.baseUrl}/notice`, params);
// }

async function fetchNoticeList({ type, keyword, page }) {
  // const params = { pg: page };
  // return axios.get(`${config.baseUrl}/notice?pg=${page}`, params);
  return axios.get(
    `${config.baseUrl}/notice?key=${type}&word=${keyword}&pg=${page}`
  );
  // return axios.get(`${config.baseUrl}/notice?key=${type}&word=""&pg=1`);
}

async function fetchNoticeItem(noticeNo) {
  return await axios.get(`${config.baseUrl}/notice/detail/${noticeNo}`);
  // return axios.get(`${config.baseUrl}/notice`);
}

async function registNotice(formData) {
  console.log(formData, "찍어봐");
  return await axios.post(`${config.baseUrl}/notice/regist`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

async function updateNotice(noticeNo, noticeItem) {
  return await axios.put(`${config.baseUrl}/notice/${noticeNo}`, noticeItem);
}

function deleteNotice(noticeNo) {
  return axios.delete(`${config.baseUrl}/notice/${noticeNo}`);
}

async function searchNotice(type, keyword) {
  return await axios.get(
    `${config.baseUrl}/notice?key=${type}&word=${keyword}`
  );
}

export {
  fetchNoticeList,
  fetchNoticeItem,
  registNotice,
  updateNotice,
  deleteNotice,
  searchNotice,
};
