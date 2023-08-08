package com.digitalBank.backend.digitalBank.web;

import java.util.List;

import com.digitalBank.backend.digitalBank.dto.AccountHistoryDTO;
import com.digitalBank.backend.digitalBank.dto.AccountOperationDTO;
import com.digitalBank.backend.digitalBank.dto.BankAccountDTO;
import com.digitalBank.backend.digitalBank.exceptions.BankAccountNotFoundException;
import com.digitalBank.backend.digitalBank.services.AccountOperationService;
import com.digitalBank.backend.digitalBank.services.BankAccountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {
	BankAccountService bankAccountService;
	AccountOperationService accountOperationService;
	public BankAccountController(BankAccountService bankAccountService,AccountOperationService accountOperationService) {
		this.bankAccountService = bankAccountService;
		this.accountOperationService=accountOperationService;
	}
	@GetMapping("/accounts/{accountId}")
	BankAccountDTO getBankAccount(@PathVariable String  accountId) throws BankAccountNotFoundException {
		return bankAccountService.getBankAccount(accountId);
		
	}
	@GetMapping("/accounts")
	List<BankAccountDTO> getBankAccountList() throws BankAccountNotFoundException {
		return bankAccountService.bankAccountList();
		
	}
	@GetMapping("/accounts/{accountId}/historiqueOperations")
	List<AccountOperationDTO> geAccountOperationByAccountId(@PathVariable String accountId) throws BankAccountNotFoundException {
		return accountOperationService.getOperationsById(accountId);
		
	}
	@GetMapping("/accounts/{accountId}/pageOperations")
	AccountHistoryDTO geAccountHistory(@PathVariable String accountId, @RequestParam(name="page", defaultValue = "0")int page, @RequestParam(name="size", defaultValue = "5")int size) throws BankAccountNotFoundException {
		return accountOperationService.accountHistory(accountId,page,size);
		
	}
}
