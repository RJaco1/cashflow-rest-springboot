package com.rjaco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rjaco.dao.IUserAccountDAO;
import com.rjaco.model.UserAccount;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private IUserAccountDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userDAO.findOneByUsernameOrEmail(username, username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        return user;
    }

}
