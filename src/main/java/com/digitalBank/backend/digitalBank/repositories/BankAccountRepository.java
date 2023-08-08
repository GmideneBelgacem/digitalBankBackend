package com.digitalBank.backend.digitalBank.repositories;

import com.digitalBank.backend.digitalBank.entities.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
