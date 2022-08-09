package com.nadet.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nadet.demo.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/** User signup **/
	public int insertOne(MUser user);
	
	/** Get User **/
	public List<MUser> findMany();

}
