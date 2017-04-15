package com.duongvantien.service;

import com.duongvantien.dao.IUserDAO;
import com.duongvantien.dao.IUserRoleDAO;
import com.duongvantien.entity.User;
import com.duongvantien.entity.UserRole;
import com.duongvantien.model.UserInfo;
import com.duongvantien.util.Helpers;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService implements IUserService {

	private static final Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IUserRoleDAO userRoleDAO;

	public List<UserInfo> findAll() throws Exception{
		List<UserInfo> listUserInfo = new ArrayList<UserInfo>();
		try {
			List<User> listUser = userDAO.findAll();
			if (!Helpers.isEmpty(listUser)) {
				for (User user : listUser) {
					listUserInfo.add(user.convertToUserInfo(user));
				}
			}
		} catch (Exception e) {
			logger.error("An exception when findAll user: ", e);
		}
		return listUserInfo;
	}

	public UserInfo findById(int id) throws Exception{
		try {
			User user = userDAO.findById(id);
			if (user != null) {
				return user.convertToUserInfo(user);
			}
		} catch (Exception e) {
			logger.error("An exception when find user :", e);
		}
		return new UserInfo();
	}

	@Transactional(rollbackFor=Throwable.class, propagation = Propagation.REQUIRES_NEW)
	public boolean createUser(UserInfo userInfo) throws Exception {
		try {
			User user = userDAO.save(userInfo.convertToUser(userInfo));
			for (String role : userInfo.getRoles()) {
				userRoleDAO.save(new UserRole(user, role));
			}
			return true;

		} catch (Exception e) {
			logger.error("An exception when create user :", e);
			throw e;
		}
	}

	@Transactional(rollbackFor=Throwable.class, propagation = Propagation.REQUIRES_NEW)
	public boolean updateUser(UserInfo userInfo) throws Exception{
		try {
			User user = userDAO.findById(userInfo.getId());
			if (user == null) {
				return false;
			}
			user.setPassword(userInfo.getPassword());
			user.setStatus(userInfo.getStatus());
			user.setUsername(userInfo.getUsername());
			userDAO.save(user);
			Iterator<UserRole> iterator = user.getUserRoles().iterator();
			int i = userInfo.getRoles().size();
			while (iterator.hasNext()) {
				UserRole userRole = iterator.next();
				if (i > 0 && !Helpers.isEmpty(userInfo.getRoles().get(i - 1))) {
					userRole.setRole(userInfo.getRoles().get(i - 1));
					userRoleDAO.save(userRole);
				} else {
					userRoleDAO.delete(userRole);
				}
				i--;
			}
			while (i > 0) {
				userRoleDAO.save(new UserRole(user, userInfo.getRoles().get(i - 1)));
				i--;
			}
			return true;

		} catch (Exception e) {
			logger.error("An exception when update user :", e);
			throw e;
		}
	}

	@Transactional(rollbackFor=Throwable.class, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteUser(int id) throws Exception{
		try {
			User user = userDAO.findById(id);
			if (user == null) {
				return false;
			}

			userDAO.delete(user);
			return true;

		} catch (Exception e) {
			logger.error("An exception when create user :", e);
			throw e;
		}
	}

}
