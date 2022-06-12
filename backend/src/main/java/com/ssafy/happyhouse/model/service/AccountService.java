package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.AccountDTO;

public interface AccountService {

//	int idCheck(String id);
	void create(AccountDTO accountDTO) throws Exception;
	AccountDTO login(Map<String, String> map) throws Exception;
	void update(AccountDTO accountDTO) throws Exception;
	void delete(AccountDTO accountDTO) throws Exception;
	AccountDTO detail(String id) throws Exception;
	
}
