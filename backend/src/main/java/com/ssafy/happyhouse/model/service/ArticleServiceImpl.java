package com.ssafy.happyhouse.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.ArticleDto;
import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.happyhouse.model.NoticeDTO;
import com.ssafy.happyhouse.model.TagDto;
import com.ssafy.happyhouse.model.mapper.ArticleMapMapper;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapMapper articleDAO;
	@Override
	@Transactional
	public int create(ArticleDto articleDto) throws Exception {
		articleDAO.create(articleDto);
		int no = articleDAO.lastInsertId();
		System.out.println(no);
		articleDto.setArticleNo(no);
		List<FileInfoDto> fileInfos = articleDto.getFileInfos();
		List<TagDto> tagInfos = articleDto.getTagInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			articleDAO.registerFile(articleDto);
		}
		if (tagInfos != null && !tagInfos.isEmpty()) {
			for(int i=0 ; i < tagInfos.size() ; i++) {
				if(articleDAO.checkTag(tagInfos.get(i).getTagname())!=0) {
					//이미 있는 해쉬 태그 article tag table에만 추가해준다 
					int hashtagno = articleDAO.hashtagnum(tagInfos.get(i).getTagname());
					articleDto.setHashtagno(hashtagno);
					articleDAO.registerTagArticle(articleDto);
				}
				else {
					//해쉬 태그 테이블에 추가하고
					//
					articleDAO.registerTag(tagInfos.get(i).getTagname());
					int hashtagno=articleDAO.lastInsertId();
					articleDto.setHashtagno(hashtagno);
					articleDAO.registerTagArticle(articleDto);
				}
			}
			
		}
		return no;
	}
	@Override
	public List<ArticleDto> listArticle(Map<String, Object> map) throws Exception {
		System.out.println("service notice list 여기까지 들어감");
		System.out.println("map start:" +map.get("start"));
		System.out.println("map spp"+map.get("spp"));
		
		List<ArticleDto> ch = articleDAO.listArticle(map);
		System.out.println(ch.toString());
		return  articleDAO.listArticle(map);
	}
	@Override
	public ArticleDto detail(int articleNo) throws Exception {
		ArticleDto detail = articleDAO.detail(articleNo);
		List<TagDto> tagInfoList = articleDAO.tagInfoList(articleNo);
		detail.setTagInfos(tagInfoList);
		return detail;
	}
	@Override
	public void update(ArticleDto articleDto) throws Exception {
		articleDAO.update(articleDto);
		List<FileInfoDto> fileInfos = articleDto.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			articleDAO.registerFile(articleDto);
		}
	}
	@Override
	@Transactional
	public void delete(int articleNo) throws Exception {
		articleDAO.delete(articleNo);
	}
	@Override
	public int getTotalCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("get total count map to string --------------");
		System.out.println(map.toString());
		return articleDAO.getTotalCount(map);
	}
	@Override
	public void deleteFile(int articleno) {
		// TODO Auto-generated method stub
		articleDAO.deletFile(articleno);
	}
	@Override
	public int gethashTotalCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("gethashTotalCount map to string --------------");
		System.out.println(map.toString());
		int tmp = articleDAO.gethashTotalCount(map);
		System.out.println("gethashTotalCount"+ tmp);
		return tmp;
	}
	@Override
	public List<ArticleDto> hashlistArticle(Map<String, Object> map) throws Exception {
		System.out.println("hashlistArticle 여기까지 들어감");
		System.out.println("map start:" +map.get("start"));
		System.out.println("map spp"+map.get("spp"));
		
		List<ArticleDto> ch = articleDAO.hashlistArticle(map);
		System.out.println(ch.toString());
		return  articleDAO.hashlistArticle(map);
	}
	@Override
	public List<String> recomend() {
		System.out.println("Service impl recomend");
		List<String> recomendlist = articleDAO.recomend();
		System.out.println(recomendlist.toString());
		return recomendlist;
	}
	@Override
	public int checkLike(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		return articleDAO.chekLike(articleDto);
	}
	@Override
	public int cntLike(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		return articleDAO.cntLike(articleDto);
	}
	@Override
	public void createLike(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		articleDAO.createLike(articleDto);
	}
	@Override
	public void deleteLike(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		articleDAO.deleteLike(articleDto);
	}
	
	
}
