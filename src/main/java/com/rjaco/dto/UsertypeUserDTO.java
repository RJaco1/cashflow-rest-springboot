package com.rjaco.dto;

import com.rjaco.model.User;
import com.rjaco.model.UserType;

public class UsertypeUserDTO {

	private User user;
	private UserType userType;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
