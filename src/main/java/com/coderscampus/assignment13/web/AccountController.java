package com.coderscampus.assignment13.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String getUserAccount (ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		Account account = accountService.findById(accountId);
		User user = userService.findById(userId);
		model.put("user", user);
		model.put("account", account);
		return "account";
	}
	
	@PostMapping("{accountId}")
	public String postOneAccount (@PathVariable Long userId, Account account) {
		account = accountService.saveAcc(account);
		return "redirect:/users/" + userId + "/accounts/" + account.getAccountId();
	}
	
}
