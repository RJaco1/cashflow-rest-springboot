package com.rjaco.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.rjaco.dao.IUserAccountDAO;
import com.rjaco.dto.AccountDTO;
import com.rjaco.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rjaco.dao.IAccountDAO;
import com.rjaco.model.Account;
import com.rjaco.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{
	
	@Autowired
	private IAccountDAO dao;

	@Autowired
	private IUserAccountDAO userDAO;

	@Override
	public Account createData(Account t) {
		return dao.save(t);
	}

	@Override
	public Account updateData(Account t) {
		return dao.save(t);
	}

	@Override
	public void deleteData(int id) {
		dao.deleteById(id);
	}

	@Override
	public Account listDataUsingId(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Account> listData() {
		return dao.findAll();
	}

	@Override
	public List<AccountDTO> listAccByUsername(String username) {

		UserAccount user = userDAO.findOneByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("User does not exist", username));
		}

		List<AccountDTO> accDto = new ArrayList<>();
		dao.listAccByUserId(user.getUserId()).forEach(account -> {
			accDto.add(new AccountDTO(
					account.getAccountId(),
					account.getAccountName(),
					account.getUser().getUserId(),
					account.getUser().getUsername(),
					account.getUser().getEmail()
			));
		});
		return accDto;
	}
}
