package com.duongvantien.model;

import com.duongvantien.entity.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UserInfo {

	private Integer id;
	private String username;
	private String password;
	private Integer status;
	private List<UserRoleInfo> userRoleInfos = new ArrayList<UserRoleInfo>();
	private List<String> roles = new ArrayList<String>();

	public UserInfo() {
		super();
	}

	public UserInfo(Integer id, String username, String password, Integer status, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
		this.setRoles(roles);
	}

	public User convertToUser(UserInfo userInfo) {
		User user = new User();
		user.setId(userInfo.getId());
		user.setPassword(userInfo.getPassword());
		user.setStatus(userInfo.getStatus());
		user.setUsername(userInfo.getUsername());
		return user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	@Size(min = 5, max = 20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull
	@Min(value = 8)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<UserRoleInfo> getUserRoleInfos() {
		return userRoleInfos;
	}

	public void setUserRoleInfos(List<UserRoleInfo> userRoleInfos) {
		this.userRoleInfos = userRoleInfos;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
