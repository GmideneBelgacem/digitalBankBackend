package com.digitalBank.backend.digitalBank.mappers;

import com.digitalBank.backend.digitalBank.dto.CurrentBankAccountDTO;
import com.digitalBank.backend.digitalBank.dto.SavingBankAccountDTO;
import com.digitalBank.backend.digitalBank.entities.CurrentAccount;
import com.digitalBank.backend.digitalBank.entities.SavingAccount;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
	CustomerMapperImpl customerMapper;

	public BankAccountMapperImpl(CustomerMapperImpl customerMapper) {
		this.customerMapper = customerMapper;
	}

	public CurrentBankAccountDTO fromCurentAccount(CurrentAccount account) {
		CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
		BeanUtils.copyProperties(account, currentBankAccountDTO);
		currentBankAccountDTO.setTypeCompte("current");
		currentBankAccountDTO.setCustomerDTO(this.customerMapper.fromCustomer(account.getCustomer()));
		return currentBankAccountDTO;
	}

	public SavingBankAccountDTO fromSavingAccount(SavingAccount account) {
		SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
		BeanUtils.copyProperties(account, savingBankAccountDTO);
		savingBankAccountDTO.setCustomer(this.customerMapper.fromCustomer(account.getCustomer()));
		savingBankAccountDTO.setTypeCompte("current");
		return savingBankAccountDTO;
	}

	public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO accountDTO) {
		SavingAccount savingAccount = new SavingAccount();
		BeanUtils.copyProperties(accountDTO, savingAccount);
		savingAccount.setCustomer(this.customerMapper.fromCustomerDTO(accountDTO.getCustomer()));
		return savingAccount;
	}

	public CurrentAccount toCurrentAccount(CurrentBankAccountDTO accountDTO) {
		CurrentAccount currentAccount = new CurrentAccount();
		BeanUtils.copyProperties(accountDTO, currentAccount);
		currentAccount.setCustomer(this.customerMapper.fromCustomerDTO(accountDTO.getCustomerDTO()));
		return currentAccount;
	}
}
