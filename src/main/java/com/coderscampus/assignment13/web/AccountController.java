package com.coderscampus.assignment13.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.AddressService;
import com.coderscampus.assignment13.service.UserService;

@Controller
@RequestMapping("/users/{userId}/accounts")
public class AccountController {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	
	
	@GetMapping("{accountId}")
	public String getUserAccount (ModelMap model, User user, @PathVariable Long userId, @PathVariable Long accountId) {
		Account account = accountService.findById(accountId); 
		user = userService.findById(userId);
		model.put("user", user);
		model.put("account", account);
		return "account";
	}
	
	@PostMapping("{accountId}")
	public String postOneAccount (@PathVariable Long userId, Account account) {
		account = accountService.saveAcc(account);
		return "redirect:/users/" + userId + "/accounts/" + account.getAccountId();
	}
	
	@PostMapping("")
	public String createNewAccount (@PathVariable Long userId, User user) {	
		user = userService.findById(userId);
		
		Account account = new Account();
		account.setAccountName("Account #" + user.getAccounts().size());
		account.getUsers().add(user);
	    user.getAccounts().add(account);
	    accountService.saveAcc(account);
	    
	    
		return "redirect:/users/"+ userId + "/accounts/" + account.getAccountId();
	}
	
}
