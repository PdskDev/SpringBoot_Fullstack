package com.nadet.demo.domain.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadet.demo.domain.user.model.MUser;
import com.nadet.demo.domain.user.service.UserService;
import com.nadet.demo.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl2 implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/** User signup*/
	@SuppressWarnings("serial")
	@Transactional
	@Override
	public void signup(MUser user) {
		
		//Existence check
		boolean exists = repository.existsById(user.getUserId());
		
		if(exists) {
			throw new DataAccessException("User already exists"){};
		}
		
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		
		//Password encryption
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		//Insert new user
		repository.save(user);
	}
	
	/** Get all user */
	@Override
	public List<MUser> getUsers(MUser user) {
		/* return repository.findAll(); */
		
		//Search conditions
		ExampleMatcher matcher = ExampleMatcher
				.matching() // and condition
				.withStringMatcher(StringMatcher.CONTAINING) // Like clause
				.withIgnoreCase(); // Both uppercase and lowercase
		return repository.findAll(Example.of(user, matcher));
	}
	
	/** Get one user */
	public MUser getUserOne(String userId) {
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		
		return user;
	}
	
	/** Update user */
	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String UserName) {
		//Password encryption
		String encryptPassword = encoder.encode(password);
		
		//User update
		repository.updateUser(userId, encryptPassword, UserName);
	}
	
	/** Delete user*/
	@Transactional
	@Override
	public void deleteUserOne(String userId) {
		repository.deleteById(userId);
	}
	
	/** Get login user */
	@Override
	public MUser getLoginUser(String userId) {
		/*
		 * Optional<MUser> option = repository.findById(userId); MUser user =
		 * option.orElse(null);
		 * 
		 * return user;
		 */
		
		return repository.findLoginUser(userId);
	}

}
