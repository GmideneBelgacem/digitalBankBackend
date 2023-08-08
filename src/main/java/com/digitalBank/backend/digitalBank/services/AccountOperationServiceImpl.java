package com.digitalBank.backend.digitalBank.services;

import java.util.List;
import java.util.stream.Collectors;

import com.digitalBank.backend.digitalBank.dto.AccountHistoryDTO;
import com.digitalBank.backend.digitalBank.dto.AccountOperationDTO;
import com.digitalBank.backend.digitalBank.entities.AccountOperation;
import com.digitalBank.backend.digitalBank.entities.BankAccount;
import com.digitalBank.backend.digitalBank.exceptions.BankAccountNotFoundException;
import com.digitalBank.backend.digitalBank.mappers.AccountOperationMapper;
import com.digitalBank.backend.digitalBank.repositories.AccountOperationRepository;
import com.digitalBank.backend.digitalBank.repositories.BankAccountRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service
public class AccountOperationServiceImpl implements AccountOperationService{
private AccountOperationRepository accountOperationRepository;
private AccountOperationMapper operationMapper;
private BankAccountRepository bankAccountRepository;


	public AccountOperationServiceImpl(AccountOperationRepository accountOperationRepository,AccountOperationMapper operationMapper,BankAccountRepository bankAccountRepository) {
	this.accountOperationRepository = accountOperationRepository;
	this.operationMapper=operationMapper;
	this.bankAccountRepository=bankAccountRepository;
}

	@Override
	public List<AccountOperationDTO> getOperationsById(String accountId) throws BankAccountNotFoundException {
		List<AccountOperation> accountOperations = accountOperationRepository.findOperationsByBankAccountId(accountId);
		return accountOperations.stream().map(account->operationMapper.fromOperation(account)).collect(Collectors.toList());
	}

	@Override
	public AccountHistoryDTO accountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
		
		Page<AccountOperation> accountOperations=accountOperationRepository.findOperationsByBankAccountId(accountId,PageRequest.of(page,size));
		AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
		List<AccountOperationDTO> accountOperationDTOList= accountOperations.getContent().stream().map(x->operationMapper.fromOperation(x)).collect(Collectors.toList());
		accountHistoryDTO.setOperationList(accountOperationDTOList);
		accountHistoryDTO.setAccountId(accountId);
		accountHistoryDTO.setBalance(bankAccount.getBalance());
		accountHistoryDTO.setCurrentPage(page);
		accountHistoryDTO.setSize(size);
		accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
		return accountHistoryDTO;
	}

}
