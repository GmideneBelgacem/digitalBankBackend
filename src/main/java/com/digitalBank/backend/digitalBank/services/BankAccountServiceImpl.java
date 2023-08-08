package com.digitalBank.backend.digitalBank.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.digitalBank.backend.digitalBank.dto.BankAccountDTO;
import com.digitalBank.backend.digitalBank.dto.CurrentBankAccountDTO;
import com.digitalBank.backend.digitalBank.dto.SavingBankAccountDTO;
import com.digitalBank.backend.digitalBank.entities.AccountOperation;
import com.digitalBank.backend.digitalBank.entities.BankAccount;
import com.digitalBank.backend.digitalBank.entities.CurrentAccount;
import com.digitalBank.backend.digitalBank.entities.Customer;
import com.digitalBank.backend.digitalBank.entities.SavingAccount;
import com.digitalBank.backend.digitalBank.enums.OperationType;
import com.digitalBank.backend.digitalBank.exceptions.BalanceNotSufficientException;
import com.digitalBank.backend.digitalBank.exceptions.BankAccountNotFoundException;
import com.digitalBank.backend.digitalBank.exceptions.CustomerNotFoundException;
import com.digitalBank.backend.digitalBank.mappers.BankAccountMapperImpl;
import com.digitalBank.backend.digitalBank.mappers.CustomerMapperImpl;
import com.digitalBank.backend.digitalBank.repositories.AccountOperationRepository;
import com.digitalBank.backend.digitalBank.repositories.BankAccountRepository;
import com.digitalBank.backend.digitalBank.repositories.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

	private CustomerRepository customerRepository;
	private BankAccountRepository bankAccountRepository;
	private AccountOperationRepository operationRepository;
	private BankAccountMapperImpl mapper;
	private CustomerMapperImpl customerMapper;
	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	public BankAccountServiceImpl(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
			AccountOperationRepository operationRepository, BankAccountMapperImpl mapper,CustomerMapperImpl customerMapper) {
		super();
		this.customerRepository = customerRepository;
		this.bankAccountRepository = bankAccountRepository;
		this.operationRepository = operationRepository;
		this.mapper = mapper;
		this.customerMapper=customerMapper;
	}

	@Override
	public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {

		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
		if(bankAccount instanceof SavingAccount){
			return mapper.fromSavingAccount((SavingAccount)bankAccount);
		}
		else return mapper.fromCurentAccount((CurrentAccount)bankAccount);
	}

	@Override
	public void debit(String accountId, double amount, String description)
			throws BankAccountNotFoundException, BalanceNotSufficientException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));		if (amount > bankAccount.getBalance()) {
			throw new BalanceNotSufficientException("balance not sufficient");
		}
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setOperationType(OperationType.DEBIT);
		accountOperation.setAmount(amount);
		accountOperation.setOperationDate(new Date());
		accountOperation.setDescription(description);
		accountOperation.setBankAccount(bankAccount);
		operationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance() - amount);
		bankAccountRepository.save(bankAccount);
	}

	@Override
	public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setOperationType(OperationType.CREDIT);
		accountOperation.setAmount(amount);
		accountOperation.setOperationDate(new Date());
		accountOperation.setDescription(description);
		accountOperation.setBankAccount(bankAccount);
		operationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance() + amount);
		bankAccountRepository.save(bankAccount);
	}

	@Override
	public void transfert(String accountIdSource, String accountIdDestination, double amount)
			throws BankAccountNotFoundException, BalanceNotSufficientException {
		debit(accountIdSource, amount, "transfert");
		credit(accountIdDestination, amount, "transfert from ");
	}

	@Override
	public CurrentBankAccountDTO saveCurrentBankAccount(CurrentBankAccountDTO accountRequest)
			throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(accountRequest.getCustomerDTO().getId()).orElse(null);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found");
		}
		CurrentAccount bankAccount;
		bankAccount=mapper.toCurrentAccount(accountRequest);
		CurrentAccount saved = bankAccountRepository.save(bankAccount);
		return this.mapper.fromCurentAccount(saved);
	}

	@Override
	public SavingBankAccountDTO saveSavingsBankAccount(SavingBankAccountDTO accountRequest)
			throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(accountRequest.getCustomer().getId()).orElse(null);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found");
		}
		SavingAccount bankAccount;
		bankAccount=mapper.fromSavingBankAccountDTO(accountRequest);
		SavingAccount saved = bankAccountRepository.save(bankAccount);
		return this.mapper.fromSavingAccount(saved);
	}

	@Override
	public List<BankAccountDTO> bankAccountList() {
		return bankAccountRepository.findAll().stream().map(x->{
			if(x instanceof SavingAccount){
				return mapper.fromSavingAccount((SavingAccount)x);
			}
			else return mapper.fromCurentAccount((CurrentAccount)x);
		}).collect(Collectors.toList());
	}

}
