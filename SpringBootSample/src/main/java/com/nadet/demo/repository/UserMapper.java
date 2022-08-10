package com.nadet.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nadet.demo.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/** User signup **/
	public int insertOne(MUser user);
	
	/** Get User **/
	public List<MUser> findMany();
	
	/** Get one user**/
	public MUser findOne(String userId);
	
	/** Update User **/
	public void updateOne(
			@Param("userId") String userId, 
			@Param("password") String password,
			@Param("userName") String userName
			);
	
    /** Delete user **/
	public int deleteOne(@Param("userId") String userId);
}
