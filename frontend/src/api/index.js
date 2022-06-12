import axios from "axios";

const config = {
  baseUrl: "http://localhost/happyhouse/api",
  headers: {
    "Content-type": "application/json",
  },
};

// KaKaoMap Axios
function fetch_Sido() {
  return axios.get(`${config.baseUrl}/map/sido`);
}

function fetch_Gugun() {
  return axios.get(`${config.baseUrl}/map/gugun`);
}

async function fetch_house_keyword_search({ type, keyword }) {
  console.log(type, keyword, "지");
  return await axios.post(`${config.baseUrl}/map/search/${type}/${keyword}`);
}
export { fetch_Sido, fetch_Gugun, config, fetch_house_keyword_search };
