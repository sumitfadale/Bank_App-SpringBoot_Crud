package com.Bank.Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bank.Application.pojo.AccountDetails;
import com.Bank.Application.service.AccountDetailService;

@RestController
@RequestMapping("/api/accounts")
public class AccountDetailController {
    @Autowired
	private AccountDetailService accountDetailService;
    @PostMapping("/saveAccount")
    public ResponseEntity<String> createAccount(@RequestBody AccountDetails account)
    {
    	String result =accountDetailService.saveAccount(account);	
		return new ResponseEntity<>(result, HttpStatus.CREATED);
    	
    }
    
    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<AccountDetails> updateAccount(@PathVariable("id")Long id,@RequestBody AccountDetails account)
    {
    	account.setId(id);	
    	account =accountDetailService.updateAccount(account);	
		return new ResponseEntity<>(account, HttpStatus.OK);
    	
    }
    
    @DeleteMapping ("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id")Long id)
    {
    	String result =accountDetailService.deleteAccount(id);	
    	  if ("Deleted Sucessfully".equals(result)) {
    		  return new ResponseEntity<>(result, HttpStatus.OK);
    		  
          } else {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
          }
    	
    }
    
    @GetMapping("/getAllAccounts")
    public ResponseEntity<AccountDetails> getAllAccount()
    {
    	AccountDetails account = new AccountDetails();
    	List<AccountDetails> allAccountList = accountDetailService.getAllAccountList(account);
    	System.out.println(allAccountList);
		return new ResponseEntity<>(account, HttpStatus.OK);
    	
    }
    
}
