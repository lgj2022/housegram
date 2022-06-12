package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.ReplyDTO;


@Mapper
public interface ReplyMapMapper {
	
	void create(ReplyDTO replyDTO) throws SQLException;

	void update(ReplyDTO replyDTO) throws SQLException;

	void delete(ReplyDTO replyDTO) throws SQLException;

	List<ReplyDTO> listReply(int articleno) throws SQLException;
	
}
