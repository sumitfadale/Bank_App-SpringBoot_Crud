package com.Bank.Application.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bank.Application.dao.AccountDetailDao;
import com.Bank.Application.pojo.AccountDetails;
import com.Bank.Application.service.AccountDetailService;
@Service
public class AccountDetailServiceImpl implements AccountDetailService{
@Autowired
private AccountDetailDao accountDetailDao;
	@Override
	public String saveAccount(AccountDetails account) {
		return accountDetailDao.saveAccount(account);
	}
	@Override
	public AccountDetails updateAccount(AccountDetails account) {
		// TODO Auto-generated method stub
		return accountDetailDao.updateAccount(account);
	}
	@Override
	public String deleteAccount(Long id) {
		// TODO Auto-generated method stub
		 return accountDetailDao.deleteAccount(id);
	}
	@Override
	public List<AccountDetails> getAllAccountList(AccountDetails account) {
		// TODO Auto-generated method stub
		return accountDetailDao.getAllAccountList(account);
	}
	

}
