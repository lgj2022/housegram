package com.ssafy.happyhouse.dao;

import java.sql.*;

import com.ssafy.happyhouse.model.AccountDTO;


public interface AccountDAO {

//	int idCheck(String id);
	void create(AccountDTO accountDTO) throws SQLException;

	AccountDTO login(String id, String pass) throws SQLException;

	void update(AccountDTO accountDTO) throws SQLException;

	void delete(AccountDTO accountDTO) throws SQLException;

	AccountDTO detail(String id) throws SQLException;

}
