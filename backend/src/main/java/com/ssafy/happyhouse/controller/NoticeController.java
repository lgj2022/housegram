package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.ssafy.happyhouse.model.AccountDTO;
import com.ssafy.happyhouse.model.NoticeDTO;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@RequestMapping("/happyhouse/api/notice")
@CrossOrigin("*")
public class NoticeController {
	@Autowired
	NoticeService nService;
/*
	@GetMapping("/list")
	public String noticeHome() {
		return "notice/notice";
	}
*/
	@GetMapping(value = "")
	public  ResponseEntity<Map<String, Object>> noticelist(@RequestParam(required = false) String pg,@RequestParam(required = false)String key,@RequestParam(required = false) String word ) throws Exception {
		System.out.println("controller 진입");
		System.out.println("key" + key);
		System.out.println("word"+ word);
		System.out.println("pg"+ pg);
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		int currentPage = Integer.parseInt(pg == null ? "1" : pg);
		int start = (currentPage - 1) * 10;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("spp", 10);
		param.put("key", key);
		param.put("word",word);
		
		int total = nService.getTotalCount(param);
		int lastPage =0;
		if(total%10==0) {
			lastPage = (total/10);
		}
		else {
			lastPage = (total/10)+1;
		}
		int startpage=1;
		if(currentPage%10==0) {
			startpage =((currentPage/10)-1)*10+1;
		}
		else {
			startpage =((currentPage/10))*10+1;
		}
		System.out.println("total :" +total);
		List<NoticeDTO> list = nService.listNotice(param);
		
		
		
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("res", "공지 리스트 조회 성공");
		map1.put("notices", list);
		map1.put("startPage",startpage);
		map1.put("currentPage", currentPage); //현재 페이지
		if(startpage+9 <= lastPage)
		map1.put("currentEndPage",startpage+9); 
		else {
			map1.put("currentEndPage",lastPage); 
		}
		map1.put("lastPage", lastPage); //총 끝페이지
		
		resEntity = new ResponseEntity<Map<String, Object>>(map1, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping(value = "/regist")
	public ResponseEntity<Map<String, Object>> register(@RequestParam String userid, @RequestParam String author, @RequestParam String title, @RequestParam String content) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;
		NoticeDTO notice = new NoticeDTO();
		notice.setUserId(userid);
		notice.setAuthor(author);
		notice.setContent(content);
		notice.setTitle(title);
		nService.create(notice);
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("msg", "공지 인서트 성공");
		resEntity = new ResponseEntity<Map<String, Object>>(map1, HttpStatus.OK);
		return 	resEntity;
				
	}
/*
	@GetMapping(value = "/regist")
	public String registerFormNotice() throws Exception {
		return "notice/noticeCreate";
	}
*/
	/*
	@GetMapping(value = "/detail/{title}")
	public String detailnotice(@PathVariable("title") String title, Model model) throws Exception {
		model.addAttribute("title", title);
		return "notice/noticeDetail";
	}
*/
	/*
	@GetMapping(value = "/noticemodify/{title}")
	public String modifyformnotice(@PathVariable("title") String title, Model model) throws Exception {
		NoticeDTO updateTarget = nService.detail(title);
		model.addAttribute("updateTarget", updateTarget);
		model.addAttribute("title", title);
		return "notice/noticeUpdate";
	}*/

	@GetMapping(value = "/detail/{no}")
	public @ResponseBody ResponseEntity<Map<String, Object>> detailnotice(@PathVariable("no") String noticeNo) throws Exception {
		NoticeDTO notice = nService.detail(noticeNo);
		int hits = Integer.parseInt(notice.getHits());
		hits++;
		notice.setHits(String.valueOf(hits));
		nService.update(notice);
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "공지 하나 조회 성공");
		map.put("notice", notice);
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@PutMapping(value = "/{no}")
	public @ResponseBody ResponseEntity<Map<String, Object>> noticeUpdate(@PathVariable("no") String no,
			@RequestBody NoticeDTO notice) throws Exception, IOException {
		System.out.println("업데이트 수행");
		notice.setNoticeNo(Integer.parseInt(no));
		// System.out.println(notice.toString());
		nService.update(notice);

		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "공지 정보 업데이트 성공");
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@DeleteMapping(value = "/{no}")
	public @ResponseBody ResponseEntity<Map<String, Object>> noticeDelete(@PathVariable("no") String no)
			throws Exception {
		System.out.println("삭제 수행 시작");
		NoticeDTO notice = new NoticeDTO();
		notice.setNoticeNo(Integer.parseInt(no));
		nService.delete(notice);

		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "공지 삭제 성공");

		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
/*
	@GetMapping(value = "/search/{type}/{keyword}")
	public @ResponseBody ResponseEntity<Map<String, Object>> searchNotice(@PathVariable("type")String type, @PathVariable("keyword")String keyword) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("op", type);
		map.put("word", keyword);
		List<NoticeDTO> list =nService.searchNotice(map);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getTitle());
		}
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("notices", list);
		resEntity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		return resEntity;
	}
	*/
}
