package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.ArticleDto;
import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.happyhouse.model.TagDto;

@Mapper
public interface ArticleMapMapper {
	
	void create(ArticleDto articleDto) throws Exception;
	void registerFile(ArticleDto articleDto) throws Exception;
	List<ArticleDto> listArticle(Map<String, Object> map) throws Exception;
	int getTotalCount(Map<String, Object> map) throws Exception;
	ArticleDto detail(int articleNo) throws Exception;
	void update(ArticleDto articleDto) throws Exception;
	void delete(int articleNo) throws Exception;
	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
	int lastInsertId();
	void deletFile(int articleNo);
	int checkTag(String tagname);
	int hashtagnum(String tagname);
	void registerTagArticle(ArticleDto articleDto);
	void registerTag(String tagname);
	List<TagDto> tagInfoList(int articleNo);
	int gethashTotalCount(Map<String, Object> map);
	List<ArticleDto> hashlistArticle(Map<String, Object> map) throws Exception;
	List<String> recomend();
	int chekLike(ArticleDto articleDto);
	int cntLike(ArticleDto articleDto);
	void createLike(ArticleDto articleDto);
	void deleteLike(ArticleDto articleDto);	
}
