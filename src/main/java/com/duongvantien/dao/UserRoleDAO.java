package com.duongvantien.dao;

import com.duongvantien.entity.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleDAO extends GenericDAO<UserRole, Integer> implements IUserRoleDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserRoleDAO() {
		super(UserRole.class);
	}

}
