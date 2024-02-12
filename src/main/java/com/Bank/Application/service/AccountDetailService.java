package com.Bank.Application.service;

import java.util.List;

import com.Bank.Application.pojo.AccountDetails;

public interface AccountDetailService {

	String saveAccount(AccountDetails account);

	AccountDetails updateAccount(AccountDetails account);

	String deleteAccount(Long id);

	List<AccountDetails> getAllAccountList(AccountDetails account);

}
