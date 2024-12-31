package com.rjaco.service;

import com.rjaco.dto.AccountDTO;
import com.rjaco.model.Account;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountService extends ICRUD<Account>{

    List<AccountDTO> listAccByUsername(String username);

}
