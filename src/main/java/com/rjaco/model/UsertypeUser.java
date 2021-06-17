package com.rjaco.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(UsertypeUserPK.class)
public class UsertypeUser {
	
	@Id
	private UserType usertype;
	
	@Id
	private User user;

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
