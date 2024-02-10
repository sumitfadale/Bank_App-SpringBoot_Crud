package com.Bank.Application.dao;

import com.Bank.Application.pojo.AccountDetails;

public interface AccountDetailDao {

	String saveAccount(AccountDetails account);

	AccountDetails updateAccount(AccountDetails account);

	String deleteAccount(Long id);

}
