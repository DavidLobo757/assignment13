package com.coderscampus.assignment13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	public Account saveAcc (Account account) {
		return accountRepo.save(account);
	}
	
	public void delAcc (Long accountId) {
		 accountRepo.deleteById(accountId);
	}
	public Account findById (Long accountId) {
		Optional<Account> accountOpt = accountRepo.findById(accountId);
		return accountOpt.orElse(new Account());
	}
	
	
}
