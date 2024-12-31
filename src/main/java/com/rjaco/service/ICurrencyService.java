package com.rjaco.service;

import com.rjaco.dto.AccountDTO;
import com.rjaco.dto.CurrencyDTO;
import com.rjaco.model.Currency;

import java.util.List;

public interface ICurrencyService extends ICRUD<Currency> {

    List<CurrencyDTO> listCurrByUsername(String username);

}