package com.digitalBank.backend.digitalBank.services;

import java.util.List;

import com.digitalBank.backend.digitalBank.dto.CustomerDTO;
import com.digitalBank.backend.digitalBank.entities.Customer;
import com.digitalBank.backend.digitalBank.exceptions.CustomerNotFoundException;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
	List<CustomerDTO> listCustomers();

	CustomerDTO findCustomerByID(Long customerId) throws CustomerNotFoundException;
	CustomerDTO createCustomer(CustomerDTO customer);

	CustomerDTO update(CustomerDTO customerRequest)throws CustomerNotFoundException;

	void delete(Long customerId)throws CustomerNotFoundException;

}
