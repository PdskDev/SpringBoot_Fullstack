package com.nadet.demo.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadet.demo.domain.user.model.MUser;
import com.nadet.demo.domain.user.service.UserService;
import com.nadet.demo.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/** User signup **/
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		
		//Password encryption
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
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
		
		//Password encryption
		String encryptPassword = encoder.encode(password);
		
		mapper.updateOne(userId, encryptPassword, userName);
	}
	
	/** Delete user **/
	@Override
	public void deleteUserOne(String userId) {
		int count = mapper.deleteOne(userId);
	}
	

}
