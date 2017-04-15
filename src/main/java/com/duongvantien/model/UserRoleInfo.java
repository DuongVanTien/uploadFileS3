package com.duongvantien.model;

import com.duongvantien.entity.User;
import com.duongvantien.entity.UserRole;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserRoleInfo {
	
	private Integer id;
	private UserInfo userInfo;
	private String role;
	
	public UserRoleInfo() {
		super();
	}

	public UserRoleInfo(Integer id, UserInfo userInfo, String role) {
		super();
		this.id = id;
		this.userInfo = userInfo;
		this.role = role;
	}
	
	public UserRole convertToUserRole(UserRoleInfo userRoleInfo){
		UserRole userRole = new UserRole();
		userRole.setRole(userRoleInfo.getRole());
		User user = new User();
		user.setId(userRoleInfo.getUserInfo().getId());
		user.setPassword(userRoleInfo.getUserInfo().getPassword());
		user.setStatus(userRoleInfo.getUserInfo().getStatus());
		user.setUsername(userRoleInfo.getUserInfo().getUsername());
		userRole.setUser(user);
		return userRole;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	@NotEmpty
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@NotEmpty
	@NotNull
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
