package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.ReplyDTO;
import com.ssafy.happyhouse.model.service.ReplyService;

@RestController
@RequestMapping("/happyhouse/api/reply")
public class ReplyController {
	
	@Autowired
	ReplyService replyService;

	@GetMapping(value = "/{no}")
	public  ResponseEntity<Map<String, Object>> Replylist(@PathVariable("no") String no) throws Exception {

		ResponseEntity<Map<String, Object>> resEntity = null;
		
		List<ReplyDTO> list = replyService.listReply(Integer.parseInt(no));
		
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("res", "댓글 리스트 조회 성공");
		map1.put("replys", list);
		
		resEntity = new ResponseEntity<Map<String, Object>>(map1, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping(value = "/regist")
	public ResponseEntity<Map<String, Object>> register(@RequestBody ReplyDTO reply) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;
		replyService.create(reply);
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("msg", "댓글 인서트 성공");
		resEntity = new ResponseEntity<Map<String, Object>>(map1, HttpStatus.OK);
		return 	resEntity;
	}
	
	@PutMapping(value = "/{no}")
	public @ResponseBody ResponseEntity<Map<String, Object>> noticeUpdate(@PathVariable("no") String no,
			@RequestBody ReplyDTO reply) throws Exception, IOException {
		System.out.println("업데이트 수행 시작");
		reply.setRno(no);
		replyService.update(reply);

		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "댓글 정보 업데이트 성공");
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

	@DeleteMapping(value = "/{no}")
	public @ResponseBody ResponseEntity<Map<String, Object>> noticeDelete(@PathVariable("no") String no)
			throws Exception {
		System.out.println("댓글 삭제 수행 시작");
		ReplyDTO reply = new ReplyDTO();
		reply.setRno(no);
		replyService.delete(reply);

		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "댓글 삭제 성공");

		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
}
