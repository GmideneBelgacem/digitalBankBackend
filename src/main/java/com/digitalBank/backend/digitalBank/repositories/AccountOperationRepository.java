package com.digitalBank.backend.digitalBank.repositories;

import java.util.List;

import com.digitalBank.backend.digitalBank.entities.AccountOperation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
 List<AccountOperation> findOperationsByBankAccountId(String accountId);
 Page<AccountOperation> findOperationsByBankAccountId(String accountId,Pageable pageable);

}
