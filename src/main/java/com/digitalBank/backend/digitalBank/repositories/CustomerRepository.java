package com.digitalBank.backend.digitalBank.repositories;

import com.digitalBank.backend.digitalBank.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
