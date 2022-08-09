package com.nadet.demo.domain.user.service;

import java.util.List;

import com.nadet.demo.domain.user.model.MUser;

public interface UserService {
	
	/** User signup **/
	public void signup(MUser user);
	
	/** Get user **/
	public List<MUser> getUsers();

}
