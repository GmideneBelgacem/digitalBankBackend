package com.digitalBank.backend.digitalBank.mappers;

import com.digitalBank.backend.digitalBank.dto.CustomerDTO;
import com.digitalBank.backend.digitalBank.entities.Customer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class CustomerMapperImpl {
	public CustomerDTO fromCustomer(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		return customerDTO;
	}
	public Customer fromCustomerDTO(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		return customer;
	}
}
