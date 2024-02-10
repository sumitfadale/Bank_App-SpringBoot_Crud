package com.Bank.Application.service;

import com.Bank.Application.pojo.AccountDetails;

public interface AccountDetailService {

	String saveAccount(AccountDetails account);

	AccountDetails updateAccount(AccountDetails account);

	String deleteAccount(Long id);

}
