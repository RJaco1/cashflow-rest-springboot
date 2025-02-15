package com.rjaco.service;

import com.rjaco.dto.AccountDTO;
import com.rjaco.model.Account;

import java.util.List;

public interface IAccountService extends ICRUD<Account>{

    List<AccountDTO> getDataDTO();

    List<AccountDTO> findAccuntsByUsername(String username);

}
