package com.rjaco.service;

import com.rjaco.dto.UsertypeUserDTO;
import com.rjaco.model.User;

public interface IUserService extends ICRUD<User>{

	User createUser(UsertypeUserDTO usertypeUserDTO);
}
