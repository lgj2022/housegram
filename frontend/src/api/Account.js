import axios from "axios";
import { config } from "./index.js";

async function userIdCheck(userid) {
  const params = { userid: userid };
  return await axios.get(`${config.baseUrl}/account/idcheck`, { params });
}
async function userSignIn(id, password) {
  const params = { id, password };
  return await axios.post(`${config.baseUrl}/account/login`, { ...params });
}

async function userSignUp(user) {
  const params = user;
  console.log(params, "params 확인");
  return await axios.post(`${config.baseUrl}/account/regist`, user);
}

async function findById(userid) {
  axios.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  return await axios.get(`${config.baseUrl}/account/info/${userid}`);
}

async function userUpdate(user) {
  console.log("userupdate 확인", user);
  return await axios.put(`${config.baseUrl}/account`, user);
}

async function userDelete(userid) {
  console.log("userdelete 확인", userid);
  return await axios.delete(`${config.baseUrl}/account/${userid}`);
}
async function profileUpload(userid, upfile) {
  console.log("ProfileUpload 확인", userid, upfile);
  let formData = new FormData();
  formData.append("upfile", upfile);
  return await await axios.post(
    `${config.baseUrl}/account/userprofile/${userid}`,
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }
  );
}
export {
  userIdCheck,
  userSignIn,
  userSignUp,
  findById,
  userUpdate,
  userDelete,
  profileUpload,
};
