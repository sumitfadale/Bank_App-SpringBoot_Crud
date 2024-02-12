package com.Bank.Application.dao;

import java.util.List;

import com.Bank.Application.pojo.AccountDetails;

public interface AccountDetailDao {

	String saveAccount(AccountDetails account);

	AccountDetails updateAccount(AccountDetails account);

	String deleteAccount(Long id);

	List<AccountDetails> getAllAccountList(AccountDetails account);

	

}
