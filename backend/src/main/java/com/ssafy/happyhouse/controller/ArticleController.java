package com.ssafy.happyhouse.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.ArticleDto;
import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.happyhouse.model.TagDto;
import com.ssafy.happyhouse.model.service.ArticleService;

@RestController
@RequestMapping("happyhouse/api/article")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@Autowired
	ResourceLoader resLoader;

	@PostMapping("/regist")
	public ResponseEntity<?> articleregister(@RequestParam String userid, @RequestParam String title,
			@RequestParam String content, @RequestParam("upfile") MultipartFile[] files ,@RequestParam("tag") String[] tags) {
		Map<String, String> response = new HashMap<>();
		try {
			// articleDto user정보 실어서 보냈다고 가정
			System.out.println("create 수행시작");
			ArticleDto articleDto = new ArticleDto();
			articleDto.setUserid(userid);
			articleDto.setContent(content);
			articleDto.setTitle(title);

			// System.out.println("파일올리면 이거야 : "+files);

//		FileUpload 관련 설정.
//		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
			if (!files[0].isEmpty()) {
//			String realPath = servletContext.getRealPath("/upload");
				// 파일을 저장할 폴더 지정
				Resource res = resLoader.getResource("classpath:static/upload");
				String canonicalPath = res.getFile().getCanonicalPath();
				logger.debug("file upload canonical path : {}", canonicalPath);
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = canonicalPath + File.separator + today;
//			logger.debug("저장 폴더 : {}", saveFolder);
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
				for (MultipartFile mfile : files) {
					FileInfoDto fileInfoDto = new FileInfoDto();
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = System.nanoTime()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setSaveFolder(today);
						fileInfoDto.setOriginFile(originalFileName);
						fileInfoDto.setSaveFile(saveFileName);
						fileInfoDto.setImgpath("http://localhost:80/static/upload/"+today + File.separator+ saveFileName);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
				}
				List<TagDto> tagInfos = new ArrayList<TagDto>();
				if(tags.length!=0) {
					for (int i = 0; i < tags.length; i++) {
						TagDto tagDto = new TagDto();
						
						tagDto.setTagname(tags[i]);
						tagInfos.add(tagDto);
					}
					
				}
				articleDto.setFileInfos(fileInfos);
				articleDto.setTagInfos(tagInfos);
			}
			System.out.println(articleDto);
			articleService.create(articleDto);
		} catch (Exception e) {
			return exceptionHandling("게시판 등록 에러가 발생했습니다" + e);
		}

		return new ResponseEntity<String>("게시판 등록 완료", HttpStatus.OK);
	}

	@GetMapping(value = "")
	public ResponseEntity<Map<String, Object>> articlelist(@RequestParam(required = false) String pg,
			@RequestParam(required = false) String key, @RequestParam(required = false) String word) throws Exception {
		System.out.println("controller 진입");
		System.out.println("key" + key);
		System.out.println("word" + word);
		System.out.println("pg" + pg);
		ResponseEntity<Map<String, Object>> resEntity = null;

		int currentPage = Integer.parseInt(pg == null ? "1" : pg);
		int start = (currentPage - 1) * 10;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("spp", 10);
		if(!key.equals("hash")) {
			
		param.put("key", key);
		param.put("word", word);
		
		
		int total = articleService.getTotalCount(param);
		int lastPage = 0;
		if (total % 10 == 0) {
			lastPage = (total / 10);
		} else {
			lastPage = (total / 10) + 1;
		}
		int startpage = 1;
		if (currentPage % 10 == 0) {
			startpage = ((currentPage / 10) - 1) * 10 + 1;
		} else {
			startpage = ((currentPage / 10)) * 10 + 1;
		}
		System.out.println("total :" + total);
		List<ArticleDto> list = articleService.listArticle(param);

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("res", "article 리스트 조회 성공");
		map1.put("articles", list);
		map1.put("startPage", startpage);
		map1.put("currentPage", currentPage); // 현재 페이지
		if (startpage + 9 <= lastPage)
			map1.put("currentEndPage", startpage + 9);
		else {
			map1.put("currentEndPage", lastPage);
		}
		map1.put("lastPage", lastPage); // 총 끝페이지
		
		resEntity = new ResponseEntity<Map<String, Object>>(map1, HttpStatus.OK);
		}
		else {
			param.put("key", key);
			param.put("word", word);
			int total = articleService.gethashTotalCount(param);
			int lastPage = 0;
			if (total % 10 == 0) {
				lastPage = (total / 10);
			} else {
				lastPage = (total / 10) + 1;
			}
			int startpage = 1;
			if (currentPage % 10 == 0) {
				startpage = ((currentPage / 10) - 1) * 10 + 1;
			} else {
				startpage = ((currentPage / 10)) * 10 + 1;
			}
			System.out.println("total :" + total);
			List<ArticleDto> list = articleService.hashlistArticle(param);
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("res", "article 리스트 조회 성공");
			map1.put("articles", list);
			map1.put("startPage", startpage);
			map1.put("currentPage", currentPage); // 현재 페이지
			if (startpage + 9 <= lastPage)
				map1.put("currentEndPage", startpage + 9);
			else {
				map1.put("currentEndPage", lastPage);
			}
			map1.put("lastPage", lastPage); // 총 끝페이지
			
			resEntity = new ResponseEntity<Map<String, Object>>(map1, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping(value = "/detail/{no}")
	public @ResponseBody ResponseEntity<Map<String, Object>> detailarticle(@PathVariable("no") String articleno)
			throws Exception {
		ArticleDto article = articleService.detail(Integer.parseInt(articleno));
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "article" + articleno + "조회 성공");
		map.put("article", article);
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PutMapping(value = "/{no}")
	public @ResponseBody ResponseEntity<Map<String, Object>> articleUpdate(@PathVariable("no") String articleno,@RequestParam String userid,@RequestParam String title, @RequestParam String content,@RequestParam("upfile") MultipartFile[] files,@RequestParam("tag") String[] tags) throws Exception, IOException {
		System.out.println("업데이트 수행");
		//articleDto user정보 실어서 보냈다고 가정
		ArticleDto articleDto = new ArticleDto();
		articleDto.setUserid(userid);
		articleDto.setContent(content);
		articleDto.setTitle(title);
		articleDto.setArticleNo(Integer.parseInt(articleno));
		 System.out.println(articleDto.toString());
		 if (!files[0].isEmpty()) {
//				String realPath = servletContext.getRealPath("/upload");
				// 파일을 저장할 폴더 지정
				Resource res = resLoader.getResource("classpath:static/upload");
				String canonicalPath = res.getFile().getCanonicalPath();
				logger.debug("file upload canonical path : {}", canonicalPath);
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = canonicalPath + File.separator + today;
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
				for (MultipartFile mfile : files) {
					FileInfoDto fileInfoDto = new FileInfoDto();
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = System.nanoTime()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setSaveFolder(today);
						fileInfoDto.setOriginFile(originalFileName);
						fileInfoDto.setSaveFile(saveFileName);
						fileInfoDto.setImgpath("http://localhost:80/static/upload/"+today + File.separator+ saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
				}
				articleDto.setFileInfos(fileInfos);
		 }
		articleService.deleteFile(Integer.parseInt(articleno));
		articleService.update(articleDto);

		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "article 정보 업데이트 성공");
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@DeleteMapping(value = "/{no}")
	public @ResponseBody ResponseEntity<Map<String, Object>> articleDelete(@PathVariable("no") String no)
			throws Exception {
		System.out.println("article 삭제 수행 시작");
		articleService.delete(Integer.parseInt(no));

		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "article 삭제 성공");

		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping(value = "/recomend")
	public @ResponseBody ResponseEntity<Map<String, Object>> hashRecomend()
			throws Exception {
		System.out.println("hashRecomend 조회 시작");
		List<String> recomend = articleService.recomend();
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recomends",recomend);
		map.put("res", "hashRecomend 조회 성공");

		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	@GetMapping(value = "/checklike")
	public @ResponseBody ResponseEntity<Map<String, Object>> checklike(@RequestParam String articleno, @RequestParam String userid)
			throws Exception {
		System.out.println("checklike 조회 시작");
	
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticleNo(Integer.parseInt(articleno));
		articleDto.setUserid(userid);
		System.out.println(articleDto.toString());
		int a = articleService.checkLike(articleDto);
		int likecnt = articleService.cntLike(articleDto);
		System.out.println("결과 확인"+a);
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		//map.put("recomends",recomend);
		map.put("likecnt",likecnt);
		if(a==1) {
		map.put("res", "checked");
		
		}
		else {
			map.put("res", "unchecked");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	
	@PostMapping(value = "/like")
	public @ResponseBody ResponseEntity<Map<String, Object>> CreateLike(@RequestParam String articleno, @RequestParam String userid)
			throws Exception {
		System.out.println("CreateLike 인서트");
	
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticleNo(Integer.parseInt(articleno));
		articleDto.setUserid(userid);
		System.out.println(articleDto.toString());
		articleService.createLike(articleDto);
		int likecnt = articleService.cntLike(articleDto);
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("likecnt",likecnt);
		map.put("res", "좋아요 성공");
		
		
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	
	@DeleteMapping(value = "/like")
	public @ResponseBody ResponseEntity<Map<String, Object>> DeleteLike(@RequestParam String articleno, @RequestParam String userid)
			throws Exception {
		System.out.println("DeleteLike 인서트");
	
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticleNo(Integer.parseInt(articleno));
		articleDto.setUserid(userid);
		System.out.println(articleDto.toString());
		articleService.deleteLike(articleDto);
		int likecnt = articleService.cntLike(articleDto);
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("likecnt",likecnt);
		map.put("res", "좋아요 해제");
		
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	
//	@GetMapping(value = "/download")
//	public String downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
//			@RequestParam("sfile") String sfile, Model model, HttpSession session) {
//		System.out.println("다운로드 한다!!");
//		Map<String, Object> fileInfo = new HashMap<String, Object>();
//		fileInfo.put("sfolder", sfolder);
//		fileInfo.put("ofile", ofile);
//		fileInfo.put("sfile", sfile);
//		model.addAttribute("downloadFile", fileInfo);
//		return "fileDownLoadView";
//	}
	/*
	 * @PostMapping("/download")
	 * 
	 * @ResponseBody public ResponseEntity<Resource> download(@RequestParam
	 * Map<String, Object> param, HttpServletRequest request) throws IOException{
	 * Resource res = resLoader.getResource("classpath:static/upload"); String
	 * canonicalPath = res.getFile().getCanonicalPath(); String filePath =
	 * canonicalPath + File.separator + param.get("sfolder") + File.separator +
	 * param.get("sfile"); File target = new File(filePath); HttpHeaders header =
	 * new HttpHeaders(); Resource rs = null; if(target.exists()) { try { String
	 * mimeType = Files.probeContentType(Paths.get(target.getAbsolutePath()));
	 * if(mimeType == null) { mimeType = "apllication/download; charset=UTF-8"; } rs
	 * = new UrlResource(target.toURI()); String userAgent =
	 * request.getHeader("User-Agent"); boolean isIE = userAgent.indexOf("MSIE") >
	 * -1 || userAgent.indexOf("Trident") > -1; String fileName = null; String
	 * originalFile = (String) param.get("ofile"); // IE는 다르게 처리 if (isIE) {
	 * fileName = URLEncoder.encode(originalFile, "UTF-8").replaceAll("\\+", "%20");
	 * } else { fileName = new String(originalFile.getBytes("UTF-8"), "ISO-8859-1");
	 * } header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+
	 * fileName +"\""); header.setCacheControl("no-cache");
	 * header.setContentType(MediaType.parseMediaType(mimeType)); }catch(Exception
	 * e) { e.printStackTrace(); } } return new ResponseEntity<Resource>(rs, header,
	 * HttpStatus.OK); }
	 */

	private ResponseEntity<String> exceptionHandling(String e) {
		return new ResponseEntity<String>("Error : " + e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
