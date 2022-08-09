package com.nadet.demo.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadet.demo.domain.user.model.MUser;
import com.nadet.demo.domain.user.service.UserService;
import com.nadet.demo.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	/** User signup **/
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		mapper.insertOne(user);
	}
	
	/** Get user **/
	public List<MUser> getUsers() {
		
		return mapper.findMany();
	}

}
