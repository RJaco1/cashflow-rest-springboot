package com.rjaco.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));

        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }

}
