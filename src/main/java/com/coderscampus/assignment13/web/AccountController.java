package com.coderscampus.assignment13.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.AddressService;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	
	
	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getUserAccount (ModelMap model,@PathVariable Long userId, @PathVariable Long accountId) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);
		
		model.put("users", Arrays.asList(user));
		model.put("user", user);
		model.put("account", account);
		
		return "account";
	}
	
//	@PostMapping
//	public String postOneAccount (User user, Account account) {
//		accountService.saveAcc(account);
//		return "redirect:/users/" + user.getUserId();
//	}
//	@PostMapping
//	public String deleteOneAccount (User user, @PathVariable Long accountId) {
//		accountService.delAcc(accountId);
//		return "redirect:/users/" + user.getUserId();
//	}
}
