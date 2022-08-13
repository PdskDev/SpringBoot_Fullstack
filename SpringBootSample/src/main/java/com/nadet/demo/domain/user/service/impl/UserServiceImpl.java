package com.nadet.demo.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<MUser> getUsers(MUser user) {
		
		return mapper.findMany(user);
	}
	
	/** Get user (1 record) **/
	@Override
	public MUser getUserOne(String userId) {
		
		return mapper.findOne(userId);
	}
	
	/** Update user **/
	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		 mapper.updateOne(userId, password, userName);
	}
	
	/** Delete user **/
	@Override
	public void deleteUserOne(String userId) {
		int count = mapper.deleteOne(userId);
	}
	

}
