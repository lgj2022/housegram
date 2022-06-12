package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.NewsDTO;
import com.ssafy.happyhouse.model.NoticeDTO;
import com.ssafy.happyhouse.model.mapper.NewsMapMapper;
import com.ssafy.happyhouse.model.mapper.NoticeMapMapper;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapMapper newsDAO;

	@Override
	public void delete() throws Exception {
		// TODO Auto-generated method stub
		newsDAO.delete();
	}

	@Override
	public void create(NewsDTO newsDTO) throws Exception {
		// TODO Auto-generated method stub
		newsDAO.create(newsDTO);
	}

	@Override
	public List<NewsDTO> listNews() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(newsDAO.listNews().toString());
		return newsDAO.listNews();
	}

	@Override
	public NewsDTO checkDate() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("check 시작");
		return newsDAO.checkDate();
	}

	@Transactional
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("init 시작");
		//
		NewsDTO ch = newsDAO.checkDate();
		if (ch == null) {
			newsDAO.delete();
			Document doc = Jsoup.connect(
					"https://search.naver.com/search.naver?where=news&query=%EB%B6%80%EB%8F%99%EC%82%B0&sm=tab_opt&sort=0&photo=1&field=0&pd=0&ds=&de=&docid=&related=0&mynews=0&office_type=0&office_section_code=0&news_office_checked=&nso=&is_sug_officeid=0")
					.get();
			Elements elem = doc.select("div[class=\"news_area\"]");
			System.out.println("check---------------------------------------------");
			Elements test = doc.select("a.dsc_thumb>img");
			System.out.println("test: " + test.toString());

			int count = 0;
			for (Element el : test) {
				count++;
				System.out.println("img" + el.attr("abs:src"));
			}
			System.out.println("총합:" + count);

			for (int i = 0; i < elem.size(); i++) {
				NewsDTO newsDTO = new NewsDTO();
				newsDTO.setImg(test.get(i).attr("abs:src"));
				// .select("a[class=\"news_tit\"]")
				Elements s = elem.get(i).select("a[class=\"news_tit\"]");
				Elements p = elem.get(i).select("a[class=\"info press\"]");

				for (Element el : s) {
					System.out.println("title:" + el.text());
					newsDTO.setTitle(el.text());
					System.out.println("주소값:" + el.attr("href"));
					newsDTO.setLink(el.attr("href"));
				}
				for (Element el : p) {
					System.out.println("언론사:" + el.text());
					String ptext = el.text();
					ptext = ptext.replace("언론사", "");
					ptext = ptext.replace("선정", "");
					ptext = ptext.trim();
					newsDTO.setPublisher(ptext);
				}
				System.out.println(newsDTO.toString());
				newsDAO.create(newsDTO);
			}
		}
	}

}
