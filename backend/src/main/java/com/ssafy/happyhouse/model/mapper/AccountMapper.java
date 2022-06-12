package com.ssafy.happyhouse.model.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AccountDTO;

@Mapper
public interface AccountMapper {
//	int idCheck(String id);
	void create(AccountDTO accountDTO) throws Exception;

	AccountDTO login(Map<String, String> map) throws Exception;

	void update(AccountDTO accountDTO) throws Exception;

	void delete(AccountDTO accountDTO) throws Exception;

	AccountDTO detail(String id) throws Exception;
}
