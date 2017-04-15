package com.duongvantien.service;

import com.duongvantien.model.UserInfo;

import java.util.List;

public interface IUserService {

	public List<UserInfo> findAll() throws Exception;

	public UserInfo findById(int id) throws Exception;

	public boolean createUser(UserInfo userInfo) throws Exception;

	public boolean updateUser(UserInfo userInfo) throws Exception;

	public boolean deleteUser(int id) throws Exception;
}
