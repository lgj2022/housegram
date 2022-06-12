import axios from "axios";
import { config } from "./index.js";

async function fetchNewsList() {
  return axios.get(`${config.baseUrl}/news`);
}

export { fetchNewsList };
