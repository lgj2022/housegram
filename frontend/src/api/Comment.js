import axios from "axios";
import { config } from "./index.js";

async function fetchCommentList(articleNo) {
  // const params = { pg: page };
  // return axios.get(`${config.baseUrl}/notice?pg=${page}`, params);
  return axios.get(`${config.baseUrl}/reply/${articleNo}`);
  // return axios.get(`${config.baseUrl}/notice?key=${type}&word=""&pg=1`);
}

async function registComment(params) {
  console.log(params);
  return await axios.post(`${config.baseUrl}/reply/regist/`, params);
}

async function updateComment({ rno, rcontent }) {
  const params = { rcontent: rcontent };
  return await axios.put(`${config.baseUrl}/reply/${rno}`, params);
}

function deleteComment(rNo) {
  console.log(rNo);
  return axios.delete(`${config.baseUrl}/reply/${rNo}`);
}

export { fetchCommentList, registComment, updateComment, deleteComment };
