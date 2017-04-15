package com.duongvantien.entity;

import com.duongvantien.model.UserInfo;
import com.duongvantien.model.UserRoleInfo;
import com.duongvantien.util.Helpers;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author FRAMGIA\do.khanh.toan
 */
@Entity
@Table(name = "user", catalog = "CurrencyMonitor")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private Integer status;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, Integer status, Set<UserRole> userRoles) {
		this.username = username;
		this.password = password;
		this.status = status;
		this.userRoles = userRoles;
	}

	public UserInfo convertToUserInfo(User user) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(user.getId());
		userInfo.setPassword(user.getPassword());
		userInfo.setStatus(user.getStatus());
		userInfo.setUsername(user.getUsername());
		Set<UserRole> roles = user.getUserRoles();
		List<UserRoleInfo> userRoleInfos = new ArrayList<UserRoleInfo>();
		if (!Helpers.isEmpty(roles)) {
			for (UserRole userRole : roles) {
				UserRoleInfo userRoleInfo = new UserRoleInfo();
				userRoleInfo.setId(userRole.getId());
				userRoleInfo.setRole(userRole.getRole());
				userRoleInfos.add(userRoleInfo);
			}
		}
		userInfo.setUserRoleInfos(userRoleInfos);
		return userInfo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
