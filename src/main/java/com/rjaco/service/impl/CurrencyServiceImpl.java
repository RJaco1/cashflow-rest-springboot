package com.rjaco.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.rjaco.dao.IUserAccountDAO;
import com.rjaco.dto.AccountDTO;
import com.rjaco.dto.CurrencyDTO;
import com.rjaco.model.Account;
import com.rjaco.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ICurrencyDAO;
import com.rjaco.model.Currency;
import com.rjaco.service.ICurrencyService;

@Service
public class CurrencyServiceImpl implements ICurrencyService {

    @Autowired
    private ICurrencyDAO dao;

    @Autowired
    private IUserAccountDAO userDAO;

    @Override
    public Currency createData(Currency t) {
        return dao.save(t);
    }

    @Override
    public Currency updateData(Currency t) {
        return dao.save(t);
    }

    @Override
    public void deleteData(int id) {
        dao.deleteById(id);
    }

    @Override
    public Currency findData(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Currency> getData() {
        return dao.findAll();
    }

    @Override
    public List<CurrencyDTO> getDataDTO() {
        List<CurrencyDTO> currDto = new ArrayList<>();
        dao.findAll().forEach(currency -> {
            currDto.add(new CurrencyDTO(
                    currency.getCurrencyId(),
                    currency.getCurrency(),
                    currency.getUser().getUserId(),
                    currency.getUser().getUsername(),
                    currency.getUser().getEmail()
            ));
        });

        return currDto;
    }

    @Override
    public List<CurrencyDTO> findCurrenciesByUsername(String username) {
        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        List<CurrencyDTO> currDto = new ArrayList<>();
        dao.listCurrByUserId(user.getUserId()).forEach(currency -> {
            currDto.add(new CurrencyDTO(
                    currency.getCurrencyId(),
                    currency.getCurrency(),
                    currency.getUser().getUserId(),
                    currency.getUser().getUsername(),
                    currency.getUser().getEmail()
            ));
        });

        return currDto;
    }
}
