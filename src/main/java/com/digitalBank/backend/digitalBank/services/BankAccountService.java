package com.digitalBank.backend.digitalBank.services;

import java.util.List;

import com.digitalBank.backend.digitalBank.dto.BankAccountDTO;
import com.digitalBank.backend.digitalBank.dto.CurrentBankAccountDTO;
import com.digitalBank.backend.digitalBank.dto.SavingBankAccountDTO;
import com.digitalBank.backend.digitalBank.exceptions.BalanceNotSufficientException;
import com.digitalBank.backend.digitalBank.exceptions.BankAccountNotFoundException;
import com.digitalBank.backend.digitalBank.exceptions.CustomerNotFoundException;

public interface BankAccountService {

	CurrentBankAccountDTO saveCurrentBankAccount(CurrentBankAccountDTO accountRequest) throws CustomerNotFoundException;

	SavingBankAccountDTO saveSavingsBankAccount(SavingBankAccountDTO accountRequest) throws CustomerNotFoundException;

	BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

	void debit(String accountId, double amount, String description)
			throws BankAccountNotFoundException, BalanceNotSufficientException;

	void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

	void transfert(String accountIdSource, String accountIdDestination, double amount)
			throws BankAccountNotFoundException, BalanceNotSufficientException;

	List<BankAccountDTO> bankAccountList();

}
