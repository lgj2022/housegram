package com.ssafy.happyhouse.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.ssafy.happyhouse.model.AccountDTO;
import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.happyhouse.model.service.AccountService;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("happyhouse/api/account")
@CrossOrigin("*")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;
	
	@Autowired
	ResourceLoader resLoader;
	
	@PostMapping(value = "/regist")
	public ResponseEntity<?> userRegister(@RequestBody AccountDTO accountDTO) {
		System.out.println(accountDTO.toString());
		Map<String, String> response = new HashMap<>();
		try {
			accountService.create(accountDTO);
			String msg = "register success";
			response.put("msg", msg);
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling("회원가입에서 에러가 발생했습니다");
		}

	}
	
	@Autowired
	private JwtServiceImpl jwtService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		Map<String, String> response = new HashMap<>();
		try {
			System.out.println("여기까지 갔지? 0");
			AccountDTO accountDto = accountService.login(map);
			System.out.println("여기까지 갔지? 1");
			if (accountDto != null) {
				String msg = "login success";
				//System.out.println("여기서걸림");
				String token = jwtService.create("userInfo", accountDto, "access-token");// key, data, subject
				//System.out.println("넘어왔다");
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				System.out.println("여기까지 갔지? 2");
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			System.out.println("여기까지 갔지? 3"+e);
			return exceptionHandling("로그인에서 실패 에러" + e);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping(value = "/kakao")
	public ResponseEntity<?> kakaoLogin(@RequestParam(value = "code", required = false) String code ) {
		System.out.println(code);
		try {
			System.out.println("이거 안받아져?");
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling("회원 상세조회에서 에러가 발생했습니다");
		}
	}

	
	
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				AccountDTO accountDto = accountService.detail(userid);
				resultMap.put("userInfo", accountDto);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	@GetMapping(value = "/idcheck")
	public ResponseEntity<?> idcheck(@RequestParam("userid") String userid) throws Exception {
		Map<String, String> response = new HashMap<>();
		System.out.println("idcheck 로직 수행");
		AccountDTO accountDto = accountService.detail(userid);
		try {
			if (accountDto != null) {
				response.put("ckid", "impossible");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
			} else {
				response.put("ckid", "possible");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling("idcheck에서 에러");
		}
	}

	@GetMapping(value = "/detail/{userid}")
	public ResponseEntity<?> userInfo(@PathVariable("userid") String userid) {
		try {
			logger.debug("파라미터 : {}", userid);
			AccountDTO accountDto = accountService.detail(userid);
			if (accountDto != null)
				return new ResponseEntity<AccountDTO>(accountDto, HttpStatus.OK);
			else
				return new ResponseEntity<String>("data not found", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling("회원 상세조회에서 에러가 발생했습니다");
		}
	}

	@PutMapping
	public ResponseEntity<?> userModify(@RequestBody AccountDTO accountDto) {
		try {
			System.out.println("유저 수정 시작");
			System.out.println(accountDto.toString());
			accountService.update(accountDto);
			return new ResponseEntity<AccountDTO>(accountDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling("회원수정에서 에러가 발생했습니다");
		}
	}

	@DeleteMapping(value = "/{userid}")
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userid) {
		try {
			AccountDTO accountDto = new AccountDTO();
			accountDto.setId(userid);
			accountService.delete(accountDto);
			return new ResponseEntity<String>(userid + "삭제 완료", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling("회원 삭제에서 에러가 발생했습니다");
		}
	}
	
	@PostMapping("userprofile/{userid}")
	public ResponseEntity<?> userProfile(@PathVariable("userid") String userid, @RequestParam("upfile") MultipartFile file) {
		try {
			System.out.println("userprofile 시작");
			
			AccountDTO accountDTO = accountService.detail(userid);
			if(!file.isEmpty()) {
				Resource res = resLoader.getResource("classpath:static/upload");
				String canonicalPath = res.getFile().getCanonicalPath();
				System.out.println(canonicalPath);
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = canonicalPath + File.separator + today;
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				String originalFileName = file.getOriginalFilename();
				String extension  = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
				FileInfoDto fileInfoDto = new FileInfoDto();
				
				UUID uuid = UUID.randomUUID();
				String filename = uuid.toString()+extension;
				System.out.println(originalFileName);
				System.out.println(file.toString());
				file.transferTo(new File(folder,filename));
				accountDTO.setProfile_img("http://localhost:80/static/upload/"+today + File.separator+ filename);
				System.out.println(accountDTO.getProfile_img());
				System.out.println(accountDTO.toString());
				//"http://localhost:80/static/img/apt.png"
				accountService.update(accountDTO);
		}
		}catch (Exception e) {
			return exceptionHandling("프로필 업데이트에서 에러가 발생했습니다"+e);
		}
		return new ResponseEntity<String>("프로필 업데이트 완료", HttpStatus.OK);
	}
	
	
	private ResponseEntity<String> exceptionHandling(String e) {
		return new ResponseEntity<String>("Error : " + e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
