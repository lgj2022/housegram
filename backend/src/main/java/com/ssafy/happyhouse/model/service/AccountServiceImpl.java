package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.AccountDAO;
import com.ssafy.happyhouse.model.AccountDTO;
import com.ssafy.happyhouse.model.mapper.AccountMapper;
import com.ssafy.happyhouse.util.SHA256;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	
	SHA256 sha = new SHA256();
	@Transactional
	@Override
	public void create(AccountDTO accountDTO) throws Exception {
		
		String salt = sha.generateSalt();
		accountDTO.setSalt(salt);
		String enpw = sha.getEncrypt(accountDTO.getPw(), salt);
		System.out.println(enpw);
		accountDTO.setPw(enpw);
		accountMapper.create(accountDTO);

	}
	
	@Override
	public AccountDTO login(Map<String, String> map) throws Exception {
		AccountDTO member = accountMapper.detail(map.get("id"));
		if(member==null) {
			return null;
		}
		System.out.println(member);
		String salt = member.getSalt();
		System.out.println(salt);
		String enpw = sha.getEncrypt(map.get("password"), salt);
		map.put("password", enpw);
		return accountMapper.login(map);
	}

	@Override
    public void update(AccountDTO accountDTO) throws Exception {
        AccountDTO member = accountMapper.detail(accountDTO.getId());
        String salt = member.getSalt();
        System.out.println(salt);
        if(accountDTO.getPw().length()<20) {
        String enpw = sha.getEncrypt(accountDTO.getPw(), salt);
        accountDTO.setPw(enpw);
        }
        accountMapper.update(accountDTO);
    }
	@Transactional
	@Override
	public void delete(AccountDTO accountDTO) throws Exception {
		accountMapper.delete(accountDTO);

	}
	@Override
	public AccountDTO detail(String id) throws Exception {
		return accountMapper.detail(id);
	}
	
	
}
