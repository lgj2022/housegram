package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.ArticleDto;
import com.ssafy.happyhouse.model.FileInfoDto;


public interface ArticleService {
	int create(ArticleDto articleDto) throws Exception;
	List<ArticleDto> listArticle(Map<String, Object> map) throws Exception;
	int getTotalCount(Map<String, Object> map) throws Exception;
	ArticleDto detail(int articleNo) throws Exception;
	void update(ArticleDto articleDto) throws Exception;
	void delete(int articleNo) throws Exception;
	void deleteFile(int articleno);
	int gethashTotalCount(Map<String, Object> param);
	List<ArticleDto> hashlistArticle(Map<String, Object> param) throws Exception;
	List<String> recomend();
	int checkLike(ArticleDto articleDto);
	int cntLike(ArticleDto articleDto);
	void createLike(ArticleDto articleDto);
	void deleteLike(ArticleDto articleDto);
}
