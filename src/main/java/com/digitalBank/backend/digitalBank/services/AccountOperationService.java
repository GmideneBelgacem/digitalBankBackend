package com.digitalBank.backend.digitalBank.services;

import java.util.List;

import com.digitalBank.backend.digitalBank.dto.AccountHistoryDTO;
import com.digitalBank.backend.digitalBank.dto.AccountOperationDTO;
import com.digitalBank.backend.digitalBank.exceptions.BankAccountNotFoundException;

public interface AccountOperationService {

	List<AccountOperationDTO> getOperationsById(String accountId)throws BankAccountNotFoundException;

	AccountHistoryDTO accountHistory(String accountId, int page, int size)throws BankAccountNotFoundException ;

}
