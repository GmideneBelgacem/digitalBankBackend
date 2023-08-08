package com.digitalBank.backend.digitalBank.mappers;

import com.digitalBank.backend.digitalBank.dto.AccountOperationDTO;
import com.digitalBank.backend.digitalBank.entities.AccountOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class AccountOperationMapper {
	public AccountOperation fromOperationDTO(AccountOperationDTO operationDTO) {
		AccountOperation operation = new AccountOperation();
		BeanUtils.copyProperties(operationDTO,operation);
		return operation;
	}
	public AccountOperationDTO fromOperation(AccountOperation operation) {
		AccountOperationDTO operationDTO = new AccountOperationDTO();
		BeanUtils.copyProperties(operation,operationDTO);
		return operationDTO;
	}
}
