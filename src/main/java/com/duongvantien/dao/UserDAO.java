package com.duongvantien.dao;

import com.duongvantien.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserDAO extends GenericDAO<User, Integer> implements IUserDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDAO() {
		super(User.class);
	}

}
